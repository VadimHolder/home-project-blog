package com.itacademy.entities;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // связь с Tag
    // postTags - имя ссылочной переменной в классе Post
    @OneToMany(mappedBy = "postTags")
    private List<Tag> tags;

    @Column
    private String createdOn;


    // relation with User
    // name= - внешний ключ
    // author - это связь, что в mappedBy of User class
    @ManyToOne
    @JoinColumn(name = "authorOfPosts")
    private User author;


    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String previewAttachment;

    @Column
    private String updatedOn;

//    // связь с Comment
//    @OneToMany(mappedBy = "postComment")
//    private List<Comment> comments;



}
