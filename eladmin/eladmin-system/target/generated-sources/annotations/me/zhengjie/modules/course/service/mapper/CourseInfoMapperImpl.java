package me.zhengjie.modules.course.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.course.domain.CourseInfo;
import me.zhengjie.modules.course.service.dto.CourseInfoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-13T22:57:13+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_91 (Oracle Corporation)"
)
@Component
public class CourseInfoMapperImpl implements CourseInfoMapper {

    @Override
    public CourseInfo toEntity(CourseInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        CourseInfo courseInfo = new CourseInfo();

        courseInfo.setId( dto.getId() );
        courseInfo.setName( dto.getName() );
        courseInfo.setCreateTime( dto.getCreateTime() );
        courseInfo.setClasstime( dto.getClasstime() );
        courseInfo.setClassNum( dto.getClassNum() );
        courseInfo.setClassTeacher( dto.getClassTeacher() );
        courseInfo.setTeacherId( dto.getTeacherId() );
        courseInfo.setClassCode( dto.getClassCode() );
        courseInfo.setTearm( dto.getTearm() );
        courseInfo.setSchoolYear( dto.getSchoolYear() );
        courseInfo.setStatus( dto.getStatus() );

        return courseInfo;
    }

    @Override
    public CourseInfoDto toDto(CourseInfo entity) {
        if ( entity == null ) {
            return null;
        }

        CourseInfoDto courseInfoDto = new CourseInfoDto();

        courseInfoDto.setId( entity.getId() );
        courseInfoDto.setName( entity.getName() );
        courseInfoDto.setCreateTime( entity.getCreateTime() );
        courseInfoDto.setClasstime( entity.getClasstime() );
        courseInfoDto.setClassNum( entity.getClassNum() );
        courseInfoDto.setClassTeacher( entity.getClassTeacher() );
        courseInfoDto.setTeacherId( entity.getTeacherId() );
        courseInfoDto.setClassCode( entity.getClassCode() );
        courseInfoDto.setTearm( entity.getTearm() );
        courseInfoDto.setSchoolYear( entity.getSchoolYear() );
        courseInfoDto.setStatus( entity.getStatus() );

        return courseInfoDto;
    }

    @Override
    public List<CourseInfo> toEntity(List<CourseInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<CourseInfo> list = new ArrayList<CourseInfo>( dtoList.size() );
        for ( CourseInfoDto courseInfoDto : dtoList ) {
            list.add( toEntity( courseInfoDto ) );
        }

        return list;
    }

    @Override
    public List<CourseInfoDto> toDto(List<CourseInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CourseInfoDto> list = new ArrayList<CourseInfoDto>( entityList.size() );
        for ( CourseInfo courseInfo : entityList ) {
            list.add( toDto( courseInfo ) );
        }

        return list;
    }
}
