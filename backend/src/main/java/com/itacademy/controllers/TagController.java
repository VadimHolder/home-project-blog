package com.itacademy.controllers;

import com.itacademy.dto.TagDto;
import com.itacademy.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TagController {

private TagService tagService;


    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/tags")
    @PreAuthorize("hasAnyAuthority('admin','moderator','blogger')")
    public List<TagDto> getTags(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name,
                                @RequestParam(defaultValue = "-id") String sort,
                                @RequestParam(defaultValue = "0") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        return tagService.getTags(id, name, sort, pageNum, pageSize);
    }




    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/tags/{id}")
    @PreAuthorize("hasAnyAuthority('admin','moderator','blogger')")
    public TagDto findTagById(@PathVariable Integer id) {

        return tagService.getTagById(id);
    }



    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/tags/{id}")
    @PreAuthorize("hasAnyAuthority('admin','moderator')")
    public ResponseEntity deleteTags(@PathVariable Integer id)  {
        tagService.removeTag(id);
        return ResponseEntity.noContent().build();
    }

}
