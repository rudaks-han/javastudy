package kr.pe.rudaks.spi.spec.impl;

import kr.pe.rudaks.spi.spec.GreetingsService;

public class ConsoleGreetings implements GreetingsService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello to " + name);
    }
}
