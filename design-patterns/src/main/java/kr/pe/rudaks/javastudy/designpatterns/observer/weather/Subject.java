package kr.pe.rudaks.javastudy.designpatterns.observer.weather;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
