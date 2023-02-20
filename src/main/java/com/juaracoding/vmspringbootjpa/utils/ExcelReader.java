package com.juaracoding.vmspringbootjpa.utils;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 18/02/2023 10:40
@Last Modified 18/02/2023 10:40
Version 1.1
*/

import org.springframework.web.multipart.MultipartFile;
public class ExcelReader {
    public static boolean isExcel(MultipartFile multipartFile)
    {
        if(!ConstantMessage.CONTENT_TYPE_XLS.equals(multipartFile.getContentType()) && !ConstantMessage.CONTENT_TYPE_XLSX.equals(multipartFile.getContentType()))
        {
            return false;
        }
        return true;


//        if(ConstantMessage.CONTENT_TYPE_XLS.equals(multipartFile.getContentType()) || ConstantMessage.CONTENT_TYPE_XLSX.equals(multipartFile.getContentType()))
//        {
//            return true;
//        }
//        return false;
    }
}
