package com.itacademy.dto;

import com.itacademy.entities.Tag;
import com.itacademy.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDto {
    private Integer id;
    private List<Tag> tags = new ArrayList<>();
    private OffsetDateTime createdOn;
    private User author;
    private String text;
    private String title;
    private String previewAttachment;
    private OffsetDateTime updatedOn;
}
