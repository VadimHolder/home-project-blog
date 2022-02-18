package com.itacademy.controllers;

import com.itacademy.dto.PostDto;

import com.itacademy.dto.UserDto;
import com.itacademy.entities.Post;
import com.itacademy.response.RestApiValidationException;
import com.itacademy.services.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Data
//@AllArgsConstructor
public class PostController {

@Autowired
    private PostService postService;


    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/posts")
    @PreAuthorize("hasAnyAuthority('admin', 'blogger', 'moderator')")
    public PostDto createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }


    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/posts/{id}")
    @PreAuthorize("hasAnyAuthority('admin', 'blogger', 'moderator')")
    public PostDto findUserById(@PathVariable Integer id) {

        return postService.getPostById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasAnyAuthority('admin', 'moderator')")
    public ResponseEntity deletePost(@PathVariable Integer id)  {
        postService.removePost(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/posts/{id}")
    @PreAuthorize("hasAnyAuthority('admin', 'moderator')")
    public PostDto updatePost(@RequestBody PostDto postDto, @PathVariable Integer id) throws RestApiValidationException {
        return postService.updatePost(postDto, id);
    }


    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/posts")
    @PreAuthorize("hasAnyAuthority('admin', 'blogger', 'moderator')")
    public List<PostDto> getPosts(@RequestParam(required = false) Integer id,
                                  @RequestParam(required = false) Integer tag_id,
                                  @RequestParam(required = false) String tag_name,
                                  @RequestParam(required = false) String author_name,
                                  @RequestParam(defaultValue = "-id") String sort,
                                  @RequestParam(defaultValue = "0") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        return postService.getPosts(id, tag_id, tag_name,author_name,sort, pageNum, pageSize);
    }

}
