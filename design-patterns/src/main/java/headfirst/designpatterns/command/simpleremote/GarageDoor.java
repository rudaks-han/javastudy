package headfirst.designpatterns.command.simpleremote;

public class GarageDoor {
    public void up() {
        System.out.println("Garage Door is up");
    }

    public void down() {
        System.out.println("Garage Door is down");
    }

    public void stop() {
        System.out.println("Garage Door is stopped");
    }

    public void lightOn() {
        System.out.println("Garage Light is on");
    }

    public void lightOff() {
        System.out.println("Garage Light is off");
    }
}
