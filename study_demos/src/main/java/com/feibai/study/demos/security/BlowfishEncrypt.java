package com.feibai.study.demos.security;

import com.google.common.io.BaseEncoding;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 对称加密算法：Blowfish
 */
public class BlowfishEncrypt {
    private static final byte[] KEY_BYTES = "salt_for_secret".getBytes(StandardCharsets.UTF_8);

    /**
     * 加密
     */
    public static String encrypt(String input) throws Exception {
        byte[] encrypted = operate(Cipher.ENCRYPT_MODE, input.getBytes(StandardCharsets.UTF_8));
        return BaseEncoding.base16().encode(encrypted);
    }

    /**
     * 解密
     */
    public String decrypt(String input) throws Exception {
        byte[] decrypted = operate(Cipher.DECRYPT_MODE, BaseEncoding.base16().decode(input));
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    private static byte[] operate(int opmode, byte[] input) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY_BYTES, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(opmode, keySpec);
        return cipher.doFinal(input);
    }

}
