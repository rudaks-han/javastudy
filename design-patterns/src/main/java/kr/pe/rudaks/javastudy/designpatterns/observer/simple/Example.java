package kr.pe.rudaks.javastudy.designpatterns.observer.simple;

public class Example {
    public static void main(String[] args) {
        SimpleSubject simpleSubject = new SimpleSubject();

        SimpleObserver simpleObserver = new SimpleObserver(simpleSubject);

        simpleSubject.setValue(100);
    }
}
