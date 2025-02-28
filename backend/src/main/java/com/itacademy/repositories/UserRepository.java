package com.itacademy.repositories;

import com.itacademy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByName(String name);

    Optional<User> findUserByEmail(String email);

    User findUserById(Integer id);

    User findUserByIdAndName(Integer id, String name);


}
