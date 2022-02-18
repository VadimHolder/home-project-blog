package com.itacademy.services;

import com.itacademy.dto.PostDto;
import com.itacademy.entities.Post;
import com.itacademy.entities.Tag;
import com.itacademy.entities.User;
import com.itacademy.mapper.PostMapper;
import com.itacademy.mapper.UserMapper;
import com.itacademy.repositories.PostRepository;
import com.itacademy.repositories.TagRepository;
import com.itacademy.repositories.UserRepository;
import com.itacademy.response.RestApiNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserService userService;

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    private final TagRepository tagRepository;

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        User user = userMapper.userToEntity(userService.getCurrentUser());
        Post post = postMapper.toPostEntity(postDto);
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
        postRepository.save(post);
        return postMapper.toPostDto(post);

    }


    @Override
    public List<PostDto> getPosts(Integer id,
                                  Integer tag_id,
                                  String tag_name,
                                  String author_name,
                                  String sort,
                                  Integer page_num,
                                  Integer page_size) {


        if (id != null && tag_id != null && tag_name != null && author_name != null) {

            if (isNull(postRepository.findPostsByIdAndTagsAndAuthor(
                    id,
                    tagRepository.findTagByIdAndName(tag_id, tag_name),
                    userRepository.findUserByName(author_name))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByIdAndTagsAndAuthor(
                            id,
                            tagRepository.findTagByIdAndName(tag_id, tag_name),
                            userRepository.findUserByName(author_name))
                    );


        } else if (id != null && tag_id != null && tag_name != null) {
            if (isNull(postRepository.findPostsByIdAndTags(
                    id,
                    tagRepository.findTagByIdAndName(tag_id, tag_name))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByIdAndTags(
                            id,
                            tagRepository.findTagByIdAndName(tag_id, tag_name))
            );

        } else if (tag_id != null && tag_name != null && author_name != null) {
            if (isNull(postRepository.findPostsByTagsAndAuthor(
                    tagRepository.findTagByIdAndName(tag_id, tag_name),
                    userRepository.findUserByName(author_name))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByTagsAndAuthor(
                            tagRepository.findTagByIdAndName(tag_id, tag_name),
                            userRepository.findUserByName(author_name))
            );

        } else if (id != null && tag_name != null && author_name != null) {
            if (isNull(postRepository.findPostsByIdAndTagsAndAuthor(
                    id,
                    tagRepository.findTagByName(tag_name),
                    userRepository.findUserByName(author_name))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByIdAndTagsAndAuthor(
                            id,
                            tagRepository.findTagByName(tag_name),
                            userRepository.findUserByName(author_name))
            );

        } else if (id != null  && tag_id != null) {
            if (isNull(postRepository.findPostsByIdAndTags(
                    id,
                    tagRepository.findTagById(tag_id))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByIdAndTags(
                            id,
                            tagRepository.findTagById(tag_id))
            );

        } else if (id != null  && tag_name != null) {
           if(isNull(postRepository.findPostsByIdAndTags(
                    id,
                    tagRepository.findTagByName(tag_name))))
            throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByIdAndTags(
                            id,
                            tagRepository.findTagByName(tag_name))
            );

        } else if(id != null && author_name != null) {
            if (isNull(postRepository.findPostsByIdAndAuthor(
                    id,
                    userRepository.findUserByName(author_name))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByIdAndAuthor(
                            id,
                            userRepository.findUserByName(author_name))
            );

        } else if (tag_id != null && author_name != null){
            if (isNull(postRepository.findPostsByTagsAndAuthor(

                    tagRepository.findTagById(tag_id),
                    userRepository.findUserByName(author_name))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByTagsAndAuthor(
                            tagRepository.findTagById(tag_id),
                            userRepository.findUserByName(author_name))
            );
        } else if (tag_name != null && author_name != null) {
            if (isNull(postRepository.findPostsByTagsAndAuthor(

                    tagRepository.findTagByName(tag_name),
                    userRepository.findUserByName(author_name))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByTagsAndAuthor(
                            tagRepository.findTagByName(tag_name),
                            userRepository.findUserByName(author_name))
            );
        } else if(id != null) {
            if (isNull(postRepository.findPostById(id)))
                throw new RestApiNotFoundException("There is not exist such Post");

            return List.of(postMapper.toPostDto(postRepository.findPostById(id)));

        } else if(tag_id != null){
            if (isNull(postRepository.findPostsByTags(
                    tagRepository.findTagById(tag_id))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByTags(
                            tagRepository.findTagById(tag_id))
            );
        } else if (tag_name != null) {
            if (isNull(postRepository.findPostsByTags(
                    tagRepository.findTagByName(tag_name))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByTags(
                            tagRepository.findTagByName(tag_name))
            );


        }else if(author_name != null) {
            if (isNull(postRepository.findPostsByAuthor(userRepository.findUserByName(author_name))))
                throw new RestApiNotFoundException("There is not exist such Post");

            return postMapper.toPostDtoList(
                    postRepository.findPostsByAuthor(userRepository.findUserByName(author_name))
            );
        }



        else {
            Pageable pagination = ProjectUtils.pagination(sort, page_num, page_size);
            return postMapper.toPostDtoList(postMapper.toList(postRepository.findAll(pagination)));
        }

    }

    @Override
    public PostDto getPostById(Integer id) {

        return postMapper.toPostDto(postRepository.findById(id).orElseThrow(() -> new RestApiNotFoundException("There is not exist such Post")));
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {
        PostDto oldPost = getPostById(id);
        postDto.setUpdatedOn(LocalDateTime.now());
        Post post = postRepository.save(postMapper.updatePost(oldPost, postDto));
        return postMapper.toPostDto(post);
    }

    @Override
    public void removePost(Integer id) {
        if (isNull(postRepository.findPostById(id))) {
            throw new RestApiNotFoundException("There is not exist such Post");
        }
        postRepository.deleteById(id);
    }
}
