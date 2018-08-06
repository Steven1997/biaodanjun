package cn.habitdiary.form.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel工具类
 */
public class ExcelUtils {


    /**
     * 新建Excel文档
     * @param title 题头
     * @param filepath 文件路径
     * @param formHead 表头项集合
     */
    public static void createExcel(String title,String filepath,String[] formHead){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //新建工作表
        HSSFSheet sheet = workbook.createSheet();
        //新建题头
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellType(HSSFCell.CELL_TYPE_STRING);
        //设置字体
        HSSFFont hssfFont = workbook.createFont();
        //字体大小
        hssfFont.setFontHeightInPoints((short)11);
        //粗体
        hssfFont.setBold(true);
        //字体样式
        hssfFont.setFontName("微软雅黑");
        //表头单元格样式
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        titleStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setFont(hssfFont);
        titleCell.setCellStyle(titleStyle);
        if (formHead.length > 1) {
            //列数多于1列，合并题头单元格
            CellRangeAddress region = new CellRangeAddress(0, 0, 0, formHead.length - 1);
            sheet.addMergedRegion(region);
        }

        HSSFRow head = sheet.createRow(1);
        //表头列数
        int colSize = formHead.length;
        for(int i = 0;i < colSize;i++) {
            HSSFCell cell = head.createCell(i);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(formHead[i]);
            HSSFCellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);//水平居中
            style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short)9);
            font.setColor(HSSFColor.WHITE.index);
            font.setBold(true);
            font.setFontName("微软雅黑");
            style.setFont(font);
            cell.setCellStyle(style);
        }
        //设置列宽度自适应
        for(int i = 0;i < formHead.length;i++){
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i,sheet.getColumnWidth(i) * 17 / 10);
        }
        //将新建的工作表保存到硬盘中
        try(FileOutputStream fos = new FileOutputStream(new File(filepath));){
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 向Excel文件中写入一行
     * @param filepath
     * @param items
     * @return
     */
    public static Integer fillExcel(String filepath,String[] items) {

        //创建输入流
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            fis = new FileInputStream(new File(filepath));
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            fos = new FileOutputStream(new File(filepath));
            //通过构造函数传参
            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            //获取工作表
            HSSFSheet sheet = workbook.getSheetAt(0);
            int newRowCount = sheet.getLastRowNum() + 1;
            HSSFRow newRow = sheet.createRow(newRowCount);
            int colSize = items.length;
            //设置字体
            HSSFFont hssfFont = workbook.createFont();
            //字体大小
            hssfFont.setFontHeightInPoints((short)9);
            for(int i = 0;i < colSize;i++) {
                HSSFCell cell = newRow.createCell(i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(items[i]);
                HSSFCellStyle style = workbook.createCellStyle();
                style.setAlignment(HorizontalAlignment.CENTER);//水平居中
                style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
                style.setFont(hssfFont);
                cell.setCellStyle(style);
            }
            //设置列宽度自适应
            for(int i = 0;i < items.length;i++){
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i,sheet.getColumnWidth(i) * 17 / 10);
            }
            workbook.write(fos);
            return Integer.valueOf(newRowCount);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fos != null && fis != null){
                try {
                    fos.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 读取表头项
     * @param filepath
     * @return 返回表头项列表
     */
    public static String[] getHeader(String filepath){
        FileInputStream fis = null;
        List<String> list = new ArrayList<>();
        try{
            fis = new FileInputStream(new File(filepath));
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            HSSFWorkbook workbook = new HSSFWorkbook(fs);
            //获取工作表
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow header = sheet.getRow(1);
            int colCnt = header.getLastCellNum();
            for(int i = 0;i < colCnt;i++) {
                list.add(header.getCell(i).getStringCellValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] listArray = list.toArray(new String[list.size()]);
        return listArray;
    }
}
