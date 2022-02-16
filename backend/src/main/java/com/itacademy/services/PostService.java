package com.itacademy.services;

import com.itacademy.dto.PostDto;
import com.itacademy.response.RestApiValidationException;

public interface PostService {
    PostDto createPost (PostDto postDto) throws RestApiValidationException;

}
