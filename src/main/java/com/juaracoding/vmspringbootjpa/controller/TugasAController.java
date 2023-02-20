package com.juaracoding.vmspringbootjpa.controller;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 19/02/2023 13:55
@Last Modified 19/02/2023 13:55
Version 1.1
*/

import com.juaracoding.vmspringbootjpa.model.TugasA;
import com.juaracoding.vmspringbootjpa.service.TugasAService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/praktikum1/")
public class TugasAController {
    private TugasAService tugasAService;

    private String[] strExcep = new String[2];

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TugasAController(TugasAService tugasAService) {
        this.tugasAService = tugasAService;
    }

    @PostMapping("v1/sv")
    public ResponseEntity<Object> saveProvinsi(@Valid
                                               @RequestBody TugasA tugasA
    )
    {
        return tugasAService.saveTugasA(tugasA);

    }
}
