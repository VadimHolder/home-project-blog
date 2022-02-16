package com.itacademy.services;

import com.itacademy.dto.PostDto;
import com.itacademy.entities.Post;
import com.itacademy.repositories.PostRepository;
import com.itacademy.response.RestApiValidationException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private final PostConverter postConverter;
    @Autowired
    private final PostDtoValidation postDtoValidation;

    @Override
    public PostDto createPost(PostDto postDto) throws RestApiValidationException {
        PostDto validatedPosDto = postDtoValidation.validatePostDto(postDto);
        Post post = postConverter.fromPostDtoToPost(validatedPosDto);
        postRepository.save(post);
        return postConverter.fromPostToPostDto(post);
    }
}
