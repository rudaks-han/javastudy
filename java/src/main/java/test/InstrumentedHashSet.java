package test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0;

    @Override
    public boolean add(E e) {
        System.out.println("add");
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        System.out.println("addAll");
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return this.addCount;
    }

}

class Main {
    public static void main(String[] args) {
        InstrumentedHashSet<String> language = new InstrumentedHashSet<String>();
        language.addAll(Arrays.asList("Java", "Ruby", "Scala"));

        System.out.println(language.getAddCount());
    }
}
