package udemy.abstractfactory.aws;

import udemy.abstractfactory.Storage;

public class S3Storage implements Storage {

    public S3Storage(int capacityInMib) {
        System.out.println("Allocated " + capacityInMib + " on S3");
    }

    @Override
    public String getId() {
        return "S3ID";
    }
}
