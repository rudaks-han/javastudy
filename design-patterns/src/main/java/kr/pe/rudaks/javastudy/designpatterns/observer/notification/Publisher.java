package kr.pe.rudaks.javastudy.designpatterns.observer.notification;

public interface Publisher {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
