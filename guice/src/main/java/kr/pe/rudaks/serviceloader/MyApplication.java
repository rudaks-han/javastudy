package kr.pe.rudaks.serviceloader;

import javax.inject.Inject;

// https://www.journaldev.com/2403/google-guice-dependency-injection-example-tutorial

public class MyApplication {

    private MessageService service;

    @Inject
    public void setService(MessageService service) {
        this.service = service;
    }

    public boolean sendMessage(String msg, String rec) {
        return service.sendMessage(msg, rec);
    }
}
