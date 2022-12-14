package me.zhengjie.modules.system.service.impl;

import me.zhengjie.exception.EntityExistException;
import me.zhengjie.exception.EntityNotFoundException;
import me.zhengjie.modules.student.repository.StudentInfoRepository;
import me.zhengjie.modules.system.domain.*;
import me.zhengjie.modules.system.domain.vo.UserBatchVo;
import me.zhengjie.modules.system.repository.RoleRepository;
import me.zhengjie.modules.system.repository.UserAvatarRepository;
import me.zhengjie.modules.system.repository.UserRepository;
import me.zhengjie.modules.system.service.UserService;
import me.zhengjie.modules.system.service.dto.RoleSmallDto;
import me.zhengjie.modules.system.service.dto.UserDto;
import me.zhengjie.modules.system.service.dto.UserQueryCriteria;
import me.zhengjie.modules.system.service.mapper.UserMapper;
import me.zhengjie.utils.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author bessie
 * @date 2018-11-23
 */
@Service
@CacheConfig(cacheNames = "user")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StudentInfoRepository studentInfoRepository;
    private final RoleRepository RoleRepository ;
    private final UserMapper userMapper;
    private final RedisUtils redisUtils;
    private final UserAvatarRepository userAvatarRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${file.avatar}")
    private String avatar;

    public UserServiceImpl(UserRepository userRepository, StudentInfoRepository studentInfoRepository, me.zhengjie.modules.system.repository.RoleRepository roleRepository, UserMapper userMapper, RedisUtils redisUtils, UserAvatarRepository userAvatarRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentInfoRepository = studentInfoRepository;
        RoleRepository = roleRepository;
        this.userMapper = userMapper;
        this.redisUtils = redisUtils;
        this.userAvatarRepository = userAvatarRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Cacheable
    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(userMapper::toDto));
    }

    @Override
    @Cacheable
    public List<UserDto> queryAll(UserQueryCriteria criteria) {
        List<User> users = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
        return userMapper.toDto(users);
    }

    @Override
    @Cacheable(key = "#p0")
    public UserDto findById(long id) {
        User user = userRepository.findById(id).orElseGet(User::new);
        ValidationUtil.isNull(user.getId(), "User", "id", id);
        return userMapper.toDto(user);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public UserDto create(User resources) {
        if (userRepository.findByUsername(resources.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        if (userRepository.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        return userMapper.toDto(userRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {
        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
        ValidationUtil.isNull(user.getId(), "User", "id", resources.getId());
        User user1 = userRepository.findByUsername(user.getUsername());
        User user2 = userRepository.findByEmail(user.getEmail());

        if (user1 != null && !user.getId().equals(user1.getId())) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }

        if (user2 != null && !user.getId().equals(user2.getId())) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }

        // ????????????????????????????????????????????????????????????
        if (!resources.getRoles().equals(user.getRoles())) {
            String key = "role::loadPermissionByUser:" + user.getUsername();
            redisUtils.del(key);
            key = "role::findByUsers_Id:" + user.getId();
            redisUtils.del(key);
        }

        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setRoles(resources.getRoles());
        user.setDept(resources.getDept());
        user.setJob(resources.getJob());
        user.setPhone(resources.getPhone());
        user.setNickName(resources.getNickName());
        user.setSex(resources.getSex());
        user.setProfession(resources.getProfession());
        userRepository.save(user);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updateCenter(User resources) {
        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
        user.setNickName(resources.getNickName());
        user.setPhone(resources.getPhone());
        user.setSex(resources.getSex());
        user.setEmail(resources.getEmail());
        userRepository.save(user);
    }

    /**
     * ?????????????????????id
     *
     * @param userName
     * @return
     */
    @Override
    public Object getUserIdByName(String userName) {
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = userRepository.getUserIdByName(userName);

        String id = list.get(0).get("id").toString();
        String username = list.get(0).get("username").toString();
        String email = list.get(0).get("email").toString();
        String phone = list.get(0).get("phone").toString();

        map.put("id", id);
        map.put("username", username);
        map.put("email", email);
        map.put("phone", phone);

        newList.add(map);

        return newList;
    }

    /**
     * ????????????
     * @param userbatch
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object batchCreate(List<UserBatchVo> userBatchVos) {

        List aaa=new ArrayList();
        for (int i = 0; i < userBatchVos.size(); i++) {
            User user=new User();
            UserBatchVo batch = userBatchVos.get(i);
            String username=batch.getUsername();
            String email=batch.getEmail();
            Boolean enbaled=true;
            String password=passwordEncoder.encode("123456");
            String phone=batch.getPhone();
            Dept dept=new Dept();
            dept.setId(Long.valueOf("17"));
            Job job=new Job();
            job.setId(Long.valueOf("15"));
//            Long dept=Long.valueOf("17");
//            Long job =Long.valueOf("15");
            String nickname=batch.getNickName();
            String sex=batch.getSex();
            String profession=batch.getProfession();
            System.out.println("--------");
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setEnabled(enbaled);
            user.setPhone(phone);
            user.setDept(dept);
            user.setJob(job);
            user.setNickName(nickname);
            user.setSex(sex);
            user.setProfession(profession);
            userRepository.save(user);
            Long id =user.getId();
            aaa.add(id);
            studentInfoRepository.InsertStudentInfo(username,nickname,sex,profession);
            Long roled=Long.valueOf("3");
            RoleRepository.insertUserId(id,roled);
        }
        System.out.println(aaa);
        return  null;
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            userRepository.deleteById(id);
        }
    }

    @Override
    @Cacheable(key = "'loadUserByUsername:'+#p0")
    public UserDto findByName(String userName) {
        User user;
        if (ValidationUtil.isEmail(userName)) {
            user = userRepository.findByEmail(userName);
        } else {
            user = userRepository.findByUsername(userName);
        }
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userMapper.toDto(user);
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String pass) {
        userRepository.updatePass(username, pass, new Date());
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updateAvatar(MultipartFile multipartFile) {
        User user = userRepository.findByUsername(SecurityUtils.getUsername());
        UserAvatar userAvatar = user.getUserAvatar();
        String oldPath = "";
        if (userAvatar != null) {
            oldPath = userAvatar.getPath();
        }
        File file = FileUtil.upload(multipartFile, avatar);
        assert file != null;
        userAvatar = userAvatarRepository.save(new UserAvatar(userAvatar, file.getName(), file.getPath(), FileUtil.getSize(multipartFile.getSize())));
        user.setUserAvatar(userAvatar);
        userRepository.save(user);
        if (StringUtils.isNotBlank(oldPath)) {
            FileUtil.del(oldPath);
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String username, String email) {
        userRepository.updateEmail(username, email);
    }

    @Override
    public void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserDto userDTO : queryAll) {
            List<String> roles = userDTO.getRoles().stream().map(RoleSmallDto::getName).collect(Collectors.toList());
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("?????????", userDTO.getUsername());
            map.put("??????", userDTO.getAvatar());
            map.put("??????", userDTO.getEmail());
            map.put("??????", userDTO.getEnabled() ? "??????" : "??????");
            map.put("????????????", userDTO.getPhone());
            map.put("??????", roles);
            map.put("??????", userDTO.getDept().getName());
            map.put("??????", userDTO.getJob().getName());
            map.put("???????????????????????????", userDTO.getLastPasswordResetTime());
            map.put("????????????", userDTO.getCreateTime());
            map.put("??????", userDTO.getProfession());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
