package kr.co.spectra.attic.convert;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang.StringUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordToExcelConverter {

    private Map<String, Map<String, EERFile>> eerFileMap;

    public static void main(String[] args) throws Exception {
        WordToExcelConverter wordToExcelConverter = new WordToExcelConverter();

        printLog("loading settings.yml");
        Settings settings = wordToExcelConverter.loadYaml("settings.yml");

        wordToExcelConverter.readSvnDirectory(settings);

        printLog("reading directory: " + settings.getInputFilePath());
        Collection<File> files = wordToExcelConverter.listSourceDirectory(settings);

        wordToExcelConverter.executeWordParse(settings, files);
    }

    private Settings loadYaml(String path) {
        Yaml yaml = new Yaml(new Constructor(Settings.class));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(path);
        Settings settings = yaml.load(inputStream);
        return settings;
    }

    private void readSvnDirectory(Settings settings) {
        String regexp = ".*eer/tags/([0-9]\\.[0-9]\\.[0-9])(.*)";

        if (settings.isCompareFileExistWithSvn()) {
            printLog("loading svn eer files : " + settings.getSvnPath());

            eerFileMap = new HashMap<String, Map<String, EERFile>>();

            Collection<File> files = FileUtils.listFiles(new File(settings.getSvnPath()), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

            for (File file : files) {

                String filename = file.getName();
                String absolutePath = file.getAbsolutePath();
                String eerVersion = null;
                String filePath = null;

                Pattern infoPattern = Pattern.compile(regexp);
                Matcher infoMatcher = infoPattern.matcher(absolutePath);

                if (infoMatcher.find()) {
                    eerVersion = infoMatcher.group(1);
                    filePath = infoMatcher.group(2);
                }

                EERFile eerFile = new EERFile();
                eerFile.setFilename(filename);
                eerFile.setFilepath(filePath);

                if (eerFileMap.containsKey(eerVersion)) {
                    eerFileMap.get(eerVersion).put(filename, eerFile);
                } else {
                    Map map = new HashMap<String, String>();
                    map.put(filename, eerFile);
                    eerFileMap.put(eerVersion, map);
                }

            }
        }
    }

    private void executeWordParse(Settings settings, Collection<File> files) {
        for (File file : files) {
            if (file.getName().startsWith("~")) // 임시파일
                continue;

            String fileName = file.getName();
            String siteName = getSiteNameInFilePath(file, settings.getInputFilePath());
            String eerVersion = getEERVersion(siteName);
            String outputFileName = getOutputFilename(file);

            printPrettyLog(siteName);

            printLog("parsing word document: " + file.getAbsolutePath());

            WordExtractor wordExtractor = new WordExtractor(mapTableHeaderToKey(settings.getExcelColumns()));
            List<HashMap<String, String>> dataList = wordExtractor.extractWordDocument(file.getAbsolutePath());

            if (settings.isWriteToExcel()) {
                ExcelWriter excelWriter = new ExcelWriter(siteName, settings.getExcelColumns(), dataList);
                excelWriter.write(settings.getOutputFilePath() + "/" + siteName + "/" + outputFileName);
                printLog("saved xls to : " + settings.getOutputFilePath() + "/" + siteName + "/" + outputFileName);
            }

            if (settings.isWriteToDb()) {
                DbExecutor dbExecutor = new DbExecutor(settings);
                dbExecutor.execute(siteName, fileName, eerVersion, dataList, eerFileMap);

                printLog("inserted " + siteName + " to DB");

            }

            //if (true) break;
        }
    }

    private Collection<File> listSourceDirectory(Settings settings) {

        String [] extensions = settings.getInputFileExtension().split(",");
        return FileUtils.listFiles(new File(settings.getInputFilePath()), extensions, true);
    }

    private String getSiteNameInFilePath(File file, String inputFilePath) {
        String temp = file.getAbsolutePath().replaceAll(inputFilePath, ""); // /칸투칸_OnDemand_EER_1.7/trunk/doc/kantukan_501_커스터마이징내역서.docx
        String [] arTemp = temp.split("/");
        String siteName = arTemp[1];

        return siteName;
    }

    private String getEERVersion(String siteName) {
        String eerVersion = "";
        if (siteName.lastIndexOf("_") > -1) {
            eerVersion = siteName.substring(siteName.lastIndexOf("_") + 1);

            try {
                String temp = eerVersion.replaceAll("\\.", "");
                Integer.parseInt(temp);
            } catch (Exception e) {
                eerVersion = "";
            }

            if (eerVersion.length() == 3)
                eerVersion += ".0";
        }

        return eerVersion;
    }

    private Map<String, String> mapTableHeaderToKey(List<ExcelColumn> excelColumns) {

        Map<String, String> map = new HashMap<String, String>();

        for (ExcelColumn excelColumn: excelColumns) {
            String id = excelColumn.getId();
            String mapToWordParagraph = excelColumn.getMapToWordParagraph();

            if (mapToWordParagraph != null) {
                String[] mapToWordParagraphs = mapToWordParagraph.split(",");

                for (String temp : mapToWordParagraphs) {
                    map.put(temp, id);
                }
            }
        }

        return map;
    }

    private String getOutputFilename(File file) {
        return file.getName().substring(0, file.getName().lastIndexOf(".")) + ".xls";
    }

    private static void printPrettyLog(String str) {
        System.out.println(StringUtils.repeat("#", 100));
        System.out.println(StringUtils.center(str, 100));
        System.out.println(StringUtils.repeat("#", 100));
    }

    private static void printLog(String str) {
        System.out.println("# " + str);
    }
}
