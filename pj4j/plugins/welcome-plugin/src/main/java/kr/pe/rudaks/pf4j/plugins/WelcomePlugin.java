package kr.pe.rudaks.pf4j.plugins;

import kr.pe.rudaks.pf4j.api.Greeting;
import org.apache.commons.lang.StringUtils;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.pf4j.RuntimeMode;

public class WelcomePlugin extends Plugin {
    public WelcomePlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("WelcomePlugin.start()");
        // for testing the development mode
        if (RuntimeMode.DEVELOPMENT.equals(wrapper.getRuntimeMode())) {
            System.out.println(StringUtils.upperCase("WelcomePlugin"));
        }
    }

    @Override
    public void stop() {
        System.out.println("WelcomePlugin.stop()");
    }

    @Extension
    public static class WelcomeGreeting implements Greeting {

        @Override
        public String getGreeting() {
            return "Welcome";
        }

    }

}
