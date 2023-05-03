package com.project.taskmanagerapp.POJO;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer userId;

    @Column(name="name")
    private String name;

    @Column(name="contactNumber")
    private String contactNumber;

    @Column(name = "email")
    private  String email;

    @Column(name = "password")
    private String password;

    @Column(name="status")
    private String status;

    @Column(name = "role")
    private String role;

     @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Set<Task> tasks;


}
