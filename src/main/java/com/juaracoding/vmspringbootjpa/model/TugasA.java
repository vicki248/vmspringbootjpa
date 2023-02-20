package com.juaracoding.vmspringbootjpa.model;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 19/02/2023 13:05
@Last Modified 19/02/2023 13:05
Version 1.1
*/

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MstTugasA")
public class TugasA {
    @Id
    @Column(name = "IDTugasA" ,length = 255 , nullable = false)
    private String idTugasA = "ID-"+System.currentTimeMillis();

    @NotEmpty
    @NotNull
    @Column(name = "Nama" , length = 40)
    private String nama;

    @NotEmpty
    @NotNull
    @Column(name = "Alamat" , length = 500)
    private String alamat;

    @NotNull
    @Column(name = "TanggalLahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;

    @NotNull
    @Column(name = "JenisKelamin", length = 1)
    private Character jenisKelamin;

    @NotNull
    @Column(name = "MasihHidup")
    private Boolean masihHidup;

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

    public String getIdTugasA() {
        return idTugasA;
    }

    public void setIdTugasA(String idTugasA) {
        this.idTugasA = idTugasA;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Character getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Character jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Boolean getMasihHidup() {
        return masihHidup;
    }

    public void setMasihHidup(Boolean masihHidup) {
        this.masihHidup = masihHidup;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
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
