package com.itacademy.dto;

import com.itacademy.entities.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;


//Именно в этот класс мы будем превращать
// нашу сущность юзер когда достанем данные из базы.
// Также этот класс будет нам служить трансфером
// между клиентом, контроллером и сервисом.
@Data
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;


}
