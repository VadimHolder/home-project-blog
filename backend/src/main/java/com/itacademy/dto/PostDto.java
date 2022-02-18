package com.itacademy.dto;

import com.itacademy.entities.Tag;
import com.itacademy.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDto {
    private Integer id;
    private Set<TagDto> tags;
    private LocalDateTime createdOn;
    private UserDto author;
    private String text;
    private String title;
    private String previewAttachment;
    private LocalDateTime updatedOn;
}
