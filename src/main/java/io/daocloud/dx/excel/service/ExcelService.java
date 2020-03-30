package io.daocloud.dx.excel.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Devin
 * @Date: 2020-03-30 14:33
 * @Version 1.0
 */
@Service
public interface ExcelService {
    /**
     * 导出模版
     * @param response
     */
    void exportTemplate(HttpServletResponse response);

    /**
     * 导入excel
     */
    void importExcel(MultipartFile file, HttpServletRequest request);


    Workbook getWorkBook(InputStream inputStream,String fileName) throws IOException;
}
