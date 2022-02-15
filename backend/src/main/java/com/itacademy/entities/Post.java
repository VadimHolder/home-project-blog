package com.itacademy.entities;

import lombok.*;
import javax.persistence.*;
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

    @ToString.Exclude
    @ManyToMany(targetEntity = Tag.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id")
    )
       private Set<Tag> tags;

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
