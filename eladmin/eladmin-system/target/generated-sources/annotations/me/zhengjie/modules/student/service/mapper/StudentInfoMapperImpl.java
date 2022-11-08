package me.zhengjie.modules.student.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.student.domain.StudentInfo;
import me.zhengjie.modules.student.service.dto.StudentInfoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-13T22:57:13+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_91 (Oracle Corporation)"
)
@Component
public class StudentInfoMapperImpl implements StudentInfoMapper {

    @Override
    public StudentInfo toEntity(StudentInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        StudentInfo studentInfo = new StudentInfo();

        studentInfo.setName( dto.getName() );
        studentInfo.setSex( dto.getSex() );
        studentInfo.setAge( dto.getAge() );
        studentInfo.setProfession( dto.getProfession() );
        studentInfo.setIdnum( dto.getIdnum() );
        studentInfo.setAddress( dto.getAddress() );
        studentInfo.setEmail( dto.getEmail() );
        studentInfo.setHobby( dto.getHobby() );
        studentInfo.setTellnum( dto.getTellnum() );
        studentInfo.setParentname( dto.getParentname() );
        studentInfo.setParentnum( dto.getParentnum() );
        studentInfo.setTeachername( dto.getTeachername() );
        studentInfo.setGuiderteacher( dto.getGuiderteacher() );
        studentInfo.setUsername( dto.getUsername() );
        studentInfo.setUserId( dto.getUserId() );
        studentInfo.setId( dto.getId() );

        return studentInfo;
    }

    @Override
    public StudentInfoDto toDto(StudentInfo entity) {
        if ( entity == null ) {
            return null;
        }

        StudentInfoDto studentInfoDto = new StudentInfoDto();

        studentInfoDto.setName( entity.getName() );
        studentInfoDto.setSex( entity.getSex() );
        studentInfoDto.setAge( entity.getAge() );
        studentInfoDto.setProfession( entity.getProfession() );
        studentInfoDto.setIdnum( entity.getIdnum() );
        studentInfoDto.setAddress( entity.getAddress() );
        studentInfoDto.setEmail( entity.getEmail() );
        studentInfoDto.setHobby( entity.getHobby() );
        studentInfoDto.setTellnum( entity.getTellnum() );
        studentInfoDto.setParentname( entity.getParentname() );
        studentInfoDto.setParentnum( entity.getParentnum() );
        studentInfoDto.setTeachername( entity.getTeachername() );
        studentInfoDto.setGuiderteacher( entity.getGuiderteacher() );
        studentInfoDto.setUserId( entity.getUserId() );
        studentInfoDto.setId( entity.getId() );
        studentInfoDto.setUsername( entity.getUsername() );

        return studentInfoDto;
    }

    @Override
    public List<StudentInfo> toEntity(List<StudentInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<StudentInfo> list = new ArrayList<StudentInfo>( dtoList.size() );
        for ( StudentInfoDto studentInfoDto : dtoList ) {
            list.add( toEntity( studentInfoDto ) );
        }

        return list;
    }

    @Override
    public List<StudentInfoDto> toDto(List<StudentInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<StudentInfoDto> list = new ArrayList<StudentInfoDto>( entityList.size() );
        for ( StudentInfo studentInfo : entityList ) {
            list.add( toDto( studentInfo ) );
        }

        return list;
    }
}
