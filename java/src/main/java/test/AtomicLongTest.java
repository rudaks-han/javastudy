package test;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {
    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(System.currentTimeMillis());

        System.out.println(System.currentTimeMillis());
        System.out.println(atomicLong);

    }
}
