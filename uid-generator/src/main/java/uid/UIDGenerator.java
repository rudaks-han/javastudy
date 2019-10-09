package uid;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.stream.Collectors;

public class UIDGenerator {
    public static void main(String[] args) {
        String result = new SecureRandom().ints(0,36)
                .mapToObj(i -> Integer.toString(i, 36))
                .map(String::toUpperCase).distinct().limit(16).collect(Collectors.joining())
                .replaceAll("([A-Z0-9]{4})", "$1-").substring(0,19);

        System.out.println(result);


        System.out.println(shortUUID());

    }

    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }



}
