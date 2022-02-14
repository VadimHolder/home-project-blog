package com.itacademy.services;


import com.itacademy.dto.TagDto;
import com.itacademy.entities.Tag;
import org.springframework.stereotype.Component;


@Component
public class TagConverter {
    public Tag fromTagDtoToTag(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());

        return tag;
    }

    public TagDto fromTagToTagDto(Tag tag) {
        return TagDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}