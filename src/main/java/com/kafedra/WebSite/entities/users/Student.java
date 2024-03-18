package com.kafedra.WebSite.entities.users;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    private Long student_id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "student_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "team")
    private String group;

    @Column(name = "course")
    private String course;


}
