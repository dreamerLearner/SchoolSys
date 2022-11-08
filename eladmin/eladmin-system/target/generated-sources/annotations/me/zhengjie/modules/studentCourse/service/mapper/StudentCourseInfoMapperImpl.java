package me.zhengjie.modules.studentCourse.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.studentCourse.domain.StudentCourseInfo;
import me.zhengjie.modules.studentCourse.service.dto.StudentCourseInfoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-13T22:57:13+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_91 (Oracle Corporation)"
)
@Component
public class StudentCourseInfoMapperImpl implements StudentCourseInfoMapper {

    @Override
    public StudentCourseInfo toEntity(StudentCourseInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        StudentCourseInfo studentCourseInfo = new StudentCourseInfo();

        studentCourseInfo.setStudentId( dto.getStudentId() );
        studentCourseInfo.setStudentCode( dto.getStudentCode() );
        studentCourseInfo.setStudentName( dto.getStudentName() );
        studentCourseInfo.setCourseCode( dto.getCourseCode() );
        studentCourseInfo.setCourseName( dto.getCourseName() );
        studentCourseInfo.setPdate( dto.getPdate() );
        studentCourseInfo.setTearm( dto.getTearm() );
        studentCourseInfo.setId( dto.getId() );

        return studentCourseInfo;
    }

    @Override
    public StudentCourseInfoDto toDto(StudentCourseInfo entity) {
        if ( entity == null ) {
            return null;
        }

        StudentCourseInfoDto studentCourseInfoDto = new StudentCourseInfoDto();

        studentCourseInfoDto.setStudentId( entity.getStudentId() );
        studentCourseInfoDto.setStudentCode( entity.getStudentCode() );
        studentCourseInfoDto.setCourseCode( entity.getCourseCode() );
        studentCourseInfoDto.setStudentName( entity.getStudentName() );
        studentCourseInfoDto.setCourseName( entity.getCourseName() );
        studentCourseInfoDto.setPdate( entity.getPdate() );
        studentCourseInfoDto.setTearm( entity.getTearm() );
        studentCourseInfoDto.setId( entity.getId() );

        return studentCourseInfoDto;
    }

    @Override
    public List<StudentCourseInfo> toEntity(List<StudentCourseInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<StudentCourseInfo> list = new ArrayList<StudentCourseInfo>( dtoList.size() );
        for ( StudentCourseInfoDto studentCourseInfoDto : dtoList ) {
            list.add( toEntity( studentCourseInfoDto ) );
        }

        return list;
    }

    @Override
    public List<StudentCourseInfoDto> toDto(List<StudentCourseInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<StudentCourseInfoDto> list = new ArrayList<StudentCourseInfoDto>( entityList.size() );
        for ( StudentCourseInfo studentCourseInfo : entityList ) {
            list.add( toDto( studentCourseInfo ) );
        }

        return list;
    }
}
