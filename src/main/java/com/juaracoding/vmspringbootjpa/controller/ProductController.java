package com.juaracoding.vmspringbootjpa.controller;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 19/02/2023 10:10
@Last Modified 19/02/2023 10:10
Version 1.1
*/

import com.juaracoding.vmspringbootjpa.model.Product;
import com.juaracoding.vmspringbootjpa.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/ops/")
public class ProductController {
    private ProductService productService;

    private String[] strExcep = new String[2];

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("v1/sv")
    public ResponseEntity<Object> saveProduct(@Valid
                                              @RequestBody Product product
    )
    {
        return productService.saveProduct(product);

    }
}
