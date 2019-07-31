package kr.pe.rudaks.javastudy.designpatterns.observer.weather;

public class CurrentConditionDisplay implements Observer, DisplayElement {
    private float temporature;
    private float humidity;
    //private float pressure;
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update(float temperature, float humidity, float pressure) {
        this.temporature = temperature;
        this.humidity = humidity;
        //this.pressure = pressure;

        display();
    }

    public void display() {
        System.out.println("Current Condition: " + temporature + "F degrees and " + humidity + "% humidity");
    }


}
