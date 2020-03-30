package io.daocloud.dx.excel.web;

import io.daocloud.dx.excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Devin
 * @Date: 2020-03-30 15:15
 * @Version 1.0
 */
@RestController
public class ExcelApi {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/excel")
    public void exportTemplate(HttpServletResponse response){
        excelService.exportTemplate(response);
    }

    @PostMapping("/excel")
    public void importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){

    }
}
