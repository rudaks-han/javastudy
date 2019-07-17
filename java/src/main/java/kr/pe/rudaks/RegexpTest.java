package kr.pe.rudaks;

import org.apache.commons.io.FilenameUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpTest {
    public static void main(String[] args) {
        //String value = "CCS00964.java (테스트 페이지)";
        //String value = "1 abbcc.java (테스트 페이지)";
        //String value = "sql-map-config.xml: TicketHistory.xml 를 TicketHistoryExt.xml 로 대체";

        //String value = "- /ecc/lib/m2repository/com/ubiz/commons-clazz/1.0alpha1/a-b-1.2.pom";
        String value = "- ecc-02-commons-engine/src/main/java/spectra/ext/commons/beans/legacy/model/CCS00964.java (테스트 페이지)";
        //String regexp = "([a-zA-Z0-9*]\\.[a-zA-Z0-9*])";
        String regexp = "[a-zA-Z0-9-_.]*\\.[a-zA-Z0-9-_]*";


        Pattern infoPattern = Pattern.compile(regexp);
        Matcher infoMatcher = infoPattern.matcher(value);
        /*if (infoMatcher.find()){ // find가 group보다 선행되어야 합니다.
            System.out.println(infoMatcher.group()); // ERROR
        }*/
        while (infoMatcher.find()) {
            System.out.println(infoMatcher.group());
        }
    }
}
