package com.itacademy.mapper;

import com.itacademy.dto.UserDto;
import com.itacademy.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
@Mapper(componentModel = "spring")
public interface UserMapper {
    User userToEntity(UserDto userDto);

    UserDto userToDto(User user);

    List<UserDto> userListToDto(Page<User> userEntities);

    @Mapping(target="id", source = "oldUser.id")
    @Mapping(target="name", source = "updatedUser.name")
    @Mapping(target="firstName", source = "updatedUser.firstName")
    @Mapping(target="lastName", source = "updatedUser.lastName")
    @Mapping(target="email", source = "oldUser.email")
    @Mapping(target="password", source = "oldUser.password")
    @Mapping(target="role", source = "oldUser.role")
    User updateUser(UserDto oldUser, UserDto updatedUser);
}
