package dev.lesechko.hibercrud.model;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "writers")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany //TODO: это правильно?
    private List<Post> posts;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Writer() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    TODO: раньше был метод composeFulleName(). Я удалил. Это бизнес-логика?
//    public String composeFullName() {
//        String fullName = "";
//        if (lastName != null && !lastName.isEmpty())
//            fullName += lastName + ", ";
//        if (firstName != null && !firstName.isEmpty())
//            fullName += firstName;
//        return fullName;
//    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
