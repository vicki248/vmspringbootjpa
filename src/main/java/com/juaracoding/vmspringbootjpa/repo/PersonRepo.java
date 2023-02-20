package com.juaracoding.vmspringbootjpa.repo;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 18/02/2023 08:29
@Last Modified 18/02/2023 08:29
Version 1.1
*/

import com.juaracoding.vmspringbootjpa.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepo extends CrudRepository<Person,Long> {
    List<Person> findByFirstName(String val);
}
