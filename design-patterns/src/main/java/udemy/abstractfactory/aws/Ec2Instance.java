package udemy.abstractfactory.aws;

import udemy.abstractfactory.Instance;
import udemy.abstractfactory.Storage;

public class Ec2Instance implements Instance {

    public Ec2Instance(Instance.Capacity capacity) {
        System.out.println("Created Ec2Instance");
    }

    @Override
    public void start() {
        System.out.println("Ec2Instance started");
    }

    @Override
    public void attachStorage(Storage storage) {
        System.out.println("Attached " + storage + " to Ec2Instance");
    }

    @Override
    public void stop() {
        System.out.println("Ec2Instance stopped");
    }

    public String toString() {
        return "Ec2Instance";
    }
}
