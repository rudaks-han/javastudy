package kr.pe.rudaks.javastudy.designpatterns.observer.notification;

public class PushSubscriber implements Observer {
    private Publisher publisher;
    private String state;

    public PushSubscriber(Publisher publisher) {
        this.publisher = publisher;
        publisher.registerObserver(this);
    }

    public void update(String status) {
        this.state = status;
        display();
    }

    public void display() {
        System.out.println("[PushSubscriber] state changed : " + state);
    }
}
