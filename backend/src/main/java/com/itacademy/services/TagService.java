package com.itacademy.services;

import com.itacademy.dto.TagDto;
import com.itacademy.dto.UserDto;
import com.itacademy.response.RestApiValidationException;

import java.util.List;

public interface TagService {
    List<TagDto> getTags(Integer id, String name, String sort, Integer pageNum, Integer pageSize);
    TagDto getTagById(Integer id);
    void removeTag(Integer id);
}
