package poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import poi.model.DataType;
import poi.model.ExcelData;
import poi.model.ExcelHeader;

/**
 * HSSF : EXCEL 2007 이전 버전(.xls)에서 사용하는 방식
 * XSSF : EXCEL 2007 이후 버전(2007포함 .xlsx)에서 사용하는 방식
 * SXSSF : XSSF의 Streaming Version으로 메모리를 적게 사용하여 대용량 엑셀 다운로드에 주로 사용되는 방식
 */
public class Example {
    private static List<ExcelHeader> headers;

    private static List<Map<String, Object>> rows;

    private static int columnSize = 20;

    private static int rowSize = 100000;

    private static String messageSample = "";

    public static void main(String[] args) throws IOException {

        String saveFile = "/Users/rudaks/temp/sxssf_excel.xlsx";

        for (int i=0; i<200; i++) {
            messageSample += "샘플정보 ";
            //messageSample += "data ";
        }

        ExcelExecutor excelExecutor = new SxssfExecutor();
        addHeader();
        addRows();
        long currTime = System.currentTimeMillis();
        addSheet(excelExecutor);
        saveToExcel(excelExecutor, saveFile);
        long elapsedTime = System.currentTimeMillis() - currTime;

        System.out.println("[sxssf] " + elapsedTime + "(ms)");



        /*currTime = System.currentTimeMillis();
        ExcelExecutor excelExecutor2 = new XssfExecutor();
        addHeader();
        addRows();
        addSheet(excelExecutor2);
        saveToExcel(excelExecutor2, "xssf_excel.xlsx");
        elapsedTime = System.currentTimeMillis() - currTime;

        System.out.println("[xssf] " + elapsedTime + "(ms)");*/
    }

    public static void addHeader() {
        headers = new ArrayList<>();

        for (int i=0; i<columnSize; i++) {
            headers.add(new ExcelHeader("col" + i, "col" + i, CellStyle.ALIGN_LEFT, DataType.STRING));
        }
    }

    public static void addRows() {
        rows = new ArrayList<>();

        String data = "엑셀 샘플 데이타1";
        //String data = "sampledata";

        for (int i = 0; i < rowSize; i++) {

            Map<String, Object> row = new HashMap<>();
            for (int k=0; k<columnSize; k++) {
                if (k == columnSize -1) {
                    row.put("col" + k, messageSample);
                } else {
                    row.put("col" + k, data + " > " + k);
                }
            }
            rows.add(row);
        }
    }

    public static void addSheet(ExcelExecutor excelExecutor) {
        ExcelData excelData = new ExcelData(headers, rows);
        excelExecutor.addSheet("test", excelData);
    }

    public static void saveToExcel(ExcelExecutor excelExecutor, String filepath) {
        try {
            Workbook workbook = excelExecutor.getWorkbook();
            FileOutputStream fos = new FileOutputStream(filepath);
            workbook.write(fos);
            fos.close();

            System.out.println("[Saved to file] " + filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

