package com.itacademy.services;

import com.itacademy.dto.TagDto;
import com.itacademy.entities.Tag;
import com.itacademy.repositories.TagRepository;
import com.itacademy.response.RestApiNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagConverter tagConverter;
    private final TagRepository tagRepository;

    @Override
    public List<TagDto> getTags(Integer id, String name, String sort, Integer pageNum, Integer pageSize) {

        if (!isNull(id) && !isNull(name) && !name.isEmpty()) {
            if (isNull(tagRepository.findTagByIdAndName(id, name)))
                throw new RestApiNotFoundException("There is not exist such combination id-name of Tag");
            return List.of(tagConverter.fromTagToTagDto(tagRepository.findTagByIdAndName(id, name)));
        } else if (!isNull(id)) {
            if (isNull(tagRepository.findTagById(id)))
                throw new RestApiNotFoundException("There is not exist such id of User");
            return List.of(tagConverter.fromTagToTagDto(tagRepository.findTagById(id)));
        } else if (!isNull(name)) {
            if (isNull(tagRepository.findTagByName(name)))
                throw new RestApiNotFoundException("There is not exist such name of User");
            return List.of(tagConverter.fromTagToTagDto(tagRepository.findTagByName(name)));
        }

        List<TagDto> tagDtoList = new ArrayList<>();
        if (sort.equals("id")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").ascending());
//            Page<User> sortedUsers = userRepository.findAll(pageable);
//            userDtoList = userConverter.fromUserToUserDto(sortedUsers);
            tagDtoList = tagRepository.findAll(pageable).stream()
                    .map(tagConverter::fromTagToTagDto)
                    .collect(Collectors.toList());
        }
        if (sort.equals("name")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("name").ascending());
//            Page<User> sortedUsers = userRepository.findAll(pageable);
//            userDtoList = userConverter.fromUserToUserDto(sortedUsers);
            tagDtoList = tagRepository.findAll(pageable).stream()
                    .map(tagConverter::fromTagToTagDto)
                    .collect(Collectors.toList());
        }

        if (sort.equals("-id")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
//            Page<User> sortedUsers = userRepository.findAll(pageable);
//            userDtoList = userConverter.fromUserToUserDto(sortedUsers.map());
            tagDtoList = tagRepository.findAll(pageable).stream()
                    .map(tagConverter::fromTagToTagDto)
                    .collect(Collectors.toList());
        }

        if (sort.equals("-name")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("name").descending());
//            Page<User> sortedUsers = userRepository.findAll(pageable);
//            userDtoList = userConverter.fromUserToUserDto(sortedUsers.map());
            tagDtoList = tagRepository.findAll(pageable).stream()
                    .map(tagConverter::fromTagToTagDto)
                    .collect(Collectors.toList());
        }

        return tagDtoList;
    }

    @Override
    public TagDto getTagById(Integer id) {
        Tag tag = tagRepository.findTagById(id);

        if (isNull(tag)) throw new RestApiNotFoundException("There is not exist such Tag");
        return tagConverter.fromTagToTagDto(tag);
    }

    @Override
    public void removeTag(Integer id) {
        if (isNull(tagRepository.findTagById(id))) {
            throw new RestApiNotFoundException("There is not exist such Tag");
        }
        tagRepository.deleteById(id);

    }
}
