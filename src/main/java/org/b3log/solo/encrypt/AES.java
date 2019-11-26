package org.b3log.solo.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created on 2019/11/26 23:17.
 *
 * @author zhaozengjie
 * Description : Aes对称加密
 */
public class AES {

    private Logger logger = LoggerFactory.getLogger(AES.class);

    private static AES aes = new AES();

    public static AES getInstance() {
        return aes;
    }

    public String encrypt(String sSrc, String sKey) {
        if (sKey == null) {
            logger.error("Key为空null");
            throw new RuntimeException("Key为空");
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            logger.error("Key长度不是16位");
            return null;
        }
        KeyGenerator kgen = null;
        try {
            kgen = KeyGenerator.getInstance("AES");

            kgen.init(128, new SecureRandom(sKey.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(enCodeFormat, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
            return new Base64().encodeToString(encrypted);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error("", e);
            throw new RuntimeException("加密出现错误");
        }

    }

    public String decrypt(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                logger.error("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                logger.error("Key长度不是16位");
                return null;
            }
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(sKey.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(enCodeFormat, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("",e);
            throw new RuntimeException("解密出现错误");
        }
    }



}
