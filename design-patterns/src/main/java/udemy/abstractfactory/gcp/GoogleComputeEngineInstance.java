package udemy.abstractfactory.gcp;

import udemy.abstractfactory.Instance;
import udemy.abstractfactory.Storage;

public class GoogleComputeEngineInstance implements Instance {

    public GoogleComputeEngineInstance(Capacity capacity) {
        System.out.println("Created Google Compute Engine Instance");
    }

    @Override
    public void start() {
        System.out.println("Google Compute Engine Instance started");
    }

    @Override
    public void attachStorage(Storage storage) {
        System.out.println("Attached " + storage + " to Google Compute Engine Instance");
    }

    @Override
    public void stop() {
        System.out.println("Google Compute Engine Instance stopped");
    }

    public String toString() {
        return "Google Compute Engine Instance";
    }
}
