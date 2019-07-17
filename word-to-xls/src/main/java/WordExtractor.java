import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class WordExtractor {

    List<String> tableTitle = new ArrayList<String>();
    Map<String, String> tableHeaderToKey;

    public WordExtractor(Map tableHeaderToKey) {
        this.tableHeaderToKey = tableHeaderToKey;
    }

    public List<HashMap<String, String>> extractWordDocument(String wordFilePath) {
        List<HashMap<String, String>> dataList = null;

        try {
            dataList = parseWordDocument(wordFilePath);

            dataList = mapTableTitle(dataList);
            dataList = removeInList(dataList, "문서정보", "변경정보");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    private List<HashMap<String, String>> parseWordDocument(String filepath) throws IOException {
        FileInputStream fis = null;
        List<HashMap<String, String>> dataList = null;

        try {
            fis = new FileInputStream(filepath);
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));

            Iterator<IBodyElement> iter = xdoc.getBodyElementsIterator();

            String paragraph = null;

            while (iter.hasNext()) {

                IBodyElement elem = iter.next();

                if (elem instanceof XWPFParagraph) {
                    String text = ((XWPFParagraph) elem).getText();
                    if ("".equals(text) || text == null)
                        continue;
                    paragraph = ((XWPFParagraph) elem).getText();
                }

                else if (elem instanceof XWPFTable) {
                    tableTitle.add(paragraph);
                    dataList = parseWordTable(elem);
                }

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            fis.close();
        }

        return dataList;
    }

    private List<HashMap<String, String>> parseWordTable(IBodyElement elem) {
        List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

        List<XWPFTable> tableList = elem.getBody().getTables();
        for (XWPFTable table : tableList) {
            HashMap<String, String> map = new HashMap<String, String>();
            String previousHeaderText = null;

            for (int i = 0; i < table.getRows().size(); i++) {

                for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {

                    XWPFTableCell cell = table.getRow(i).getCell(j);
                    String text = cell.getText();

                    if (j % 2 ==1) {
                        String headerText = table.getRow(i).getCell(j-1).getText();

                        String key = null;

                        if ("".equals(headerText)) {
                            key = previousHeaderText;

                            text = "";
                            for (int k=0; k<cell.getBodyElements().size(); k++) {
                                IBodyElement element = cell.getBodyElements().get(k);

                                if (element instanceof XWPFParagraph) {
                                    String _text = ((XWPFParagraph) element).getText();

                                    text += _text + "\n";
                                }
                            }
                        } else {
                            key = tableHeaderToKey.get(headerText);
                        }

                        if (headerText != null && !"".equals(headerText))
                            previousHeaderText = key;

                        if (key != null) {

                            String value = map.get(key);
                            if (value != null && value.length() > 0) {
                                map.put(key, value + "\n" + text);
                            } else {
                                map.put(key, text);
                            }
                        }
                    }

                }
            }

            dataList.add(map);

        }

        return dataList;
    }

    private List<HashMap<String, String>> mapTableTitle(List<HashMap<String, String>> dataList) {
        if (dataList != null && dataList.size() > 0) {
            for (int i = 0; i < dataList.size(); i++) {
                HashMap<String, String> map = dataList.get(i);
                map.put("customizeName", tableTitle.get(i));

            }
        }

        return dataList;
    }

    private List<HashMap<String, String>> removeInList(List<HashMap<String, String>> dataList, String ... filterString) {
        for (int i=0; i<filterString.length; i++) {
            if (dataList != null && dataList.size() > 0) {
                for (int j = 0; j < dataList.size(); j++) {
                    HashMap<String, String> map = dataList.get(j);
                    if (filterString[i].equals(map.get("customizeName"))) {
                        dataList.remove(j);
                    }
                }
            }
        }

        return dataList;
    }
}
