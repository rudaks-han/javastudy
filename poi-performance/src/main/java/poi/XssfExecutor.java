package poi;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import poi.model.ExcelData;
import poi.model.ExcelHeader;

@Getter
public class XssfExecutor extends ExcelExecutor {

    private XSSFWorkbook workbook;

    public XssfExecutor() {
        workbook = new XSSFWorkbook();
    }

    public void addSheet(String sheetName, ExcelData excelData) {
        Sheet sheet = workbook.createSheet(sheetName);

        Row sxssfRow; // 행
        Cell sxssfCell; // 셀

        List<ExcelHeader> excelHeaders = excelData.getHeaders();

        int rowIndex = 0;
        sxssfRow = sheet.createRow((short) rowIndex);
        sxssfRow.setHeight((short) 1024);

        // header
        for (int i = 0; i < excelHeaders.size(); i++) {
            sxssfCell = sxssfRow.createCell(i);
            sxssfCell.setCellValue(excelHeaders.get(i).getHeaderName());
        }

        // data
        List<Map<String, Object>> rows = excelData.getRows();
        if (rows != null && rows.size() > 0) {

            for (Map<String, Object> row : rows) {
                rowIndex++;

                sxssfRow = sheet.createRow(rowIndex);

                for (int i = 0; i < excelHeaders.size(); i++) {
                    ExcelHeader excelHeader = excelHeaders.get(i);

                    Object value = row.get(excelHeader.getColumnName());
                    value = value == null ? "" : value;

                    sxssfCell = sxssfRow.createCell(i);

                    switch (excelHeader.getDataType()) {
                        case NUMBER:
                            sxssfCell.setCellValue(Long.parseLong(value + ""));
                            break;
                        default:
                            sxssfCell.setCellValue(value + "");
                    }
                }
            }
        }
    }
}
