package kr.pe.rudaks.serviceloader;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ClientApplication {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppInjector());

        MyApplication app = injector.getInstance(MyApplication.class);
        app.sendMessage("Hi rudaks", "rudaks");
    }
}
