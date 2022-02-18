package com.itacademy.mapper;

import com.itacademy.dto.TagDto;
import com.itacademy.entities.Tag;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto tagToDto(Tag tag);
//    Tag tagToTag (Optional <Tag> tag);

    Tag tagToEntity(TagDto tagDto);

    List<Tag> tagToEntityList(Page<Tag> tagsPage);

    List<TagDto> tagToDtoList(List<Tag> tags);
}
