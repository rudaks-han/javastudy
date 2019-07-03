package kr.pe.rudaks.javastudy;

import javax.inject.Singleton;

@Singleton
public class FacebookService implements MessageService {
    public boolean sendMessage(String msg, String receipient) {
        System.out.println("Message sent Facebook user " + receipient + " with message=" + msg);
        return true;
    }
}
