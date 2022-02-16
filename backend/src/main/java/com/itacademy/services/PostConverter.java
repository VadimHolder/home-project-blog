package com.itacademy.services;


import com.itacademy.dto.PostDto;
import com.itacademy.entities.Post;
import com.itacademy.entities.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class PostConverter {

    private final UserAuthorConverter userAuthorConverter;
    private final TagConverter tagConverter;

    public Post fromPostDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTags(postDto.getTags());
        post.setCreatedOn(postDto.getCreatedOn());
        post.setAuthor(userAuthorConverter.fromAuthorDtoToUser(postDto.getAuthor()));
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());
        post.setPreviewAttachment(postDto.getPreviewAttachment());
        post.setUpdatedOn(postDto.getUpdatedOn());
        return post;
    }

    public PostDto fromPostToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .tags(post.getTags())
                .createdOn(post.getCreatedOn())
                .author(userAuthorConverter.fromUserToAuthorDto(post.getAuthor()))
                .text(post.getText())
                .title(post.getTitle())
                .previewAttachment(post.getPreviewAttachment())
                .updatedOn(post.getUpdatedOn())
                .build();
    }
}
