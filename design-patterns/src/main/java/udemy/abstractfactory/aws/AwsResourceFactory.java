package udemy.abstractfactory.aws;

import udemy.abstractfactory.Instance;
import udemy.abstractfactory.ResourceFactory;
import udemy.abstractfactory.Storage;
import udemy.abstractfactory.Instance.Capacity;

public class AwsResourceFactory implements ResourceFactory {
    @Override
    public Instance createInstance(Capacity capacity) {
        return new Ec2Instance(capacity);
    }

    @Override
    public Storage createStorage(int capMib) {
        return new S3Storage(capMib);
    }
}
