package com.itacademy.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // связь с Tag
    // postTags - имя ссылочной переменной в классе Post
//    @OneToMany(mappedBy = "postTags")


    @ManyToMany(targetEntity = Tag.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id")
    )
       private Set<Tag> tags;

    @Column
    private LocalDateTime createdOn;


    // relation with User
    // name= - внешний ключ
    // author - это связь, что в mappedBy of User class
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User author;


    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String previewAttachment;

    @Column
    private LocalDateTime updatedOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post that = (Post) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
