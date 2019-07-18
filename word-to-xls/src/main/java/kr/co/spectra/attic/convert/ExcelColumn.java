package kr.co.spectra.attic.convert;

public class ExcelColumn
{
    private String id;
    private String columnName;
    private boolean autoSize;
    private int addColumnWidth;
    private String mapToWordParagraph;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isAutoSize() {
        return autoSize;
    }

    public void setAutoSize(boolean autoSize) {
        this.autoSize = autoSize;
    }

    public int getAddColumnWidth() {
        return addColumnWidth;
    }

    public void setAddColumnWidth(int addColumnWidth) {
        this.addColumnWidth = addColumnWidth;
    }

    public String getMapToWordParagraph() {
        return mapToWordParagraph;
    }

    public void setMapToWordParagraph(String mapToWordParagraph) {
        this.mapToWordParagraph = mapToWordParagraph;
    }
}


