package udemy.abstractfactory;

import udemy.abstractfactory.Instance.Capacity;

public interface ResourceFactory {

    Instance createInstance(Capacity capacity);

    Storage createStorage(int capMib);
}
