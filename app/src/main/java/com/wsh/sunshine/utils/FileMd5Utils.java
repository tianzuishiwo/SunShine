package com.wsh.sunshine.utils;

import com.elvishew.xlog.XLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ApplianceServer
 *
 * @author SuSong  su_song@gowild.cn
 * @version 16/4/25 下午5:04
 */
public class FileMd5Utils {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
                                               "5", "6", "7", "8", "9",
                                               "a", "b", "c", "d", "e", "f"};

    /**
     * 获取文件的MD5值
     */
    public static String getFileMD5(File file) {
        String          md5         = null;
        FileInputStream fis         = null;
        FileChannel     fileChannel = null;
        try {
            fis = new FileInputStream(file);
            fileChannel = fis.getChannel();
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest    md         = MessageDigest.getInstance("MD5");
            md.update(byteBuffer);
            md5 = byteArrayToHexString(md.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
            XLog.e(e);
        } finally {
            try {
                if (fileChannel != null) {
                    fileChannel.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                XLog.e(e);
            }
        }

        return md5;
    }

    /**
     * 字节数组转十六进制字符串
     */
    private static String byteArrayToHexString(byte[] digest) {

        StringBuilder buffer = new StringBuilder();
        for (byte aDigest : digest) {
            buffer.append(byteToHexString(aDigest));
        }
        return buffer.toString();
    }

    /**
     * 字节转十六进制字符串
     */
    private static String byteToHexString(byte b) {
        int d1 = (b & 0xf0) >> 4;
        int d2 = b & 0xf;
        return hexDigits[d1] + hexDigits[d2];
    }

    //入口
//    public static void main(String[] args) throws Exception {
//        System.out.println("-----测试创建文件的md5后缀----------");
//
//        File file = new File("E:/jquery-1.9.1.min.js");
//
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        //获取参数
//        String parent = file.getParent();
//
//        System.out.println(parent);
//        String fileName = file.getName();
//        System.out.println(fileName);
//        //首先获取文件的MD5
//        String md5 = getFileMD5(file);
//
//        //93d97357be249c61407fa21aa434e72f
//        System.out.println("-----获取的md5：" + md5);
//
//        //组装
//        File md5File = new File(parent + fileName + ".md5");
//        if (md5File.exists()) {
//            md5File.delete();
//            md5File.createNewFile();
//        }
//
//        FileOutputStream fos = new FileOutputStream(md5File);
//        fos.write(md5.getBytes());
//
//        fos.flush();
//        fos.close();
//
//        System.out.println("--------完成---------");
//    }
}
