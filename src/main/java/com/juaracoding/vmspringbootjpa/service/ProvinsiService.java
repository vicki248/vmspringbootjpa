package com.juaracoding.vmspringbootjpa.service;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 19/02/2023 12:30
@Last Modified 19/02/2023 12:30
Version 1.1
*/

import com.juaracoding.vmspringbootjpa.configuration.OtherConfig;
import com.juaracoding.vmspringbootjpa.handler.ResourceNotFoundException;
import com.juaracoding.vmspringbootjpa.handler.ResponseHandler;
import com.juaracoding.vmspringbootjpa.model.Provinsi;
import com.juaracoding.vmspringbootjpa.repo.ProvinsiRepo;
import com.juaracoding.vmspringbootjpa.utils.ConstantMessage;
import com.juaracoding.vmspringbootjpa.utils.LoggingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ProvinsiService {
    private ProvinsiRepo provinsiRepo;
    private String [] strExceptionArr = new String[2];
    @Autowired
    public ProvinsiService(ProvinsiRepo provinsiRepo) {
        strExceptionArr[0] = "ProvinsiService";
        this.provinsiRepo = provinsiRepo;
    }

    public ResponseEntity<Object> saveProvinsi(Provinsi provinsi)
    {
        String strMessage = ConstantMessage.SUCCESS_SAVE;
        try
        {
            provinsiRepo.save(provinsi);
        }
        catch (Exception e)
        {
            strExceptionArr[1]="saveProvinsi(Provinsi provinsi)";
            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST,null,"FI02001",null);
        }

        return new ResponseHandler().generateResponse(strMessage,
                HttpStatus.CREATED,null,null,null);
    }

    public ResponseEntity<Object> uploadProvinsiBatch(List<Provinsi> provinsiList) {
        String strMessage = ConstantMessage.SUCCESS_SAVE;
        try {
            provinsiRepo.saveAll(provinsiList);
        } catch (Exception e) {
            strExceptionArr[1] = "uploadProvinsiBatch(List<Provinsi> provinsiList)";
            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST,null,"FI02001",null);
        }

        return new ResponseHandler().generateResponse(strMessage,
                HttpStatus.CREATED,null,null,null);
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public ResponseEntity<Object> saveUploadFile(List<Provinsi> listProvinsi,
                                                 MultipartFile multipartFile,
                                                 WebRequest request) throws ResourceNotFoundException {
        List<Provinsi> provinsiList = null;
        String strMessage = ConstantMessage.SUCCESS_SAVE;
        try
        {
            provinsiList = provinsiRepo.saveAll(listProvinsi);
            if(provinsiList.size()==0)
            {
                return new ResponseHandler().generateResponse(ConstantMessage.ERROR_EMPTY_FILE +" -- "+multipartFile.getOriginalFilename(),
                        HttpStatus.BAD_REQUEST,null,"FI01020",request);
            }
        }
        catch (Exception e)
        {
            strMessage = e.getMessage();
            strExceptionArr[1]="saveUploadFile(List<Provinsi> listProvinsi, MultipartFile multipartFile, WebRequest request) --- LINE 77";
            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateResponse(strMessage,
                    HttpStatus.BAD_REQUEST,null,"FI01021",request);
        }

        return new ResponseHandler().generateResponse(strMessage,
                HttpStatus.CREATED,null,null,request);
    }
}
