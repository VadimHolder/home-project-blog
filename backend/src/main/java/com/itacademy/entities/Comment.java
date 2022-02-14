package com.itacademy.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

 /*   @Column
    private String author;
*/
    @Column(nullable = false)
    private String text;

    @Column
    private Timestamp createdOn;

    @Column
    private Timestamp updatedOn;

    // relation with User
    // name - внешний ключ
    // user - это то, что в mappedBy of User class
    @ManyToOne
    @JoinColumn(name = "authorOfComments")
    private User userComment;

    //relation with Post
    // name - внешний ключ
    // user - это то, что в mappedBy of User class
//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post postComment;

}
