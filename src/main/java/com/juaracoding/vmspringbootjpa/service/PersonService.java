package com.juaracoding.vmspringbootjpa.service;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 18/02/2023 08:33
@Last Modified 18/02/2023 08:33
Version 1.1
*/

import com.juaracoding.vmspringbootjpa.model.Person;
import com.juaracoding.vmspringbootjpa.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

@Service
@Transactional
public class PersonService {
    private PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public void savePerson(Person person){

        List<Person> l = new ArrayList<>();
        List<Person> ll = new LinkedList<>();
        List<Person> lll = new Vector<>();

        Pageable p = null;
        personRepo.save(person);
        personRepo.findAll();
        personRepo.findByFirstName(person.getFirstName());

    }

    public List<Person> findByName(Person person){
        return personRepo.findByFirstName(person.getFirstName());

    }
}
