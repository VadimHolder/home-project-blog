package com.itacademy.repositories;

import com.itacademy.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post,Integer> {
}
