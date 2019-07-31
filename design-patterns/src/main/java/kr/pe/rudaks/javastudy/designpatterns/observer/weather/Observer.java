package kr.pe.rudaks.javastudy.designpatterns.observer.weather;

public interface Observer {
    void update(float temperature, float humidity, float pressure);
}
