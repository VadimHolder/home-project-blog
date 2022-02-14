package com.itacademy.dto;

import com.itacademy.entities.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDto {
    private Integer id;
    private String text;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private User userComment;
}
