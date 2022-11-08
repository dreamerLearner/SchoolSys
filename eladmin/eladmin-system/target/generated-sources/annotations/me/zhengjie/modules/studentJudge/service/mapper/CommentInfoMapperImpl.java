package me.zhengjie.modules.studentJudge.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.studentJudge.domain.CommentInfo;
import me.zhengjie.modules.studentJudge.service.dto.CommentInfoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-13T22:57:13+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_91 (Oracle Corporation)"
)
@Component
public class CommentInfoMapperImpl implements CommentInfoMapper {

    @Override
    public CommentInfo toEntity(CommentInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        CommentInfo commentInfo = new CommentInfo();

        commentInfo.setId( dto.getId() );
        commentInfo.setUsername( dto.getUsername() );
        commentInfo.setClassId( dto.getClassId() );
        commentInfo.setTeacherId( dto.getTeacherId() );
        commentInfo.setTeacherName( dto.getTeacherName() );
        commentInfo.setMemo( dto.getMemo() );
        commentInfo.setCreatetime( dto.getCreatetime() );
        commentInfo.setStatus( dto.getStatus() );
        commentInfo.setClassName( dto.getClassName() );

        return commentInfo;
    }

    @Override
    public CommentInfoDto toDto(CommentInfo entity) {
        if ( entity == null ) {
            return null;
        }

        CommentInfoDto commentInfoDto = new CommentInfoDto();

        commentInfoDto.setId( entity.getId() );
        commentInfoDto.setUsername( entity.getUsername() );
        commentInfoDto.setClassId( entity.getClassId() );
        commentInfoDto.setTeacherId( entity.getTeacherId() );
        commentInfoDto.setTeacherName( entity.getTeacherName() );
        commentInfoDto.setMemo( entity.getMemo() );
        commentInfoDto.setCreatetime( entity.getCreatetime() );
        commentInfoDto.setStatus( entity.getStatus() );
        commentInfoDto.setClassName( entity.getClassName() );

        return commentInfoDto;
    }

    @Override
    public List<CommentInfo> toEntity(List<CommentInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<CommentInfo> list = new ArrayList<CommentInfo>( dtoList.size() );
        for ( CommentInfoDto commentInfoDto : dtoList ) {
            list.add( toEntity( commentInfoDto ) );
        }

        return list;
    }

    @Override
    public List<CommentInfoDto> toDto(List<CommentInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CommentInfoDto> list = new ArrayList<CommentInfoDto>( entityList.size() );
        for ( CommentInfo commentInfo : entityList ) {
            list.add( toDto( commentInfo ) );
        }

        return list;
    }
}
