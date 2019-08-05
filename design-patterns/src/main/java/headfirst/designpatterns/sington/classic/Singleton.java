package headfirst.designpatterns.sington.classic;

public class Singleton {
    public static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }

        return uniqueInstance;
    }

    public String getDescription() {
        return "I'm a classic singleton";
    }
}
