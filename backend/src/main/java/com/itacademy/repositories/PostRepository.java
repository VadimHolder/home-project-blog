package com.itacademy.repositories;

import com.itacademy.entities.Post;
import com.itacademy.entities.Tag;
import com.itacademy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository <Post,Integer> {


    List<Post> findPostsByIdAndTagsAndAuthor(Integer id, Tag tag, User author);

    List<Post> findPostsByTagsAndAuthor(Tag tag, User author);



   List<Post> findPostsByIdAndAuthor(Integer id, User author);

    List<Post> findPostsByAuthor(User user);

    List<Post> findPostsByIdAndTags(Integer id, Tag tag);

  List<Post> findPostsByTags(Tag tag);
   Post findPostById (Integer id);
}
