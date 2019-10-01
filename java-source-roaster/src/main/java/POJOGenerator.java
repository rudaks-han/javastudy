import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.source.JavaClassSource;

public class POJOGenerator {

    public static void main(String[] args) throws Exception {

        createDomain();

    }

    public static void createDomain() throws Exception {
        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage("com.spectra.pilot.mocha.notice.domain")
                .setName("Notice");

        javaClass.addField()
                .setName("title")
                .setType("String")
                .setPrivate();

        javaClass.addField()
                .setName("content")
                .setType("String")
                .setPrivate();

        javaClass.addField()
                .setName("importance")
                .setType("boolean")
                .setPrivate();

        javaClass.addMethod()
                .setConstructor(true)
                .setPublic()
                .setBody("super(noticeKey);" +
                        "\nthis.title = title;")
                .addParameter("String", "title");

        String filePath = "src/main/java/notice/Notice.java";
        FileUtils.forceMkdir(new File("src/main/java/notice"));
        FileUtils.writeStringToFile(new File(filePath), javaClass.toString());
    }

    public void generate() throws Exception {
        //create an empty instance
        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);

        //set the package name, and Java class name
        javaClass.setPackage("demo.JavaCodeGenerator.roaster").setName("PersonPojo");

        //add interface, can be one or more
        javaClass.addInterface(Serializable.class);

        //extend an abstract class
        javaClass.extendSuperType(Date.class);

        //add a Long/private/static/final Long field, with value '-1L'
        javaClass.addField()
                .setName("serialVersionUID")
                .setType("long")
                .setLiteralInitializer("-1L")
                .setPrivate().setStatic(true).setFinal(true);

        //add a String property, setter/getter functions are generated
        javaClass.addProperty(String.class, "firstName");
        //add an Integer property, final and not exposed
        javaClass.addProperty(Integer.class, "id").setMutable(true).setAccessible(false);
        //add a new property with class 'demo.JavaCodeGenerator.App', an Import line is added
        javaClass.addProperty("demo.JavaCodeGenerator.App", "app");

        //add a public constructor function,
        javaClass.addMethod()
                .setConstructor(true)
                .setPublic()
                .setBody("this.id = id;")
                .addParameter(Integer.class, "id");

        //add another private function
        javaClass.addMethod()
                .setName("increaseId")
                .setPrivate()
                .setBody("this.id += step;")
                .addParameter("int", "step");

        //check if any syntax error
        if(javaClass.hasSyntaxErrors()){
            System.err.println("SyntaxError: "+javaClass.getSyntaxErrors());
        }

        //output to file
        String filePath = "src/main/java/demo/JavaCodeGenerator/roaster/PersonPojo.java";
        FileUtils.forceMkdir(new File("src/main/java/demo/JavaCodeGenerator/roaster"));
        FileUtils.writeStringToFile(new File(filePath), javaClass.toString());
    }
}