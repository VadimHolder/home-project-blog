package com.itacademy.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tag")
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "name", unique = true)
    private String name;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

 /*//relation with Post
    // name= - внешний ключ
    // postTags - это связь, что в mappedBy of Post class
 @ManyToMany(mappedBy = "tags",fetch = FetchType.EAGER)
 private List<Post> posts = new ArrayList<>();*/
}
