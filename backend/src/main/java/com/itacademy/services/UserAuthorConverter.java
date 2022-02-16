package com.itacademy.services;

import com.itacademy.dto.AuthorDto;
import com.itacademy.dto.UserDto;
import com.itacademy.entities.User;
import com.itacademy.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserAuthorConverter {
    private UserRepository userRepository;

    public User fromAuthorDtoToUser(AuthorDto authorDto) {

        User user = userRepository.findUserByName(authorDto.getName());
        return user;
    }

    public AuthorDto fromUserToAuthorDto(User user) {
        return AuthorDto.builder()
                .name(user.getName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public AuthorDto fromUserDtoToAuthorDto(UserDto userDto) {
        return AuthorDto.builder()
                .name(userDto.getName())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }
}
