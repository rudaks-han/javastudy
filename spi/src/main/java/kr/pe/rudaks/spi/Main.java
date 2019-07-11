package kr.pe.rudaks.spi;

import kr.pe.rudaks.spi.spec.GreetingsProvider;
import kr.pe.rudaks.spi.spec.GreetingsService;

// https://dzone.com/articles/play-with-java-serviceloader-forget-about-dependen
public class Main {
    public static void main(String[] args) {
        GreetingsService service = GreetingsProvider.getInstance().serviceImpl();
        service.sayHello("rudaks");
    }
}
