package core.db;

import javax.crypto.*;

public class Hmac {

    public static String hmac(String str) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
        SecretKey sk = kg.generateKey();
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(sk);
        byte[] result = mac.doFinal(str.getBytes());
        return result.toString();
    }
}
