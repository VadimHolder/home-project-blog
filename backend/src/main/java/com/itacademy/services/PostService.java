package com.itacademy.services;

import com.itacademy.dto.PostDto;
import com.itacademy.response.RestApiValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getPosts(Integer id, Integer tag_id, String tag_name,
                           String author_name, String sort, Integer page_num,
                           Integer page_size);

    PostDto getPostById(Integer id);

    PostDto updatePost(PostDto postDto, Integer id);

    void removePost(Integer id);

}
