package me.zhengjie.modules.grade.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.grade.domain.GradeInfo;
import me.zhengjie.modules.grade.service.dto.GradeInfoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-13T22:57:13+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_91 (Oracle Corporation)"
)
@Component
public class GradeInfoMapperImpl implements GradeInfoMapper {

    @Override
    public GradeInfo toEntity(GradeInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        GradeInfo gradeInfo = new GradeInfo();

        gradeInfo.setId( dto.getId() );
        gradeInfo.setClassId( dto.getClassId() );
        gradeInfo.setClassCode( dto.getClassCode() );
        gradeInfo.setTeacherId( dto.getTeacherId() );
        gradeInfo.setTeacherName( dto.getTeacherName() );
        gradeInfo.setGrade( dto.getGrade() );
        gradeInfo.setCourseName( dto.getCourseName() );
        gradeInfo.setPdate( dto.getPdate() );
        gradeInfo.setStatus( dto.getStatus() );
        gradeInfo.setUsername( dto.getUsername() );
        gradeInfo.setStudentName( dto.getStudentName() );

        return gradeInfo;
    }

    @Override
    public GradeInfoDto toDto(GradeInfo entity) {
        if ( entity == null ) {
            return null;
        }

        GradeInfoDto gradeInfoDto = new GradeInfoDto();

        gradeInfoDto.setId( entity.getId() );
        gradeInfoDto.setClassId( entity.getClassId() );
        gradeInfoDto.setClassCode( entity.getClassCode() );
        gradeInfoDto.setTeacherId( entity.getTeacherId() );
        gradeInfoDto.setTeacherName( entity.getTeacherName() );
        gradeInfoDto.setGrade( entity.getGrade() );
        gradeInfoDto.setCourseName( entity.getCourseName() );
        gradeInfoDto.setPdate( entity.getPdate() );
        gradeInfoDto.setStatus( entity.getStatus() );
        gradeInfoDto.setUsername( entity.getUsername() );
        gradeInfoDto.setStudentName( entity.getStudentName() );

        return gradeInfoDto;
    }

    @Override
    public List<GradeInfo> toEntity(List<GradeInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<GradeInfo> list = new ArrayList<GradeInfo>( dtoList.size() );
        for ( GradeInfoDto gradeInfoDto : dtoList ) {
            list.add( toEntity( gradeInfoDto ) );
        }

        return list;
    }

    @Override
    public List<GradeInfoDto> toDto(List<GradeInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<GradeInfoDto> list = new ArrayList<GradeInfoDto>( entityList.size() );
        for ( GradeInfo gradeInfo : entityList ) {
            list.add( toDto( gradeInfo ) );
        }

        return list;
    }
}
