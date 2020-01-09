package udemy.abstractfactory;

public interface Instance {
    public enum Capacity{micro, small, large}

    void start();

    void attachStorage(Storage storage);

    void stop();
}
