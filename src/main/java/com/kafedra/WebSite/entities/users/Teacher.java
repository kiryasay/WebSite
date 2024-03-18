package com.kafedra.WebSite.entities.users;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teachers")
@Data
public class Teacher {

    @Id
    private Long teacher_id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "teacher_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mail")
    private String mail;

    @Column(name = "telegram")
    private String telegram;

    @Column(name = "vkontakte")
    private String vkontakte;

    @Column(name = "description")
    private String description;
}

