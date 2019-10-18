package test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class UUIDGenerator {
    public static void main(String[] args) {
        // Creating a random UUIDGenerator (Universally unique identifier).
        /*UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();*/

        /*System.out.println("Random UUIDGenerator String = " + randomUUIDString);
        System.out.println("UUIDGenerator version       = " + uuid.version());
        System.out.println("UUIDGenerator variant       = " + uuid.variant());*/

        //RandomStringUtils.random(2, 33, 125, true, false);

        //long l = ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).getLong();
        //System.err.println("l : " + l);
        //System.out.println(Long.toString(l, Character.MAX_RADIX));

        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid : " + uuid);
        String base64Id = uuidToBase64(uuid);
        System.out.println("base64Id : " + base64Id);
        System.out.println("uuid : " + uuidFromBase64(base64Id));

        List<String> list = new ArrayList<>();

        long count = 1000000;
        System.out.println("checking for " + count);

        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        for (long i=0; i<count; i++) {
            //String id = shortUUID();
            //String id = uuidToBase64(UUID.randomUUID().toString());
            String id = sequenceGenerator.nextId() + "";

            if (list.contains(id)) {
                System.err.println("==== exists ====");
                break;
            } else {
                list.add(id);
            }
        }

        System.out.println("checking ended");


        //System.out.println("shortUUID : " + shortUUID());


    }


    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

    private static String uuidToBase64(String str) {
        Base64 base64 = new Base64();
        UUID uuid = UUID.fromString(str);
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return base64.encodeBase64URLSafeString(bb.array());
    }

    private static String uuidFromBase64(String str) {
        Base64 base64 = new Base64();
        byte[] bytes = base64.decodeBase64(str);
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        UUID uuid = new UUID(bb.getLong(), bb.getLong());
        return uuid.toString();
    }

}
