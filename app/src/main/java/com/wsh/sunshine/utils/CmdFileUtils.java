package com.wsh.sunshine.utils;

//import com.gowild.smarthome.Constant;

/**
 * ApplianceServer
 *
 * @author SuSong  su_song@gowild.cn
 * @version 16/3/17 下午5:42
 */
public class CmdFileUtils {
//
//    private static volatile String mCmdFilePath;
//
//    public static boolean deleteCmdFile(Context context) {
//        mCmdFilePath = context.getFilesDir() + File.separator + Constant.CMD_FILE;
//        File file = new File(mCmdFilePath);
//        return file.exists() && file.delete();
//    }
//
//    public static File getCmdFile(Context context) {
//        mCmdFilePath = context.getFilesDir() + File.separator + Constant.CMD_FILE;
//        File file = new File(mCmdFilePath);
//        if (!file.exists()) {
//            try {
//                boolean isSuccess = file.createNewFile();
//                XLog.i("isSuccess:" + isSuccess);
//            } catch (IOException e) {
//                XLog.e(e);
//            }
//        }
//        return file;
//    }
//
//    public static void writeCmdFile(Context context, String data) {
//        writeCmdFile(context, data, false);
//    }
//
//    public static void writeCmdFile(Context context, String data, boolean isAppend) {
//        FileOutputStream fos = null;
//        try {
//            mCmdFilePath = context.getFilesDir() + File.separator + Constant.CMD_FILE;
//            XLog.i("cmdFilePath = " + mCmdFilePath);
//            File file = new File(mCmdFilePath);
//            if (!file.exists() && !file.createNewFile()) {
//                return;
//            }
//            fos = new FileOutputStream(file, isAppend);
//            fos.write(data.getBytes(Constant.UTF8));
//        } catch (IOException e) {
//            XLog.e(e);
//        } finally {
//            try {
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (IOException e) {
//                XLog.e(e);
//            }
//        }
//    }
//
//    public static void mergeLogFileAndCmdFile(Context context) {
//        BufferedInputStream  bis = null;
//        BufferedOutputStream bos = null;
//        FileInputStream      in  = null;
//        FileOutputStream     out = null;
//        try {
//            mCmdFilePath = context.getFilesDir() + File.separator + Constant.CMD_FILE;
//            File cmdFile = new File(mCmdFilePath);
//            if (!cmdFile.exists() && !cmdFile.createNewFile()) {
//                return;
//            }
//            in = new FileInputStream(cmdFile);
//            bis = new BufferedInputStream(in);
//            File logFile = LogUtils.getLogFile();
//            out = new FileOutputStream(logFile, true);
//            bos = new BufferedOutputStream(out);
//            byte[] buf = new byte[1024];
//            int    len = 0;
//            bos.write("\r\n========= 指令文件开始 =========\r\n".getBytes("utf-8"));
//            while ((len = bis.read(buf)) != -1) {
//                bos.write(buf, 0, len);
//            }
//            bos.write("\r\n========= 指令文件结束 =========\r\n\r\n".getBytes("utf-8"));
//        } catch (IOException e) {
//            XLog.e(e);
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//                if (out != null) {
//                    out.close();
//                }
//                if (bis != null) {
//                    bis.close();
//                }
//                if (bos != null) {
//                    bos.close();
//                }
//            } catch (IOException e) {
//                XLog.e(e);
//            }
//        }
//    }

}
