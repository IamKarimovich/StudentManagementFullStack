package com.ruslancodes.studentsystem.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "address")
    private String address;

    @Column(name = "student_no")
    private String studentNo;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;




}
