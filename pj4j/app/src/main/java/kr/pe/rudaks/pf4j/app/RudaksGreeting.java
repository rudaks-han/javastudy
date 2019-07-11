package kr.pe.rudaks.pf4j.app;

import kr.pe.rudaks.pf4j.api.Greeting;

public class RudaksGreeting implements Greeting {
    public String getGreeting() {
        return "Hi Rudaks (by Service Loader)";
    }
}
