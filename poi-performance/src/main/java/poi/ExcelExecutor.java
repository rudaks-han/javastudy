package poi;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Workbook;
import poi.model.ExcelData;

@Getter
public abstract class ExcelExecutor {

    public Workbook workbook;

    public abstract void addSheet(String sheetName, ExcelData excelData);
}
