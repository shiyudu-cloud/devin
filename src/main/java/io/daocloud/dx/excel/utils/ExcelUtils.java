package io.daocloud.dx.excel.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;


/**
 * Excel工具类
 *
 * @author Devin
 */
@Component
public class ExcelUtils {

    /**
     * 导出模版
     *
     * @param sheetName
     * @return
     * @throws Exception
     */
    public XSSFWorkbook exportTemplate(String sheetName, String[] titles) {
        return createExcelFile(sheetName, titles);
    }

    /**
     * 已认证数据生成EXCEL
     *
     * @param sheetName
     * @return
     * @throws Exception
     */
    public XSSFWorkbook createExcelFile(String sheetName, String[] titles) {
        // 创建新的Excel工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        style(workbook);
        // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
        XSSFSheet sheet = workbook.createSheet(sheetName);
        XSSFRow row = sheet.createRow(0);
        //增加Excel标题
        title(row, titles);
        return workbook;

    }

    /**
     * 设置Excel风格
     * @param workbook
     */
    private void style(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);
    }

    private void title(XSSFRow row, String[] titles) {
        XSSFCell cell;
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }
    }
}