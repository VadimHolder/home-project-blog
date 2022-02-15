package com.itacademy.services;


import com.itacademy.dto.PostDto;
import com.itacademy.entities.Post;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
/*
@Component
public class PostConverter {
    public Post fromPostDtoToPost(PostDto postDto) {
      *//*  Post post = new Post();
//        post.setId(postDto.getId());
        post.setTags(postDto.getTags());
//        post.setCreatedOn(String.valueOf(postDto.getCreatedOn()));
//        post.setAuthor(postDto.getAuthor());
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());
        post.setPreviewAttachment(postDto.getPreviewAttachment());
//        post.setUpdatedOn(String.valueOf(postDto.getUpdatedOn()));
        return post;*//*
    }

    public PostDto fromPostToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .tags(post.getTags())
                .createdOn(OffsetDateTime.parse(post.getCreatedOn()))
                .author(post.getAuthor())
                .text(post.getText())
                .title(post.getTitle())
                .previewAttachment(post.getPreviewAttachment())
                .updatedOn(OffsetDateTime.parse(post.getUpdatedOn()))
                .build();
    }
}*/
