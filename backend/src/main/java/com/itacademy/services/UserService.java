package com.itacademy.services;

//в интерфейсе добавляем методы которые нам нужны для работы


import com.itacademy.dto.PasswordChangeDto;
import com.itacademy.dto.RoleDto;
import com.itacademy.dto.UserDto;
import com.itacademy.response.RestApiValidationException;


import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto) throws RestApiValidationException;

    List<UserDto> getUsers(Integer id, String name, String sort, Integer pageNum, Integer pageSize);

    UserDto getUserById(Integer id);

    UserDto updateUser(UserDto userDto, Integer id) throws RestApiValidationException;

    void removeUser(Integer id);

    String getUserRoleById(Integer id);

//    String updateUserRole(String role, Integer id) throws RestApiNotFoundException, RestApiValidationException;

    RoleDto updateUserRole(RoleDto roleDto, Integer id) throws RestApiValidationException;

    UserDto getCurrentUser ();
    UserDto updateCurrentUser(UserDto userDto) throws RestApiValidationException;
    void updatePasswordCurrentUser (PasswordChangeDto passwordChangeDto) throws RestApiValidationException;


}
