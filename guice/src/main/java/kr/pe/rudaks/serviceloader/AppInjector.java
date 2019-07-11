package kr.pe.rudaks.serviceloader;

import com.google.inject.AbstractModule;

public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(MessageService.class).to(FacebookService.class);
    }
}
