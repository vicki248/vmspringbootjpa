package com.juaracoding.vmspringbootjpa.service;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 19/02/2023 13:54
@Last Modified 19/02/2023 13:54
Version 1.1
*/

import com.juaracoding.vmspringbootjpa.configuration.OtherConfig;
import com.juaracoding.vmspringbootjpa.handler.ResponseHandler;
import com.juaracoding.vmspringbootjpa.model.TugasA;
import com.juaracoding.vmspringbootjpa.repo.TugasARepo;
import com.juaracoding.vmspringbootjpa.utils.ConstantMessage;
import com.juaracoding.vmspringbootjpa.utils.LoggingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TugasAService {
    private TugasARepo tugasARepo;
    private String [] strExceptionArr = new String[2];

    @Autowired
    public TugasAService(TugasARepo tugasARepo) {
        strExceptionArr[0] = "TugasAService";
        this.tugasARepo = tugasARepo;
    }

    public ResponseEntity<Object> saveTugasA(TugasA tugasA)
    {
        String strMessage = ConstantMessage.SUCCESS_SAVE;
        try
        {
            tugasARepo.save(tugasA);
        }
        catch (Exception e)
        {
            strExceptionArr[1]="saveTugasA(TugasA tugasA)";
            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST,null,"FI02001",null);
        }

        return new ResponseHandler().generateResponse(strMessage,
                HttpStatus.CREATED,null,null,null);
    }
}
