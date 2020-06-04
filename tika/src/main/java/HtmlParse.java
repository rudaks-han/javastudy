import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;


public class HtmlParse {

    public static void main(final String[] args) throws IOException, SAXException, TikaException {
        HtmlParse htmlParse = new HtmlParse();
        String result = htmlParse.parseToPlainText();

        //System.out.println(result);
    }

    public String parseToPlainText() throws IOException, SAXException, TikaException {
        BodyContentHandler handler = new BodyContentHandler();

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        try (InputStream stream = HtmlParse.class.getResourceAsStream("cassandra.html")) {
            parser.parse(stream, handler, metadata, parseContext);
            System.err.println("title : " + metadata.get(metadata.TITLE));
            //System.err.println("title : " + metadata.get());
            //System.err.println("body : " + handler.toString());

            String[] metadataNames = metadata.names();//Now we have all the metadata tags here

            for(String name : metadataNames) {
                if (name == "h2"){ //here you can check if the tag names are the particular ones you need and do what you want with them
                    System.out.println(name + ": " + metadata.get(name));
                }
            }
            return handler.toString();
        }
    }
}
