import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

    public static void main(String[] args) throws IOException {

        //String html = FileUtils.readFileToString(new File("/Users/rudaks/_WORK/_GIT/javastudy/jsoup/src/main/resources/cassandra.html"), "UTF-8");
        String html = FileUtils.readFileToString(new File("/Users/rudaks/_WORK/_GIT/javastudy/jsoup/src/main/resources/test.html"), "UTF-8");
        Document doc = Jsoup.parseBodyFragment(html);

        // 파일명
        // 문서 제목
        // section 1
        // section 2

        // title
        /*String documentTitle = doc.getElementsByTag("title").text();
        System.out.println("documentTitle : " + documentTitle);

        Elements section1Body = doc.getElementsByClass("sect1");
        for (Element section1: section1Body) {
            System.out.println("---------------------------------------------------");
            String section1Title = section1.select("h2 > a.link").text();
            String section1Href = section1.select("h3 > a").attr("href");
            String section1Content = section1.select("div").text();
            System.out.println("section1Title : " + section1Title);
            System.out.println("section1Href : " + section1Href);
            System.out.println("section1Content : " + section1Content);
            Elements section2Body = section1.select(".sectionbody > .sect2");
            for (Element section2: section2Body) {
                System.out.println("---------------------------------------------------");
                String section2Title = section2.select("h3 > a").text();
                String section2Href = section2.select("h3 > a").attr("href");
                String section2Content = section2.select("div").text();

                System.out.println("section2Title : " + section2Title);
                System.out.println("section2Href : " + section2Href);
                System.out.println("section2Content : " + section2Content);
            }
        }*/

        // sectionbody > .paragraph
        // .sectionbody > .sect2
        Elements elements = doc.getElementsByTag("h3").nextAll();
        System.out.println("------");
        //System.out.println("next : " + elements.html());
        for (Element element: elements) {
            Elements children = element.children().not(".sect2");

            System.out.println("-----child html");
            System.out.println(children.html());

            for (Element child: children) {
                /*System.out.println("-----child html");
                System.out.println(child.html());*/
            }
        }
        //System.out.println(elements.size());
        String result = elements.html();

        //System.out.println(result);

    }
}
