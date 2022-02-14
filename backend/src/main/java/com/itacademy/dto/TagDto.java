package com.itacademy.dto;

import com.itacademy.entities.Post;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDto {
    private Integer id;
    private String name;

}
