package test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriterDemo {
   /* public void write(List<ExcelEntity> list) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell;

        cell = row.createCell(0);
        cell.setCellValue("커스터마이징 명칭");

        cell = row.createCell(1);
        cell.setCellValue("요구사항");

        cell = row.createCell(2);
        cell.setCellValue("java 변경사항");

        cell = row.createCell(3);
        cell.setCellValue("DB 변경사항");

        cell = row.createCell(4);
        cell.setCellValue("커스터마이징 타입");

        cell = row.createCell(5);
        cell.setCellValue("변경 서비스");

        cell = row.createCell(6);
        cell.setCellValue("작업자");

        cell = row.createCell(7);
        cell.setCellValue("해결방안");

        cell = row.createCell(8);
        cell.setCellValue("이유");

        for (int i=0; i<list.size(); i++) {
            ExcelEntity excelEntity = list.get(i);

            row = sheet.createRow(i+1);

            cell = row.createCell(0);
            cell.setCellValue(excelEntity.getName());

            cell = row.createCell(1);
            cell.setCellValue(excelEntity.getRequirement());

            cell = row.createCell(2);
            cell.setCellValue(excelEntity.getJavaChanges());

            cell = row.createCell(3);
            cell.setCellValue(excelEntity.getDbChanges());

            cell = row.createCell(4);
            cell.setCellValue(excelEntity.getCustomizedType());

            cell = row.createCell(5);
            cell.setCellValue(excelEntity.getChangedService());

            cell = row.createCell(6);
            cell.setCellValue(excelEntity.getWriter());

            cell = row.createCell(7);
            cell.setCellValue(excelEntity.getSolution());

            cell = row.createCell(8);
            cell.setCellValue(excelEntity.getCause());

        }

        // 입력된 내용 파일로 쓰기
        File file = new File("/Users/rudaks/temp/output.xls");
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook!=null) workbook.close();
                if(fos!=null) fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/


}
