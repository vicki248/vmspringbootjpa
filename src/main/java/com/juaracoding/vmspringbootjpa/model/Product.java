package com.juaracoding.vmspringbootjpa.model;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 18/02/2023 08:25
@Last Modified 18/02/2023 08:25
Version 1.1
*/

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MstProduct")
public class Product {
    private static final long serialversionUID = 1L;

    @Id
    @Column(name = "IDProduct")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @NotEmpty
    @NotNull
    @Column(name = "NameProduct")
    private String nameProduct;

    @ManyToOne
    @JoinColumn(name = "IDCategoryProduct")
    private CategoryProduct categoryProduct;

    @Column(name = "DescriptionProduct")
    private String descriptionProduct;

    /*
       start audit trails
    */
    @Column(name ="CreatedDate" , nullable = false)
    private Date createdDate = new Date();

    @Column(name = "CreatedBy", nullable = false)
    private Integer createdBy;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "IsDelete", nullable = false)
    private Byte isDelete = 1;
    /*
        end audit trails
     */

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}
