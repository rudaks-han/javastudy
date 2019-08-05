package headfirst.designpatterns.sington.threadsafe;

public class Singleton {
    public static Singleton uniqueInstance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }

        return uniqueInstance;
    }

    public String getDescription() {
        return "I'm a thread safe singleton";
    }
}
