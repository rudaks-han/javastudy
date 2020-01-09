package udemy.abstractfactory.gcp;

import udemy.abstractfactory.Instance;
import udemy.abstractfactory.Instance.Capacity;
import udemy.abstractfactory.ResourceFactory;
import udemy.abstractfactory.Storage;
import udemy.abstractfactory.aws.Ec2Instance;
import udemy.abstractfactory.aws.S3Storage;

public class GoogleResourceFactory implements ResourceFactory {
    @Override
    public Instance createInstance(Capacity capacity) {
        return new GoogleComputeEngineInstance(capacity);
    }

    @Override
    public Storage createStorage(int capMib) {
        return new GoogleCloudStorage(capMib);
    }
}
