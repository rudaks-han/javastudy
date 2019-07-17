import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbWriter {

    private Connection conn;
    private PreparedStatement pstmt;

    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");

        conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testdb", "test", "test");

        return conn;
    }

    public void execute(String siteName, String fileName, String eerVersion, List<HashMap<String, String>> dataList) {

        insertCustomize(siteName, fileName, eerVersion, dataList);

        insertCustomizeChangedFiles(siteName, dataList);

        System.out.println("[execute sql] " + siteName);
    }

    private void insertCustomize(String siteName, String fileName, String eerVersion, List<HashMap<String, String>> dataList) {
        try {
            conn = getConnection();
            String sql = "INSERT INTO t_customize_item(file_name, site_name, eer_version, customize_name, requirements, java_changes, db_changes, changed_files, customize_type, changed_service, developer, solution, cause) ";
            sql += "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ";

            if (dataList != null && dataList.size() > 0) {
                for (int i = 0; i < dataList.size(); i++) {
                    HashMap<String, String> map = dataList.get(i);

                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, fileName);
                    pstmt.setString(2, siteName);
                    pstmt.setString(3, eerVersion);
                    pstmt.setString(4, map.get("customizeName"));
                    pstmt.setString(5, map.get("requirements"));
                    pstmt.setString(6, map.get("javaChanges"));
                    pstmt.setString(7, map.get("dbChanges"));
                    pstmt.setString(8, map.get("changedFiles"));
                    pstmt.setString(9, map.get("customizeType"));
                    pstmt.setString(10, map.get("changedService"));
                    pstmt.setString(11, map.get("developer"));
                    pstmt.setString(12, map.get("solution"));
                    pstmt.setString(13, map.get("cause"));

                    pstmt.execute();
                    pstmt.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {

                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    private void insertCustomizeChangedFiles(String siteName, List<HashMap<String, String>> dataList) {
        try {
            conn = getConnection();
            String sql = "INSERT INTO t_customize_changed_file(site_name, customize_name, filepath, filename, ext) ";
            sql += "VALUES(?,?,?,?,?) ";

            if (dataList != null && dataList.size() > 0) {
                for (int i = 0; i < dataList.size(); i++) {
                    HashMap<String, String> map = dataList.get(i);

                    String changedFiles = map.get("changedFiles");
                    if (changedFiles != null) {
                        String[] arChangedFiles = changedFiles.split("\n");

                        for (int j = 0; j < arChangedFiles.length; j++) {

                            if (arChangedFiles[j].indexOf("/") > -1) {

                                String fileName = getFileName(arChangedFiles[j]);
                                String fileExt = FilenameUtils.getExtension(fileName);

                                pstmt = conn.prepareStatement(sql);
                                pstmt.setString(1, siteName);
                                pstmt.setString(2, map.get("customizeName"));
                                pstmt.setString(3, arChangedFiles[j]);
                                pstmt.setString(4, fileName);
                                pstmt.setString(5, fileExt);

                                pstmt.execute();
                                pstmt.close();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {

                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    private String getFileName(String filePath) {
        String fileName = "";
        if (filePath != null && filePath.length() > 0 && filePath.indexOf("/") > -1 && filePath.indexOf(".") > -1) {
            //String temp = filePath.substring(filePath.lastIndexOf("/")+1);

            //if (temp.indexOf(".") > -1) {
                fileName = getFileNameByRegExp(filePath);
            //}
        }

        return fileName;
    }

    private String getFileNameByRegExp(String value) {
        String filename = "";

        String regexp = "[a-zA-Z0-9-_.]*\\.[a-zA-Z]*";

        Pattern infoPattern = Pattern.compile(regexp);
        Matcher infoMatcher = infoPattern.matcher(value);
        while (infoMatcher.find()){
            filename = infoMatcher.group();
        }

        return filename;
    }
}
