package kr.pe.rudaks.serviceloader;

import javax.inject.Singleton;

@Singleton
public class EmailService implements MessageService {
    public boolean sendMessage(String msg, String receipient) {
        System.out.println("Email Message sent to " + receipient + " with message=" + msg);
        return true;
    }
}
