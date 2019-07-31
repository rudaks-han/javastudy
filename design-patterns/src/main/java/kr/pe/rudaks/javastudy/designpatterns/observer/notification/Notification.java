package kr.pe.rudaks.javastudy.designpatterns.observer.notification;

public class Notification {
    public static void main(String[] args) {
        StateChangeMachine stateChangeMachine = new StateChangeMachine();
        EmailSubscriber emailSubscriber = new EmailSubscriber(stateChangeMachine);
        PushSubscriber pushSubscriber = new PushSubscriber(stateChangeMachine);

        stateChangeMachine.setState("new_message");
    }
}
