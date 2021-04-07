package rudaks;

import org.apache.commons.lang.StringUtils;
import org.pf4j.PluginManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Boot {

    public static void main(String[] args) {
        // print logo
        printLogo();

        // retrieves the spring application context
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        // retrieves automatically the extensions for the Greeting.class extension point
        Greetings greetings = applicationContext.getBean(Greetings.class);
        greetings.printGreetings();

        // stop plugins
        PluginManager pluginManager = applicationContext.getBean(PluginManager.class);
        /*
        // retrieves manually the extensions for the Greeting.class extension point
        List<Greeting> greetings = pluginManager.getExtensions(Greeting.class);
        System.out.println("greetings.size() = " + greetings.size());
        */
        pluginManager.stopPlugins();
    }

    private static void printLogo() {
        System.out.println(StringUtils.repeat("#", 40));
        System.out.println(StringUtils.center("PF4J-SPRING", 40));
        System.out.println(StringUtils.repeat("#", 40));
    }
}
