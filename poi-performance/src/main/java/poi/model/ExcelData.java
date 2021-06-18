package poi.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExcelData {

    private List<ExcelHeader> headers;

    private List<Map<String, Object>> rows;
}
