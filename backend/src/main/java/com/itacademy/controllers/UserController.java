package com.itacademy.controllers;

import com.itacademy.dto.PasswordChangeDto;
import com.itacademy.dto.RoleDto;
import com.itacademy.dto.UserDto;
import com.itacademy.response.RestApiValidationException;
import com.itacademy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/1")
@AllArgsConstructor
public class UserController {


    private UserService userService;


    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('admin', 'blogger', 'moderator')")
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok("Server is working");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Some exception is happen");
        }
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/users")
    @PreAuthorize("hasAnyAuthority('admin', 'blogger', 'moderator')")
    public UserDto createUser(@RequestBody UserDto userDto) throws RestApiValidationException {
        return userService.createUser(userDto);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity deleteUsers(@PathVariable Integer id)  {
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public UserDto findUserById(@PathVariable Integer id) {

        return userService.getUserById(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/users/{id}/role")
    @PreAuthorize("hasAnyAuthority('admin')")
    public String getUserRoleById(@PathVariable Integer id) {
        return userService.getUserRoleById(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/users/{id}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) throws  RestApiValidationException {
        return userService.updateUser(userDto, id);
    }

    /* @ResponseStatus(value = HttpStatus.OK)
     @PutMapping("/users/{id}/role")
     public String updateUserRole(@RequestBody String role, @PathVariable Integer id) throws RestApiNotFoundException, RestApiValidationException {
         return userService.updateUserRole(role, id);
     }*/

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/users/{id}/role")
    @PreAuthorize("hasAnyAuthority('admin')")
    public RoleDto updateUserRole(@RequestBody RoleDto roleDto, @PathVariable Integer id) throws  RestApiValidationException {
        return userService.updateUserRole(roleDto, id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('admin')")
    public List<UserDto> getUsers(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name,
                                  @RequestParam(defaultValue = "-id") String sort,
                                  @RequestParam(defaultValue = "0") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        return userService.getUsers(id, name, sort, pageNum, pageSize);
    }


    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/users/current")
    @PreAuthorize("hasAnyAuthority('admin', 'blogger', 'moderator')")
    public UserDto getCurrentUser() {

        return userService.getCurrentUser();
    }


    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/users/current")
    @PreAuthorize("hasAnyAuthority('admin', 'blogger', 'moderator')")
    public UserDto updateCurrentUser(@RequestBody UserDto userDto) throws  RestApiValidationException {
        return userService.updateCurrentUser(userDto);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/users/current/password")
    @PreAuthorize("hasAnyAuthority('admin', 'blogger', 'moderator')")
    public void updatePasswordCurrentUser(@RequestBody PasswordChangeDto passwordChangeDto) throws RestApiValidationException {
        userService.updatePasswordCurrentUser(passwordChangeDto);
    }



}



