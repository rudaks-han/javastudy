package velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VelocityWriter {

    static String modelName = "User";
    static String packageName = "com.companyname.projectname";

    public static void main(String[] args) {

        final Properties props = new Properties();
        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        final VelocityEngine engine = new VelocityEngine(props);
        final VelocityContext context = new VelocityContext();

        engine.init();

        Template template = engine.getTemplate("templates/class.vm");

        //VelocityContext context = new VelocityContext();

        if(packageName != null) {
            context.put("packagename", packageName);
        }

        List<Field> properties = new ArrayList<>();
        properties.add(new Field("id", "int"));
        properties.add(new Field("firstName", "String"));
        properties.add(new Field("lastName", "String"));
        properties.add(new Field("dob", "LocalDate"));
        context.put("className", modelName);
        context.put("properties", properties);

        StringWriter writer = new StringWriter();
        template.merge( context, writer );

        System.out.println(writer.toString());
    }
}
