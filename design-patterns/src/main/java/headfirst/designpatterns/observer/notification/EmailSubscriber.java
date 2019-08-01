package headfirst.designpatterns.observer.notification;

public class EmailSubscriber implements Observer {
    private Publisher publisher;
    private String state;

    public EmailSubscriber(Publisher publisher) {
        this.publisher = publisher;
        publisher.registerObserver(this);
    }

    public void update(String status) {
        this.state = status;
        display();
    }

    public void display() {
        System.out.println("[EmailSubscriber] state changed : " + state);
    }
}
