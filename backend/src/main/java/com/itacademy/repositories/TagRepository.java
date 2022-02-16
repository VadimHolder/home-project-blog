package com.itacademy.repositories;


import com.itacademy.dto.TagDto;
import com.itacademy.entities.Tag;
import com.itacademy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findTagById(Integer id);

    Tag findTagByName(String name);

    /*@Override
    Optional<Tag> findByName(String name);
*/
    Tag findTagByIdAndName(Integer id, String name);

}
