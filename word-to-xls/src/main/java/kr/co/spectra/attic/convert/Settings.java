package kr.co.spectra.attic.convert;

import java.util.List;

public class Settings {
    private String inputFilePath;
    private String inputFileExtension;
    private String outputFilePath;

    private boolean writeToExcel;
    private boolean writeToDb;
    private boolean compareFileExistWithSvn;

    private String svnPath;

    private List<ExcelColumn> excelColumns;

    private String jdbcDriver;
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;

    public boolean isWriteToExcel() {
        return writeToExcel;
    }

    public void setWriteToExcel(boolean writeToExcel) {
        this.writeToExcel = writeToExcel;
    }

    public boolean isWriteToDb() {
        return writeToDb;
    }

    public void setWriteToDb(boolean writeToDb) {
        this.writeToDb = writeToDb;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getInputFileExtension() {
        return inputFileExtension;
    }

    public void setInputFileExtension(String inputFileExtension) {
        this.inputFileExtension = inputFileExtension;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public List<ExcelColumn> getExcelColumns() {
        return excelColumns;
    }

    public void setExcelColumns(List<ExcelColumn> excelColumns) {
        this.excelColumns = excelColumns;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getSvnPath() {
        return svnPath;
    }

    public void setSvnPath(String svnPath) {
        this.svnPath = svnPath;
    }

    public boolean isCompareFileExistWithSvn() {
        return compareFileExistWithSvn;
    }

    public void setCompareFileExistWithSvn(boolean compareFileExistWithSvn) {
        this.compareFileExistWithSvn = compareFileExistWithSvn;
    }
}
