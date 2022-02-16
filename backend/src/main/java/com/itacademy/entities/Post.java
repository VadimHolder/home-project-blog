package com.itacademy.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // связь с Tag
    // postTags - имя ссылочной переменной в классе Post
//    @OneToMany(mappedBy = "postTags")


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id")
    )
       private Set<Tag> tags = new HashSet<>();

    @Column
    private LocalDateTime createdOn;


    // relation with User
    // name= - внешний ключ
    // author - это связь, что в mappedBy of User class
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "authorOfPosts")
    private User author;


    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String previewAttachment;

    @Column
    private LocalDateTime updatedOn;

//    // связь с Comment
//    @OneToMany(mappedBy = "postComment")
//    private List<Comment> comments;



}
