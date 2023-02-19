package com.juaracoding.vmspringbootjpa.repo;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 09/02/2023 20:30
@Last Modified 09/02/2023 20:30
Version 1.1
*/

import com.juaracoding.vmspringbootjpa.model.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepo extends JpaRepository<CategoryProduct,Long> {
    //    List<CategoryProduct> findByNameCategoryProduct(String value);

    /*
        findByNameCategoryProduct
        SELECT * FROM MstCategoryProduct WHERE NameCategoryProduct = ?
    */
}
