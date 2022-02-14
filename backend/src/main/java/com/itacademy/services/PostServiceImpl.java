package com.itacademy.services;

import com.itacademy.dto.PostDto;
import com.itacademy.entities.Post;
import com.itacademy.repositories.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private UserConverter userConverter;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post;
        post = postConverter.fromPostDtoToPost(postDto);
        post.setAuthor(userConverter.fromUserDtoToUser(userService.getCurrentUser()));
        post.setCreatedOn(String.valueOf(OffsetDateTime.now()));
        post.setUpdatedOn(String.valueOf(OffsetDateTime.now()));
        postRepository.save(post);
        return postConverter.fromPostToPostDto(post);
    }
}
