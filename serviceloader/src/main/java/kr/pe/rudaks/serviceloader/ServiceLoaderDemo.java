package kr.pe.rudaks.serviceloader;

import java.util.ServiceLoader;

public class ServiceLoaderDemo {
    public static void main(String[] args) throws InterruptedException {
        ServiceLoader<CPService> serviceLoader = ServiceLoader.load(CPService.class);

        for (CPService cpService : serviceLoader) {
            cpService.show();
        }
    }
}
