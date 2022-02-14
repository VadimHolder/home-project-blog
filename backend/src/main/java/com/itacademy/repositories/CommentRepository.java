package com.itacademy.repositories;

import com.itacademy.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

//сущность с которой будет работать репозиторий -Comment
// и тип первичного ключа -Integer
//Spring automatically implements
// this repository interface in a bean that has the same name commentRepository
public interface CommentRepository extends JpaRepository <Comment, Integer> {
}

