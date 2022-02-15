package com.itacademy.repositories;


import com.itacademy.entities.Tag;
import com.itacademy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findTagById(Integer id);

    Tag findTagByName(String name);

    Tag findTagByIdAndName(Integer id, String name);
}
