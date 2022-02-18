package com.itacademy.services;

import com.itacademy.dto.TagDto;
import com.itacademy.mapper.TagMapper;
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

    private final TagMapper tagMapper;
    private final TagRepository tagRepository;

    @Override
    public List<TagDto> getTags(Integer id, String name, String sort, Integer pageNum, Integer pageSize) {

        if (!isNull(id) && !isNull(name) && !name.isEmpty()) {
            return List.of(tagMapper.tagToDto(tagRepository.findTagByIdAndName(id, name)));
        } else if (!isNull(id)) {
            if (isNull(getTagById(id)))
                throw new RestApiNotFoundException("There is not exist such id of User");
            return List.of(getTagById(id));
        } else if (!isNull(name)) {

            if(isNull(tagRepository.findTagByName(name))){
                throw new RestApiNotFoundException("Tag with name: " + name + " not exist!");
            }
            return List.of(tagMapper.tagToDto(tagRepository.findTagByName(name)));
        }

        List<TagDto> tagDtoList = new ArrayList<>();
        if (sort.equals("id")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").ascending());

            tagDtoList = tagRepository.findAll(pageable).stream()
                    .map(tagMapper::tagToDto)
                    .collect(Collectors.toList());
        }
        if (sort.equals("name")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("name").ascending());

            tagDtoList = tagRepository.findAll(pageable).stream()
                    .map(tagMapper::tagToDto)
                    .collect(Collectors.toList());
        }

        if (sort.equals("-id")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").descending());

            tagDtoList = tagRepository.findAll(pageable).stream()
                    .map(tagMapper::tagToDto)
                    .collect(Collectors.toList());
        }

        if (sort.equals("-name")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("name").descending());

            tagDtoList = tagRepository.findAll(pageable).stream()
                    .map(tagMapper::tagToDto)
                    .collect(Collectors.toList());
        }

        return tagDtoList;
    }

    @Override
    public TagDto getTagById(Integer id) {
        return tagMapper.tagToDto(tagRepository.findById(id).orElseThrow(
                () -> new RestApiNotFoundException("Tag with id: " + id + " not exist!")
        ));
    }

    @Override
    public void removeTag(Integer id) {
        tagRepository.delete(tagRepository.findById(id).orElseThrow(
                () -> new RestApiNotFoundException("Tag with id: " + id + " not exist!")
        ));

    }
}
