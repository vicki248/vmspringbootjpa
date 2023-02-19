package com.juaracoding.vmspringbootjpa.controller;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 09/02/2023 20:29
@Last Modified 09/02/2023 20:29
Version 1.1
*/

import com.juaracoding.vmspringbootjpa.configuration.OtherConfig;
import com.juaracoding.vmspringbootjpa.dto.CategoryProductDTO;
import com.juaracoding.vmspringbootjpa.handler.ResourceNotFoundException;
import com.juaracoding.vmspringbootjpa.handler.ResponseHandler;
import com.juaracoding.vmspringbootjpa.model.CategoryProduct;
import com.juaracoding.vmspringbootjpa.service.CategoryProductService;
import com.juaracoding.vmspringbootjpa.utils.ConstantMessage;
import com.juaracoding.vmspringbootjpa.utils.CsvReader;
import com.juaracoding.vmspringbootjpa.utils.ExcelReader;
import com.juaracoding.vmspringbootjpa.utils.LoggingFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
@RestController
@RequestMapping("/api/mgmnt")
public class CategoryProductController {
    private CategoryProductService categoryProductService;
    private String [] strExceptionArr = new String[2];

    @Autowired
    private ModelMapper modelMapper;

    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    List<CategoryProduct> lsCPUpload = new ArrayList<CategoryProduct>();

    @Autowired
    public CategoryProductController(CategoryProductService categoryProductService) {
        strExceptionArr[0] = "CategoryProductController";
        this.categoryProductService = categoryProductService;
    }

    @PostMapping("/v1/s")
    public ResponseEntity<Object> saveCategory(@Valid
                                               @RequestBody CategoryProduct categoryProduct
    ){

        categoryProductService.saveDataCategory(categoryProduct);

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,null,null,null);
    }

    @PostMapping("/v1/sl")
    public ResponseEntity<Object> saveCategoryList(@Valid
                                                   @RequestBody List<CategoryProduct> listCategoryProduct
    ){

        categoryProductService.saveAllCategory(listCategoryProduct);

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);

    }
    @PostMapping("/v1/upl/batch")
    public ResponseEntity<Object> uploadCsvMaster(@Valid
                                                  @RequestParam("fileDemo")
                                                  @RequestHeader  MultipartFile multipartFile,
                                                  WebRequest request
    ) throws Exception {

        if(CsvReader.isCsv(multipartFile))
        {
            return categoryProductService.saveUploadFile(
                    csvToCategoryProduct(
                            multipartFile.getInputStream()),multipartFile,request);
        }
        else
        {
            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_NOT_CSV_FILE+" -- "+multipartFile.getOriginalFilename(),
                    HttpStatus.NOT_ACCEPTABLE,null,"FI01021",request);
        }

    }
    @PostMapping("/v1/uplxls/batch")
    public ResponseEntity<Object> uploadExcelMaster(@Valid
                                                    @RequestParam("fileDemo")
                                                    @RequestHeader  MultipartFile multipartFile,
                                                    WebRequest request
    ) throws Exception {

        if(ExcelReader.isExcel(multipartFile))
        {
            return categoryProductService.saveUploadFile(
                    excelToCategoryProduct(multipartFile.getInputStream()),
                    multipartFile,
                    request);
        }
        else
        {
            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_NOT_EXCEL_FILE+" -- "+multipartFile.getOriginalFilename(),
                    HttpStatus.NOT_ACCEPTABLE,null,"FI01021",request);
        }
    }

    @PutMapping("/v1/sl/{id}")
    public ResponseEntity<Object> updateCategoryById(@Valid
                                                     @RequestBody CategoryProduct categoryProduct,
                                                     @PathVariable Long id
    ) throws Exception {

        categoryProductService.updateCategory(categoryProduct,id);

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,null,null,null);
    }

    @GetMapping("/v1/f")
    public ResponseEntity<Object> findAll(){

        List<CategoryProduct> ls = categoryProductService.findAllCategory();

        if(ls.size()==0)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
                            HttpStatus.NOT_FOUND,
                            categoryProductService.findAllCategory(),
                            null,
                            null);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        ls,
                        null,
                        null);


    }

    /*
        PAGINATION SORTING DEFAULT by ID TANPA PARAMETER SORT BY YANG SPESIFIK DAN MASIH RETURN PAGE BUKAN LIST
        Problem :
        1. tidak dapat menggunakan validasi jika data kosong. artinya informasi hanya seadanya saja.
        2. Informasi paging masih berantakan, informasi yang tidak perlu juga masih dikembalikan
     */
    @GetMapping("/v1/fp1/{size}/{page}/{sort}")
    public ResponseEntity<Object> findAllPagination(
            @PathVariable("size") Integer sizez,
            @PathVariable("page") Integer pagez,
            @PathVariable("sort") String sortz
    ){

        Pageable pageable = null;
        if(sortz.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(pagez,sizez, Sort.by("idCategoryProduct").descending());
        }
        else
        {
            pageable = PageRequest.of(pagez,sizez);
        }
        Page<CategoryProduct> page = categoryProductService.findAllCategoryByPage(pageable);

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        page,
                        null,
                        null);
    }

    /*
        PAGINATION SORTING DEFAULT by ID TANPA PARAMETER SORT BY YANG SPESIFIK DAN RESPONSE SUDAH MENGGUNAKAN LIST
        Sudah ada validasi semisal data kosong dan informasi di response lebih dinamis
        Problem :
           1. Data content sudah lebih baik namun informasi Paging tidak diikutsertakan, sehingga menghambat front end nantinya
           2. belum menggunakan DTO data content untuk response masih belum rapih
     */
    @GetMapping("/v1/fp2/{size}/{page}/{sort}")
    public ResponseEntity<Object> findAllPagination2(
            @PathVariable("size") Integer sizez,
            @PathVariable("page") Integer pagez,
            @PathVariable("sort") String sortz
    ){

        Pageable pageable = null;
        if(sortz.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(pagez,sizez, Sort.by("idCategoryProduct").descending());
        }
        else
        {
            pageable = PageRequest.of(pagez,sizez);

        }
        Page<CategoryProduct> page = categoryProductService.findAllCategoryByPage(pageable);
        List<CategoryProduct> listCategoryProduct = page.getContent();
        if(listCategoryProduct.size()==0)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
                            HttpStatus.NOT_FOUND,
                            null,
                            null,
                            null);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        listCategoryProduct,
                        null,
                        null);
    }

    /*
       PAGINATION DENGAN SORT BY DAN RESPONSE SUDAH MENGGUNAKAN LIST NAMUN VALIDASI SORT BY MASIH DI EMBED DI METHOD REQUEST
       Problem :
       1. Data content sudah lebih baik namun Data Paging tidak diikutsertakan, sehingga menghambat front end nantinya
       2. belum menggunakan DTO data content untuk response masih belum rapih
    */
    @GetMapping("/v1/fpsb1/{size}/{page}/{sort}/{sortby}")
    public ResponseEntity<Object> findPaginationSortBy1(
            @PathVariable("size") Integer sizez,
            @PathVariable("page") Integer pagez,
            @PathVariable("sort") String sortz,
            @PathVariable("sortby") String sortzBy
    ){

        Pageable pageable = null;
        String strSortBy = "";

        if(sortzBy.equalsIgnoreCase("id"))
        {
            strSortBy = "idCategoryProduct";
        }
        else if(sortzBy.equalsIgnoreCase("name"))
        {
            strSortBy = "nameCategoryProduct";
        }
        else if(sortzBy.equalsIgnoreCase("description"))
        {
            strSortBy = "strDescCategoryProduct";
        }
        else
        {
            strSortBy = "idCategoryProduct";
        }

        if(sortz.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(pagez,sizez, Sort.by(strSortBy).descending());
        }
        else
        {
            pageable = PageRequest.of(pagez,sizez, Sort.by(strSortBy).ascending());
        }

        Page<CategoryProduct> page = categoryProductService.findAllCategoryByPage(pageable);
        List<CategoryProduct> listCategoryProduct = page.getContent();

        if(listCategoryProduct.size()==0)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
                            HttpStatus.NOT_FOUND,
                            categoryProductService.findAllCategory(),
                            null,
                            null);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        listCategoryProduct,
                        null,
                        null);
    }

    /*
       PAGINATION DENGAN SORT BY DAN RESPONSE SUDAH MENGGUNAKAN LIST SERTA VALIDASI SUDAH DIBUAT METHOD SENDIRI
       Problem :
       1. belum menggunakan DTO data content untuk response masih belum rapih
    */
    @GetMapping("/v1/fpsb2/{size}/{page}/{sort}/{sortby}")
    public ResponseEntity<Object> findPaginationSortBy2(
            @PathVariable("size") Integer sizez,
            @PathVariable("page") Integer pagez,
            @PathVariable("sort") String sortz,
            @PathVariable("sortby") String sortzBy
    ){

        Pageable pageable = filterPagination(pagez,sizez,sortz,sortzBy);
        Page<CategoryProduct> page = categoryProductService.findAllCategoryByPage(pageable);
        List<CategoryProduct> listCategoryProduct = page.getContent();

        if(listCategoryProduct.size()==0)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
                            HttpStatus.NOT_FOUND,
                            categoryProductService.findAllCategory(),
                            null,
                            null);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        listCategoryProduct,
                        null,
                        null);
    }

    /*
       PAGINATION DENGAN SORT BY DAN RESPONSE SUDAH MENGGUNAKAN LIST SERTA VALIDASI SUDAH DIBUAT METHOD SENDIRI + DTO di embed di method request
       Problem :
       1. kurang rapih karena DTO nya masih diembed di method request
    */
    @GetMapping("/v1/fpsbd1/{size}/{page}/{sort}/{sortby}")
    public ResponseEntity<Object> findPaginationSortByDTO1(
            @PathVariable("size") Integer sizez,
            @PathVariable("page") Integer pagez,
            @PathVariable("sort") String sortz,
            @PathVariable("sortby") String sortzBy
    ){

        Pageable pageable = filterPagination(pagez,sizez,sortz,sortzBy);
        Page<CategoryProduct> page = categoryProductService.findAllCategoryByPage(pageable);
        List<CategoryProduct> listCategoryProduct = page.getContent();

        /*
            CATATAN PENTING!!
            VALIDASI INI DILETAKKAN DISINI AGAR TIDAK PERLU MELAKUKAN PROSES APAPUN (PROSES MAPPING ATAU TRANSFORM DTO) JIKA DATA YANG DICARI KOSONG
         */
        if(listCategoryProduct.size()==0)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
                            HttpStatus.NOT_FOUND,
                            null,
                            null,
                            null);
        }

        List<CategoryProductDTO> listCategoryProductDTO = modelMapper.map(listCategoryProduct, new TypeToken<List<CategoryProductDTO>>() {}.getType());

//        CategoryProduct categoryProduct = new CategoryProduct();
//        CategoryProductDTO categoryProductDTO = modelMapper.map(categoryProduct, new TypeToken<CategoryProductDTO>() {}.getType());
        objectMapper.put("data",listCategoryProductDTO);
        objectMapper.put("currentPage",page.getNumber());
        objectMapper.put("totalItems",page.getTotalElements());
        objectMapper.put("totalPages",page.getTotalPages());
        objectMapper.put("sort",page.getSort());
        objectMapper.put("numberOfElements",page.getNumberOfElements());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        objectMapper,
                        null,
                        null);
    }

    /*
       PAGINATION DENGAN SORT BY DAN RESPONSE SUDAH MENGGUNAKAN LIST SERTA VALIDASI SUDAH DIBUAT METHOD SENDIRI + DTO sudah dibuat method sendiri
    */
    @GetMapping("/v1/fpsbd2/{size}/{page}/{sort}/{sortby}")
    public ResponseEntity<Object> findPaginationSortByDTO2(
            @PathVariable("size") Integer sizez,
            @PathVariable("page") Integer pagez,
            @PathVariable("sort") String sortz,
            @PathVariable("sortby") String sortzBy
    ){

        Pageable pageable = filterPagination(pagez,sizez,sortz,sortzBy);
        Page<CategoryProduct> page = categoryProductService.findAllCategoryByPage(pageable);
        List<CategoryProduct> listCategoryProduct = page.getContent();

        /*
            CATATAN PENTING!!
            VALIDASI INI DILETAKKAN DISINI AGAR TIDAK PERLU MELAKUKAN PROSES APAPUN (PROSES MAPPING ATAU TRANSFORM DTO) JIKA DATA YANG DICARI KOSONG
         */
        if(listCategoryProduct.size()==0)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.WARNING_DATA_EMPTY,
                            HttpStatus.NOT_FOUND,
                            null,
                            null,
                            null);
        }

        List<CategoryProductDTO> listCategoryProductDTO = modelMapper.map(listCategoryProduct, new TypeToken<List<CategoryProductDTO>>() {}.getType());
        Map<String, Object> mapResult = transformObject(objectMapper,listCategoryProductDTO,page);

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        mapResult,
                        null,
                        null);
    }

    private Map<String,Object> transformObject(Map<String,Object> mapz, List ls, Page page)
    {
        mapz.put("data",ls);
        mapz.put("currentPage",page.getNumber());
        mapz.put("totalItems",page.getTotalElements());
        mapz.put("totalPages",page.getTotalPages());
        mapz.put("sort",page.getSort());
        mapz.put("numberOfElements",page.getNumberOfElements());

        return mapz;
    }

    private Pageable filterPagination(Integer page, Integer size, String sorts , String sortsBy)
    {
        Pageable pageable;
        String strSortBy = "";

        if(sortsBy.equalsIgnoreCase("id"))
        {
            strSortBy = "idCategoryProduct";
        }
        else if(sortsBy.equalsIgnoreCase("name"))
        {
            strSortBy = "nameCategoryProduct";
        }
        else if(sortsBy.equalsIgnoreCase("description"))
        {
            strSortBy = "strDescCategoryProduct";
        }
        else
        {
            strSortBy = "idCategoryProduct";
        }

        if(sorts.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(page,size, Sort.by(strSortBy).descending());
        }
        else
        {
            pageable = PageRequest.of(page,size, Sort.by(strSortBy).ascending());
        }

        return pageable;
    }

    private Pageable filterPaginationV2(Integer page, Integer size, String sorts , String sortsBy)
    {
        Pageable pageable;
        String strSortBy = "";

        if(sortsBy.equalsIgnoreCase("id"))
        {
            strSortBy = "idCategoryProduct";
        }
        else if(sortsBy.equalsIgnoreCase("name"))
        {
            strSortBy = "nameCategoryProduct";
        }
        else if(sortsBy.equalsIgnoreCase("description"))
        {
            strSortBy = "strDescCategoryProduct";
        }
        else
        {
            strSortBy = "idCategoryProduct";
        }

        if(sorts.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(page,size, Sort.by(strSortBy).descending());
        }
        else
        {
            pageable = PageRequest.of(page,size, Sort.by(strSortBy).ascending());
        }

        return pageable;
    }

    public List<CategoryProduct> csvToCategoryProduct(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase()
        );
//        lsCPUpload = new ArrayList<CategoryProduct>();
        lsCPUpload.clear();
        int intCatchErrorRecord = 1;
        try {
            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                CategoryProduct cProducts = new CategoryProduct();
                cProducts.setNameCategoryProduct(record.get("NameCategoryProduct"));
                cProducts.setStrDescCategoryProduct(record.get("DescCategoryProduct"));
                cProducts.setCreatedBy(Integer.parseInt(record.get("CreatedBy")));
                lsCPUpload.add(cProducts);
                intCatchErrorRecord++;
            }

        } catch (Exception ex) {
            strExceptionArr[1]="csvToCategoryProduct(InputStream inputStream) --- LINE 530"+ex.getMessage()+" Error Record at Line "+intCatchErrorRecord;
            LoggingFile.exceptionStringz(strExceptionArr,ex, OtherConfig.getFlagLogging());
            throw new Exception(ex.getMessage()+" Error Record at Line "+intCatchErrorRecord);
        }
        finally {
            if (!csvParser.isClosed()) {
                csvParser.close();
            }
        }
        return lsCPUpload;
    }

    public List<CategoryProduct> excelToCategoryProduct(InputStream inputStream) throws Exception {
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("page-1");
//        Sheet sheetOne = workbook.getSheet("page-1");
//        Sheet sheetTwo = workbook.getSheet("page-2");
        Iterator<Row> rows = sheet.iterator();

        lsCPUpload.clear();
        int intCatchErrorRecord = 0;
        int intNextCell = 0;

        try {

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                Iterator<Cell> cellsInRow = currentRow.iterator();

                if(intCatchErrorRecord!=0){//MENGABAIKAN HEADER
                    intNextCell = 0;

                    CategoryProduct cProducts = new CategoryProduct();

                    while (cellsInRow.hasNext()) {
                        Cell currentCell = cellsInRow.next();
                        /*
                            URUTAN nya harus dimapping sesuai dengan cell di file excel nya !! dimulai dari index 0
                         */
                        if(intNextCell==0)
                        {
                            cProducts.setNameCategoryProduct(currentCell.getStringCellValue());
                        }
                        else if(intNextCell==1)
                        {
                            cProducts.setStrDescCategoryProduct(currentCell.getStringCellValue());
                        }
                        else
                        {
                            cProducts.setCreatedBy((int) currentCell.getNumericCellValue());
                        }

                        intNextCell++;
                    }
                    lsCPUpload.add(cProducts);
                }
                intCatchErrorRecord++;
            }

        } catch (Exception ex) {
            strExceptionArr[1]="excelToCategoryProduct(InputStream inputStream) ---LINE 589";
//            System.out.println(WhatsappConfig.getAuth());
            LoggingFile.exceptionStringz(strExceptionArr,new ResourceNotFoundException(ex.getMessage()+" Error Record at Line "+intCatchErrorRecord), OtherConfig.getFlagLogging());
            throw new Exception(ex.getMessage()+" Error Record at Line "+intCatchErrorRecord);
        }
        finally
        {
            workbook.close();
        }
        return lsCPUpload;
    }
}
