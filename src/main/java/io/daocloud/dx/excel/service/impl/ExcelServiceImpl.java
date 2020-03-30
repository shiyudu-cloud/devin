package io.daocloud.dx.excel.service.impl;

import io.daocloud.dx.excel.constants.ExcelConstants;
import io.daocloud.dx.excel.service.ExcelService;
import io.daocloud.dx.excel.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Author: Devin
 * @Date: 2020-03-30 14:37
 * @Version 1.0
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Autowired
    private ExcelUtils excelUtils;


    @Override
    public void exportTemplate(HttpServletResponse response) {
        ServletOutputStream out = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            out = response.getOutputStream();
            String fileName = new String((ExcelConstants.TEMPLATE_FILE_NAME).getBytes(), "UTF-8");
            XSSFWorkbook xssfWorkbook = excelUtils.exportTemplate(ExcelConstants.SHEET_NAME, new String[]{"用户账号", "用户昵称", "主邮箱"});
            this.download(response,fileName);
            bufferedOutputStream = new BufferedOutputStream(out);
            bufferedOutputStream.flush();
            xssfWorkbook.write(bufferedOutputStream);
        } catch (Exception e) {
            LOGGER.error("导出操作日志部分信息失败=======> 原因:{} ",e.getMessage());
        }finally {
            this.close(out,bufferedOutputStream);
        }

    }

    @Override
    public void importExcel(MultipartFile file, HttpServletRequest request) {

    }

    @Override
    public Workbook getWorkBook(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (ExcelConstants.EXCEL_2003L.equals(fileType)) {
            // 2003-
            workbook = new HSSFWorkbook(inputStream);
        } else if (ExcelConstants.EXCEL_2007U.equals(fileType)) {
            // 2007+
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 下载
     * @param response
     * @param fileName
     */
    private void download(HttpServletResponse response,String fileName) throws UnsupportedEncodingException {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
    }
    /**
     * 关闭
     * @param out
     */
    private void close(ServletOutputStream out,BufferedOutputStream bufferedOutputStream){
        if(bufferedOutputStream != null){
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                LOGGER.info("关闭【BufferedOutputStream】失败 {}",e.getMessage());
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (Exception e) {
                LOGGER.info("关闭【ServletOutputStream】失败 {}",e.getMessage());
            }
        }
    }
}
