package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpTest {
    public static void main(String[] args) {

        // GetMapping("{agentGroupId}")
        // GetMapping
        //String regexp = "(.*)(\\((.*?)\\))";
        //String regexp = "(^[)]*)([(]*.*[)]*)";
        //String regexp = "(.*)(\\([^\\(\\)]?\\))?";
        String regexp = "(\\w+)(\\(.*?\\))?";
        String value = "GetMapping(\"{agentGroupId}\")";
        //value = "GetMapping";

        Pattern infoPattern = Pattern.compile(regexp, Pattern.DOTALL);
        Matcher infoMatcher = infoPattern.matcher(value);

        if (infoMatcher.find()) {
            System.out.println(infoMatcher.group());
            System.out.println("0 : " + infoMatcher.group(0));
            System.out.println("1 : " + infoMatcher.group(1));
            System.out.println("2 : " + infoMatcher.group(2));
            System.out.println("3 : " + infoMatcher.group(3));
            //System.out.println(infoMatcher.group(2));
        }
    }
}
