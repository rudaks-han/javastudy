package uid;

import org.apache.commons.codec.digest.DigestUtils;

public class Hash {

    public static void main(String[] args) {
        String str = "1aafdadfadfasdfasf2345";

        String hashing = DigestUtils.md5Hex(str);

        System.out.println(hashing);
    }
}
