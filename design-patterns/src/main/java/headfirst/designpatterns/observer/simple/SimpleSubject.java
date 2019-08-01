package headfirst.designpatterns.observer.simple;

import java.util.ArrayList;

public class SimpleSubject implements Subject {
    private ArrayList<Observer> observers;
    private int value;

    public SimpleSubject() {
        observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0)
            observers.remove(i);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(value);
        }
    }

    public void setValue(int value) {
        this.value = value;
        notifyObservers();
    }
}
