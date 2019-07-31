package kr.pe.rudaks.javastudy.designpatterns.observer.notification;

import java.util.ArrayList;

public class StateChangeMachine implements Publisher {
    private ArrayList<Observer> observers;
    private String status;

    public StateChangeMachine() {
        observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0)
            observers.remove(i);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }

    public void setState(String state) {
        this.status = state;

        notifyObservers();
    }
}
