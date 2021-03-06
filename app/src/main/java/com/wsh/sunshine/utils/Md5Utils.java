package com.wsh.sunshine.utils;

import com.elvishew.xlog.XLog;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * © 2012 amsoft.cn
 * 名称：AbMd5.java
 * 描述：MD5加密.
 * <p>
 * author 还如一梦中
 * version v1.0
 * date：2013-01-17 下午11:52:13
 * <p>
 * 两种方式一样的效果
 */
public class Md5Utils {

    /**
     * MD5加密32位
     *
     * @param str 要加密的字符串
     * @return String 加密的字符串
     */
    public static String MD5(String str) {
        return getMD5(str);
    }

    /**
     * MD5加密32位大写
     *
     * @param str 要加密的字符串
     * @return String 加密的字符串
     */
    public static String MD5UpperCase(String str) {
        return getMD5(str).toUpperCase(Locale.CHINA);
    }

    /**
     * MD5加密16位
     *
     * @param str 要加密的字符串
     * @return String 加密的字符串
     */
    public static String MD516(String str) {
        //16位加密，从第9位到25位
        return getMD5(str).substring(8, 24).toLowerCase(Locale.CHINA);

    }

    /**
     * MD5加密16位
     *
     * @param str 要加密的字符串
     * @return String 加密的字符串
     */
    public static String MD516UpperCase(String str) {
        //16位加密，从第9位到25位
        return getMD5(str).substring(8, 24).toUpperCase(Locale.CHINA);
    }

    /**
     * 描述：MD5加密.
     *
     * @param str 要加密的字符串
     * @return String 加密的字符串
     */
    public static String getMD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(str.getBytes("UTF-8"));
            byte[]        bytes = md5.digest();
            StringBuilder sb    = new StringBuilder();
            for (byte b : bytes) {
                if (Integer.toHexString(0xff & b).length() == 1) {
                    sb.append("0").append(Integer.toHexString(0xff & b));
                } else {
                    sb.append(Integer.toHexString(0xff & b));
                }
            }
            return sb.toString().toLowerCase(Locale.CHINA);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            XLog.e(e);
            return str;
        }
    }

    /**
     * 描述：MD5加密.
     *
     * @param str 要加密的字符串
     * @return String 加密的字符串
     */
    public static String getMD52(String str) {
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                             '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[]        strTemp = str.getBytes(Charset.defaultCharset());
            MessageDigest mdTemp  = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte tmp[] = mdTemp.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char strs[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                strs[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                strs[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            return new String(strs).toLowerCase(Locale.CHINA); // 换后的结果转换为字符串
        } catch (NoSuchAlgorithmException e) {
            XLog.e(e);
            return str;
        }
    }


    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        System.out.println(Md5Utils.MD5("MD5加密"));
        System.out.println(Md5Utils.MD5UpperCase("MD5加密"));
        System.out.println(Md5Utils.MD516("MD5加密"));
        System.out.println(Md5Utils.MD516UpperCase("MD5加密"));
        System.out.println(Md5Utils.getMD52("MD5加密"));
    }

}
