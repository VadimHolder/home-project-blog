package com.itacademy.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;



    //relation with Post
    // name= - внешний ключ
    // postTags - это связь, что в mappedBy of Post class
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post postTags;
}
