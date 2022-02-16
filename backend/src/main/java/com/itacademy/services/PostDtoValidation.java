package com.itacademy.services;


import com.itacademy.dto.AuthorDto;
import com.itacademy.dto.PostDto;
import com.itacademy.dto.TagDto;
import com.itacademy.dto.UserDto;
import com.itacademy.entities.Post;
import com.itacademy.entities.Tag;
import com.itacademy.entities.User;
import com.itacademy.repositories.TagRepository;
import com.itacademy.response.RestApiValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Service
@AllArgsConstructor
public class PostDtoValidation {


    private final UserService userService;
    private final TagRepository tagRepository;
    private final TagConverter tagConverter;
    private final UserConverter userConverter;
    private final UserAuthorConverter userAuthorConverter;
    private final PostConverter postConverter;

    public PostDto validatePostDto(PostDto postDto) throws RestApiValidationException {

//        Validation object and important fields for exceptions

        if (isNull(postDto)) {
            throw new RestApiValidationException("Object PostDto is null");
        }
        if (isNull(postDto.getTags()) || postDto.getTags().isEmpty()) {
            throw new RestApiValidationException("Tags are empty");
        }
        if (isNull(postDto.getText()) || postDto.getText().isEmpty()) {
            throw new RestApiValidationException("text is empty");
        }

        if (isNull(postDto.getTitle()) || postDto.getTitle().isEmpty()) {
            throw new RestApiValidationException("title is empty");
        }
        if (isNull(postDto.getPreviewAttachment()) || postDto.getPreviewAttachment().isEmpty()) {
            throw new RestApiValidationException("preview attachment is empty");
        }

//Validation all fields correct data

        PostDto validatedPostDto = new PostDto();

// Find by name tags from DB, compare them, add in DB new items, get from repository list of tags in format id-name
        List<Tag> tagsFromRepository = tagRepository.findAll();
        Set<String> tagsNameFromRepository = tagsFromRepository.stream().map(Tag::getName).collect(Collectors.toSet());
        Set<Tag> tagsDtoFromPostDto = tagsFromRepository.stream().filter(postDto.getTags()::contains).collect(Collectors.toSet());
        Set<Tag> newTags = postDto.getTags().stream()
                .filter(tagFromPostDto -> !tagsNameFromRepository.contains(tagFromPostDto.getName()))
                .map(tagRepository::save).collect(Collectors.toSet());
        newTags.addAll(tagsDtoFromPostDto);
        postDto.getTags().clear();
        postDto.setTags(newTags);
//        validatedPostDto.setTags(postDto.getTags());


        validatedPostDto.setTags(postDto.getTags());
        UserDto currentUserDto = userService.getCurrentUser();
        validatedPostDto.setAuthor(userAuthorConverter.fromUserDtoToAuthorDto(currentUserDto));
        validatedPostDto.setId(postDto.getId());
        validatedPostDto.setCreatedOn(LocalDateTime.now());
        validatedPostDto.setText(postDto.getText());
        validatedPostDto.setTitle(postDto.getTitle());
        validatedPostDto.setPreviewAttachment(postDto.getPreviewAttachment());
        validatedPostDto.setUpdatedOn(LocalDateTime.now());

        return validatedPostDto;
/*

        User user = userConverter.fromUserDtoToUser(userService.getCurrentUser());
        Post post = postConverter.fromPostDtoToPost(postDto);
        post.setAuthor(user);

        List<Tag> oldTags = tagRepository.findAll();
        Set<String> tagsName = oldTags.stream().map(Tag::getName).collect(Collectors.toSet());

        Set<Tag> collect = oldTags.stream().filter(post.getTags()::contains).collect(Collectors.toSet());


        Set<Tag> newTags = post.getTags().stream()
                .filter(tagEntity -> !tagsName.contains(tagEntity.getName()))
                .map(tagRepository::save).collect(Collectors.toSet());

        newTags.addAll(collect);
        post.getTags().clear();
        post.setTags(newTags);

        post.setCreatedOn(LocalDateTime.now());
        post.setUpdatedOn(LocalDateTime.now());
//        postRepository.save(post);
        return postConverter.fromPostToPostDto(post);

*/




    }
}
