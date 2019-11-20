package ru.kupchenkov.security;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class DesEncrypter {

    private Cipher ecipher;
    private Cipher dcipher;

    public DesEncrypter() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, getKey());
        dcipher.init(Cipher.DECRYPT_MODE, getKey());
    }

    public String encrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        byte[] utf8 = str.getBytes("UTF8");
        byte[] enc = ecipher.doFinal(utf8);
        return new String(Base64.getEncoder().encode(enc));
    }

    public String decrypt(String str) throws IOException, IllegalBlockSizeException, BadPaddingException {
        byte[] dec = Base64.getDecoder().decode(str);
        byte[] utf8 = dcipher.doFinal(dec);
        return new String(utf8, "UTF8");
    }

    public static SecretKey getKey(){
        String keyString = "pro$k8er";
        byte[] keyStrict = keyString.getBytes();
        keyStrict = Arrays.copyOf(keyStrict, 8);
        System.out.println(Arrays.toString(keyStrict));
        SecretKey secretKeySpec = new SecretKeySpec(keyStrict, "DES");
        return  secretKeySpec;
    }

}
