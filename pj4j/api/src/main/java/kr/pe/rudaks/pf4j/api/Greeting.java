package kr.pe.rudaks.pf4j.api;

import org.pf4j.ExtensionPoint;

public interface Greeting extends ExtensionPoint {
    String getGreeting();
}
