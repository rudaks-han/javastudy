package test;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;

import java.io.FileInputStream;

public class HeaderFooterReader {

    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream("/Users/rudaks/temp/test.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(xdoc);

            XWPFHeader header = policy.getDefaultHeader();
            if (header != null) {
                System.out.println(header.getText());
            }

            XWPFFooter footer = policy.getDefaultFooter();
            if (footer != null) {
                System.out.println(footer.getText());
            }

            fis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}