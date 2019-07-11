package kr.pe.rudaks.spi.spec;

import java.util.NoSuchElementException;
import java.util.ServiceLoader;

// define the provider
public class GreetingsProvider {
    private static GreetingsProvider provider;
    private ServiceLoader<GreetingsService> serviceLoader;

    private GreetingsProvider() {
        serviceLoader = ServiceLoader.load(GreetingsService.class);
    }

    public static GreetingsProvider getInstance() {
        if (provider == null) {
            provider = new GreetingsProvider();
        }

        return provider;
    }

    public GreetingsService serviceImpl() {
        GreetingsService service = serviceLoader.iterator().next();

        if (service != null) {
            return service;
        } else {
            throw new NoSuchElementException("No implementation for GreetingProvider");
        }
    }
}
