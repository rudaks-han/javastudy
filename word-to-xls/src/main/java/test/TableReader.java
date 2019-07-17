package test;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

public class TableReader {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("/Users/rudaks/temp/111.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            Iterator bodyElementIterator = xdoc.getBodyElementsIterator();
/*

            int loop = 0;

            while (bodyElementIterator.hasNext()) {
                IBodyElement element = (IBodyElement) bodyElementIterator.next();

                System.err.println("===================");
                System.err.println("loop : " + loop);

                if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
                    System.err.println("---------");

                    List<XWPFTable> tableList = element.getBody().getTables();
                    for (XWPFTable table : tableList) {
                        for (int i = 0; i < table.getRows().size(); i++) {

                            System.err.println("table : " + i);
                            for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {
                                System.out.println(table.getRow(i).getCell(j).getText());
                            }
                        }
                    }
                } */
/*else if ("PARAGRAPH".equalsIgnoreCase(element.getElementType().name())) {
                    List<XWPFParagraph> paragraphList = element.getBody().getParagraphs();

                    for (int i=0; i<paragraphList.size(); i++) {


                        XWPFParagraph paragraph = paragraphList.get(i);
                        if ("".equals(paragraph.getText()) || paragraph.getText() == null) {
                            continue;
                        }

                        System.err.println("paragraph : " + i);
                        System.out.println(paragraph.getText());

                    }
                }*//*


                loop++;
            }
*/

            Iterator<IBodyElement> iter = xdoc.getBodyElementsIterator();

            while (iter.hasNext())
            {

                IBodyElement elem = iter.next();

                if (elem instanceof XWPFParagraph)
                {
                    String text = ((XWPFParagraph) elem).getText();
                    if ("".equals(text) || text == null)
                        continue;

                    System.err.println("=======================");
                    System.err.println("Paragraph");
                    System.err.println("=======================");
                    System.out.println(((XWPFParagraph) elem).getText());
                    List<XWPFParagraph> paragraphList = elem.getBody().getParagraphs();

                    /*for (int i=0; i<paragraphList.size(); i++) {
                        XWPFParagraph paragraph = paragraphList.get(i);
                        if ("".equals(paragraph.getText()) || paragraph.getText() == null) {
                            continue;
                        }

                        System.err.println("=======================");
                        System.err.println("Paragraph");
                        System.err.println("=======================");
                        System.out.println(paragraph.getText());

                    }*/
                }

                else if (elem instanceof XWPFTable)
                {
                    System.err.println("=======================");
                    System.err.println("Table");
                    System.err.println("=======================");
                    //System.out.println(((XWPFTable) elem).getText());

                    List<XWPFTable> tableList = elem.getBody().getTables();
                    for (XWPFTable table : tableList) {
                        for (int i = 0; i < table.getRows().size(); i++) {

                            for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {
                                //System.out.println(table.getRow(i).getCell(j).getText());

                                String text = table.getRow(i).getCell(j).getText();
                                if (j % 2 == 0)
                                    System.out.print(j + " [" + text + "] ");
                                else {
                                    System.out.println(j + " " + text);
                                }
                            }
                        }
                    }
                }
            }

            fis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
