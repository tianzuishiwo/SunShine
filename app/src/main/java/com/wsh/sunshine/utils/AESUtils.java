package com.wsh.sunshine.utils;

import com.elvishew.xlog.XLog;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密解密
 *
 * @author SuSong  su_song@gowild.cn
 * @version 16/6/30 下午3:26
 */
public class AESUtils {


    /**
     * AES加密
     * http://blog.kchandrahasa.com/blog/2013/08/09/android-4-dot-2-and-javax-dot-crypto-dot-badpaddingexception-pad-block-corrupted/
     * 经测试无效
     *
     * @param content 待加密的内容
     * @param key     密钥
     * @return 加密后的String
     */
    public static String aesEncrypt(String content, String key) {
        try {
            int          iterationCount = 1000;
            int          saltLength     = 32; // bytes; 64 bits
            int          keyLength      = 256;
            SecureRandom random         = new SecureRandom();
            byte[]       salt           = new byte[saltLength];
            random.nextBytes(salt);
            KeySpec          keySpec       = new PBEKeySpec(key.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory keyFactory    = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[]           raw           = keyFactory.generateSecret(keySpec).getEncoded();
            SecretKeySpec    secretKeySpec = new SecretKeySpec(raw, "AES");

            // Cipher cipher = Cipher.getInstance("AES"); //4.3以上有bug，用下边一句ok
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] bytes        = content.getBytes("utf-8");
            byte[] encryptBytes = cipher.doFinal(bytes);

            return byteArr2HexStr(encryptBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | UnsupportedEncodingException | InvalidKeySpecException | IllegalBlockSizeException e) {
            XLog.e(e);
        }
        return content;
    }

    /**
     * AES解密
     * http://blog.kchandrahasa.com/blog/2013/08/09/android-4-dot-2-and-javax-dot-crypto-dot-badpaddingexception-pad-block-corrupted/
     * 经测试无效
     *
     * @param content 待解密的内容
     * @param key     密钥
     * @return 解密后的String
     */
    public static String aesDecrypt(String content, String key) {
        try {
            int          iterationCount = 1000;
            int          saltLength     = 32; // bytes; 64 bits
            int          keyLength      = 256;
            SecureRandom random         = new SecureRandom();
            byte[]       salt           = new byte[saltLength];
            random.nextBytes(salt);
            KeySpec          keySpec       = new PBEKeySpec(key.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory keyFactory    = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[]           raw           = keyFactory.generateSecret(keySpec).getEncoded();
            SecretKeySpec    secretKeySpec = new SecretKeySpec(raw, "AES");

            // Cipher cipher = Cipher.getInstance("AES"); //4.3以上有bug，用下边一句ok
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] bytes        = hexStr2ByteArr(content);
            byte[] decryptBytes = cipher.doFinal(bytes);

            return new String(decryptBytes, "utf-8");
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | UnsupportedEncodingException | InvalidKeySpecException | IllegalBlockSizeException e) {
            XLog.e(e);
        }
        return content;
    }


    /**
     * AES加密
     *
     * @param content 待加密的内容
     * @param key     密钥
     * @return 加密后的String
     */
    public static String aesEncryptDeprecated(String content, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // SecureRandom random = SecureRandom.getInstance("SHA1PRNG");//android4.1以后会有bug,替换下边一句
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            random.setSeed(key.getBytes(Charset.defaultCharset()));
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[]    raw       = secretKey.getEncoded();

            // Cipher cipher = Cipher.getInstance("AES"); //4.3以上有bug，用下边一句ok
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(raw, "AES"));

            byte[] bytes        = content.getBytes("utf-8");
            byte[] encryptBytes = cipher.doFinal(bytes);

            return byteArr2HexStr(encryptBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | UnsupportedEncodingException | NoSuchProviderException | IllegalBlockSizeException e) {
            XLog.e(e);
        }
        return content;
    }

    /**
     * AES解密
     *
     * @param content 待解密的内容
     * @param key     密钥
     * @return 解密后的String
     */
    public static String aesDecryptDeprecated(String content, String key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // SecureRandom random = SecureRandom.getInstance("SHA1PRNG");//android4.1以后会有bug,替换下边一句
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            sr.setSeed(key.getBytes(Charset.defaultCharset()));
            kgen.init(128, sr);
            SecretKey secretKey = kgen.generateKey();
            byte[]    raw       = secretKey.getEncoded();

            // Cipher cipher = Cipher.getInstance("AES"); //4.3以上有bug，用下边一句ok
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(raw, "AES"));

            byte[] bytes        = hexStr2ByteArr(content);
            byte[] decryptBytes = cipher.doFinal(bytes);

            return new String(decryptBytes, "utf-8");
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | UnsupportedEncodingException | NoSuchProviderException | IllegalBlockSizeException e) {
            XLog.e(e);
        }
        return content;
    }

    //随机生成密钥
    private static String getKey() throws NoSuchAlgorithmException {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 为我们选择的DES算法生成一个KeyGenerator对象
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(sr);
        // 生成密匙
        SecretKey key = kg.generateKey();
        // 获取密匙数据
        byte rawKeyData[] = key.getEncoded();

        //return new String(rawKeyData);

        return byteArr2HexStr(rawKeyData);
    }

//    public static void main(String[] args) throws Exception {
//        String content = "我爱你";
//        System.out.println("加密前：" + content);
//
//        String key = "123456890";
//        key = getKey();
//        System.out.println("加密密钥和解密密钥：" + key);
//
//        String encrypt = aesEncrypt(content, key);
//        System.out.println("加密后：" + encrypt);
//
//        String decrypt = aesDecrypt(encrypt, key);
//        System.out.println("解密后：" + decrypt);
//    }


    /**
     * 将byte数组转换为表示16进制值的字符串，
     * 如：byte[]{8,18}转换为：0813，
     * 和public static byte[] hexStr2ByteArr(String strIn)
     * 互为可逆的转换过程
     *
     * @param arrB 需要转换的byte数组
     * @return 转换后的字符串
     */
    private static String byteArr2HexStr(byte[] arrB) {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuilder sb = new StringBuilder(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组，
     * 和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     *
     * @param strIn 需要转换的字符串
     * @return 转换后的byte数组
     */
    private static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes(Charset.defaultCharset());
        int    iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2, Charset.defaultCharset());
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
}
