package com.itacademy.repositories;


import com.itacademy.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findTagByName(String name);
    Tag findTagById (Integer id);


    Tag findTagByIdAndName(Integer id, String name);

//    Tag findTagByIdAnAndName(Integer id, String name);

//    Optional<List<Tag>> findTagsByName(List<String> name);


}
