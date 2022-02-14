package com.itacademy.services;

import com.itacademy.dto.PasswordChangeDto;
import com.itacademy.dto.RoleDto;
import com.itacademy.dto.UserDto;
import com.itacademy.entities.User;
import com.itacademy.repositories.UserRepository;
import com.itacademy.response.RestApiNotFoundException;
import com.itacademy.response.RestApiValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void validateUserDto(UserDto userDto) throws RestApiValidationException {
        if (isNull(userDto)) {
            throw new RestApiValidationException("Object user is null");
        }
        if (isNull(userDto.getName()) || userDto.getName().isEmpty()) {
            throw new RestApiValidationException("Name is empty");
        }
        if (isNull(userDto.getEmail()) || userDto.getEmail().isEmpty()) {
            throw new RestApiValidationException("email is empty");
        }

        if (isNull(userDto.getRole()) || userDto.getRole().isEmpty()) {
            userDto.setRole("blogger");
        } else if (!(userDto.getRole().equals("blogger") || userDto.getRole().equals("admin") || userDto.getRole().equals("moderator"))) {
            throw new RestApiValidationException("role is wrong");
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) throws RestApiValidationException {
        //для валидации входящих данных
        validateUserDto(userDto);

        //конвертирую в юзера
        //сохраняю в базу данных
        //предварительно проверяя дубликацию name, email
        if (!isNull(userRepository.findUserByName(userDto.getName()))) {
            throw new RestApiValidationException("name is busy");
        }
        if (userRepository.findUserByEmail(userDto.getEmail()).isPresent()) {
            throw new RestApiValidationException("email is busy");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(userConverter.fromUserDtoToUser(userDto));
        //конвертирую сохраненного юзера обратно в дто и возвращаю return
        return userConverter.fromUserToUserDto(savedUser);
    }


    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findUserById(id);

        if (isNull(user)) throw new RestApiNotFoundException("There is not exist such User");
        return userConverter.fromUserToUserDto(user);
    }

  /*  @Override
    public String updateUserRole(String role, Integer id) throws RestApiNotFoundException, RestApiValidationException {
        User user = userRepository.findUserById(id);
        if (isNull(user)) throw new RestApiNotFoundException("There is not exist such User");

        if (!(role.equals("blogger") || role.equals("admin") || role.equals("moderator"))) {
            throw new RestApiValidationException("role is wrong, choose blogger, moderator or admin");
        }

        user.setId(id);
        user.setName(user.getName());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setRole(role);

        userRepository.save(user);


        //конвертирую сохраненного юзера обратно в дто и возвращаю return
        return userConverter.fromUserToUserDto(user).getRole();

    }*/

    @Override
    public RoleDto updateUserRole(RoleDto roleDto, Integer id) throws RestApiValidationException {
        User user = userRepository.findUserById(id);
        if (isNull(user)) throw new RestApiNotFoundException("There is not exist such User");

        if (!(roleDto.getName().equals("blogger") || roleDto.getName().equals("admin") || roleDto.getName().equals("moderator"))) {
            throw new RestApiValidationException("role is wrong, choose blogger, moderator or admin");
        }

        user.setId(id);
        user.setName(user.getName());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setRole(roleDto.getName());

        userRepository.save(user);

        //конвертирую сохраненного юзера обратно в дто и возвращаю return
        return new RoleDto(user.getRole());

    }


    @Override
    public void removeUser(Integer id) {
        if (isNull(userRepository.findUserById(id))) {
            throw new RestApiNotFoundException("There is not exist such User");
        }
        userRepository.deleteById(id);
    }

    @Override
    public String getUserRoleById(Integer id) {
        User user = userRepository.findUserById(id);
        if (isNull(user)) throw new RestApiNotFoundException("There is not exist such User");
        return userConverter.fromUserToUserDto(user).getRole();
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) throws RestApiValidationException {
        User user = userRepository.findUserById(id);
        if (isNull(user)) throw new RestApiNotFoundException("There is not exist such User");

        if (!isNull(userRepository.findUserByName(userDto.getName()))) {
            throw new RestApiValidationException("name is busy");
        }
        if (userRepository.findUserByEmail(userDto.getEmail()).isPresent()) {
            throw new RestApiValidationException("email is busy");
        }

        user.setId(id);
        user.setName(userDto.getName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(user.getRole());
        userRepository.save(user);


        //конвертирую сохраненного юзера обратно в дто и возвращаю return
        return userConverter.fromUserToUserDto(user);


    }


    @Override
    public List<UserDto> getUsers(Integer id, String name, String sort, Integer pageNum, Integer pageSize) {


        if (!isNull(id) && !isNull(name) && !name.isEmpty()) {
            if (isNull(userRepository.findUserByIdAndName(id, name)))
                throw new RestApiNotFoundException("There is not exist such combination id-name of User");
            return List.of(userConverter.fromUserToUserDto(userRepository.findUserByIdAndName(id, name)));
        } else if (!isNull(id)) {
            if (isNull(userRepository.findUserById(id)))
                throw new RestApiNotFoundException("There is not exist such id of User");
            return List.of(userConverter.fromUserToUserDto(userRepository.findUserById(id)));
        } else if (!isNull(name)) {
            if (isNull(userRepository.findUserByName(name)))
                throw new RestApiNotFoundException("There is not exist such name of User");
            return List.of(userConverter.fromUserToUserDto(userRepository.findUserByName(name)));
        }

        List<UserDto> userDtoList = new ArrayList<>();
        if (sort.equals("id")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").ascending());
//            Page<User> sortedUsers = userRepository.findAll(pageable);
//            userDtoList = userConverter.fromUserToUserDto(sortedUsers);
            userDtoList = userRepository.findAll(pageable).stream()
                    .map(userConverter::fromUserToUserDto)
                    .collect(Collectors.toList());
        }
        if (sort.equals("name")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("name").ascending());
//            Page<User> sortedUsers = userRepository.findAll(pageable);
//            userDtoList = userConverter.fromUserToUserDto(sortedUsers);
            userDtoList = userRepository.findAll(pageable).stream()
                    .map(userConverter::fromUserToUserDto)
                    .collect(Collectors.toList());
        }

        if (sort.equals("-id")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
//            Page<User> sortedUsers = userRepository.findAll(pageable);
//            userDtoList = userConverter.fromUserToUserDto(sortedUsers.map());
            userDtoList = userRepository.findAll(pageable).stream()
                    .map(userConverter::fromUserToUserDto)
                    .collect(Collectors.toList());
        }

        if (sort.equals("-name")) {
            Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("name").descending());
//            Page<User> sortedUsers = userRepository.findAll(pageable);
//            userDtoList = userConverter.fromUserToUserDto(sortedUsers.map());
            userDtoList = userRepository.findAll(pageable).stream()
                    .map(userConverter::fromUserToUserDto)
                    .collect(Collectors.toList());
        }

        return userDtoList;

    }


    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findUserByName(currentUserName);
        if (isNull(user)) throw new RestApiNotFoundException("There is not exist such User");
        return userConverter.fromUserToUserDto(user);
    }


    @Override
    public UserDto updateCurrentUser(UserDto userDto) throws RestApiValidationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepository.findUserByName(currentUserName);
        if (isNull(user)) throw new RestApiNotFoundException("There is not exist such User");

        if (!isNull(userRepository.findUserByName(userDto.getName()))) {
            throw new RestApiValidationException("name is busy");
        }
        if (userRepository.findUserByEmail(userDto.getEmail()).isPresent()) {
            throw new RestApiValidationException("email is busy");
        }

        user.setId(user.getId());
        user.setName(userDto.getName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(user.getRole());
        userRepository.save(user);


        //конвертирую сохраненного юзера обратно в дто и возвращаю return
        return userConverter.fromUserToUserDto(user);
    }

    @Override
    public void updatePasswordCurrentUser(PasswordChangeDto passwordChangeDto) throws RestApiValidationException {
// get current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findUserByName(currentUserName);
        if (isNull(user)) throw new RestApiNotFoundException("There is not exist such User");
//        get password of current user
        if (passwordEncoder.matches(passwordChangeDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
        } else {
            throw new RestApiValidationException("Wrong old password");
        }
        userRepository.save(user);
    }


}

