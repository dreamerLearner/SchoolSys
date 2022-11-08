package me.zhengjie.modules.teachers.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.teachers.domain.TeachersInfo;
import me.zhengjie.modules.teachers.service.dto.TeachersInfoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-13T22:57:13+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_91 (Oracle Corporation)"
)
@Component
public class TeachersInfoMapperImpl implements TeachersInfoMapper {

    @Override
    public TeachersInfo toEntity(TeachersInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        TeachersInfo teachersInfo = new TeachersInfo();

        teachersInfo.setId( dto.getId() );
        teachersInfo.setUserId( dto.getUserId() );
        teachersInfo.setName( dto.getName() );
        teachersInfo.setAge( dto.getAge() );
        teachersInfo.setSex( dto.getSex() );
        teachersInfo.setAddress( dto.getAddress() );
        teachersInfo.setPhone( dto.getPhone() );
        teachersInfo.setIdnum( dto.getIdnum() );
        teachersInfo.setTeacherid( dto.getTeacherid() );

        return teachersInfo;
    }

    @Override
    public TeachersInfoDto toDto(TeachersInfo entity) {
        if ( entity == null ) {
            return null;
        }

        TeachersInfoDto teachersInfoDto = new TeachersInfoDto();

        teachersInfoDto.setId( entity.getId() );
        teachersInfoDto.setUserId( entity.getUserId() );
        teachersInfoDto.setName( entity.getName() );
        teachersInfoDto.setAge( entity.getAge() );
        teachersInfoDto.setSex( entity.getSex() );
        teachersInfoDto.setAddress( entity.getAddress() );
        teachersInfoDto.setPhone( entity.getPhone() );
        teachersInfoDto.setIdnum( entity.getIdnum() );
        teachersInfoDto.setTeacherid( entity.getTeacherid() );

        return teachersInfoDto;
    }

    @Override
    public List<TeachersInfo> toEntity(List<TeachersInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TeachersInfo> list = new ArrayList<TeachersInfo>( dtoList.size() );
        for ( TeachersInfoDto teachersInfoDto : dtoList ) {
            list.add( toEntity( teachersInfoDto ) );
        }

        return list;
    }

    @Override
    public List<TeachersInfoDto> toDto(List<TeachersInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TeachersInfoDto> list = new ArrayList<TeachersInfoDto>( entityList.size() );
        for ( TeachersInfo teachersInfo : entityList ) {
            list.add( toDto( teachersInfo ) );
        }

        return list;
    }
}
