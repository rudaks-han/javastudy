package headfirst.designpatterns.observer.notification;

public interface Publisher {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
