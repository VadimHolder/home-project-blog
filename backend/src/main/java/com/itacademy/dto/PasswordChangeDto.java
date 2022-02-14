package com.itacademy.dto;

import lombok.Data;

@Data
public class PasswordChangeDto {
    String oldPassword;
    String newPassword;
}
