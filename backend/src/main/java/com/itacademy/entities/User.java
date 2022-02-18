package com.itacademy.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String password;


 /*
// variant for Role
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    */

    @Column(name = "role", nullable = false)
    private String role;


/*

    // связь с Post
    // user - имя ссылочной переменной в классе Post
    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    // связь с Comment
    @OneToMany(mappedBy = "userComment")
    private List<Comment> comments;

*/



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User that = (User) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
