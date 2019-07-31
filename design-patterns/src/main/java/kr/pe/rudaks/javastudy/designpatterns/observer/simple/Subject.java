package kr.pe.rudaks.javastudy.designpatterns.observer.simple;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
