package optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        optional();
    }

    public static void checkNull() {
        House house = new House();

        if (house.getOwner() != null) {
            System.out.println(house.getOwner().getName()); // NullPointerException 발생
        }

        System.out.println(house.getAddress());
    }

    public static void optional() {
        House house = new House();

        Optional.of(house)
                .map(House::getOwner)
                .map(Person::getName)
                .ifPresent(name -> System.out.println("owner: " + name));

        String name = Optional.of(house)
                .map(House::getOwner)
                .map(Person::getName)
                .orElse("없음");

        System.out.println(name);

        List<String> list = null; new ArrayList<>();
        System.out.println("list: " + Stream.of(list));
    }
}
