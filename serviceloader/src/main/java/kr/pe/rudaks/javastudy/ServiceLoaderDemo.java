package kr.pe.rudaks.javastudy;

import java.util.ServiceLoader;

public class ServiceLoaderDemo {
    public static void main(String[] args) throws InterruptedException {
        ServiceLoader<CPService> serviceLoader = ServiceLoader.load(CPService.class);

        while (true) {
            for (CPService cpService : serviceLoader) {
                cpService.show();
            }
            Thread.sleep(1000);
        }
    }
}
