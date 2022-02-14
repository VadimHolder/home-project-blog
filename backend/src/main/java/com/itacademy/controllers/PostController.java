package com.itacademy.controllers;

import com.itacademy.dto.PostDto;
import com.itacademy.services.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class PostController {

    private PostService postService;




//    @Autowired
//    private ConversionService conversionService;


    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/posts")
    @PreAuthorize("hasAnyAuthority('admin', 'blogger', 'moderator')")
    public PostDto createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }


}
