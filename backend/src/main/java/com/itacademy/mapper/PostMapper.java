package com.itacademy.mapper;


import com.itacademy.dto.PostDto;
import com.itacademy.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto toPostDto(Post postEntity);

    Post toPostEntity(PostDto postDto);

    @Mapping(target = "id", source = "oldPost.id")
    @Mapping(target = "createdOn", source = "oldPost.createdOn")
    @Mapping(target = "tags", source = "newPost.tags")
    @Mapping(target = "author", source = "oldPost.author")
    @Mapping(target = "text", source = "newPost.text")
    @Mapping(target = "title", source = "newPost.title")
    @Mapping(target = "previewAttachment", source = "newPost.previewAttachment")
    @Mapping(target = "updatedOn", source = "newPost.updatedOn")

    Post updatePost(PostDto oldPost, PostDto newPost);

    List<Post> toList(Page<Post> postEntity);

    List<PostDto> toPostDtoList(List<Post> postEntities);
}
