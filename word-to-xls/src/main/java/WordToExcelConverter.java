import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.*;

public class WordToExcelConverter {

    //private static String tempWordFilePath = "/Users/rudaks/temp/111.docx";

    private static String baseFilePath = "/Users/rudaks/temp/custom2docx";
    private static String baseOutputPath = "/Users/rudaks/temp/output";

    public static void main(String[] args) throws Exception {
        WordToExcelConverter wordToExcelConverter = new WordToExcelConverter();

        Collection<File> files = wordToExcelConverter.readSourceDirectory(baseFilePath, "docx");

        for (File file : files) {
            if (file.getName().startsWith("~")) // 임시파일
                continue;

            String fileName = file.getName();
            String siteName = wordToExcelConverter.getSiteNameInFilePath(file);
            String eerVersion = wordToExcelConverter.getEERVersion(siteName);
            String outputFileName = wordToExcelConverter.getOutputFilename(file);

            WordExtractor wordExtractor = new WordExtractor(wordToExcelConverter.mapTableHeaderToKey());
            List<HashMap<String, String>> dataList = wordExtractor.extractWordDocument(file.getAbsolutePath());

            ExcelWriter excelWriter = new ExcelWriter(siteName, getExcelHeaders(), dataList);
            excelWriter.write(baseOutputPath + "/" + siteName + "/" + outputFileName);

            DbWriter dbWriter = new DbWriter();
            dbWriter.execute(siteName, fileName, eerVersion, dataList);

            //if (true) return;
        }

        /*WordExtractor wordParser = new WordExtractor();
        List<HashMap<String, String>> dataList = wordParser.extractWordDocument(tempWordFilePath);

        ExcelWriter excelWriter = new ExcelWriter("customizing", getExcelHeaders(), dataList);
        excelWriter.write("/Users/rudaks/temp/output1.xls");*/
    }

    private Collection<File> readSourceDirectory(String filePath, String ... extensions) {
        return FileUtils.listFiles(new File(filePath), extensions, true);
    }

    private String getSiteNameInFilePath(File file) {
        String temp = file.getAbsolutePath().replaceAll(baseFilePath, ""); // /칸투칸_OnDemand_EER_1.7/trunk/doc/kantukan_501_커스터마이징내역서.docx
        String [] arTemp = temp.split("/");
        String siteName = arTemp[1];

        return siteName;
    }

    private String getEERVersion(String siteName) {
        String eerVersion = "_";
        if (siteName.lastIndexOf("_") > -1) {
            eerVersion = siteName.substring(siteName.lastIndexOf("_") + 1);

            try {
                String temp = eerVersion.replaceAll("\\.", "");
                Integer.parseInt(temp);
            } catch (Exception e) {
                eerVersion = "_";
            }
        }

        return eerVersion;
    }

    private static List<ExcelColumn> getExcelHeaders() {
        List<ExcelColumn> headerList = new ArrayList<ExcelColumn>();
        headerList.add(new ExcelColumn("customizeName", "커스터마이징 명칭", true, 0));
        headerList.add(new ExcelColumn("requirements", "요구사항", false, 2800*3));
        headerList.add(new ExcelColumn("javaChanges", "java 변경사항", false, 2800*3));
        headerList.add(new ExcelColumn("dbChanges", "DB 변경사항", false, 2800*3));
        headerList.add(new ExcelColumn("changedFiles", "변경 파일", false, 2800*3));
        headerList.add(new ExcelColumn("customizeType", "커스터마이징 타입", true, 0));
        headerList.add(new ExcelColumn("changedService", "변경 서비스", true, 0));
        headerList.add(new ExcelColumn("developer", "작업자", true, 0));
        headerList.add(new ExcelColumn("solution", "해결방안", true, 0));
        headerList.add(new ExcelColumn("cause", "이유", true, 0));

        return headerList;
    }

    private static Map<String, String> mapTableHeaderToKey() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("수정자", "developer");
        map.put("고객요구사항", "requirements");
        map.put("프로세스 설명", "requirements");
        map.put("상세 구현 내용", "javaChanges");
        map.put("패키지 경로", "changedFiles");
        map.put("테이블 변경", "dbChanges");

        return map;
    }

    private String getOutputFilename(File file) {
        return file.getName().substring(0, file.getName().lastIndexOf(".")) + ".xls";
    }

    private void printLog(String str) {
        System.out.println(StringUtils.repeat("#", 100));
        System.out.println(StringUtils.center(str, 100));
        System.out.println(StringUtils.repeat("#", 100));

    }
}
