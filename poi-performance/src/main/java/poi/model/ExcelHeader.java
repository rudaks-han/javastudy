package poi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExcelHeader {
    private String columnName;
    private String headerName;

    private short columnAlign;

    private DataType dataType;
}
