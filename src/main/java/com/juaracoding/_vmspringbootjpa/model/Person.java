package com.juaracoding._vmspringbootjpa.model;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 09/02/2023 19:53
@Last Modified 09/02/2023 19:53
Version 1.1
*/

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "MstPerson")
public class Person {
    @Id
    @Column(name = "IDPerson")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FirstName", nullable = false, length = 15)
    private String firstName;

    @Column(name = "MiddleName", nullable = true, length = 15)
    private String middleName;

    @Column(name = "LastName", length = 15)
    private String lastName;

    @Column(name = "DayOfBirth")
    private LocalDate dayOfBirth;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Transient
    private Integer age;


    public Long getId() {
        return id;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }
}
