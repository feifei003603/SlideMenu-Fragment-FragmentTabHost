/**
 * Copyright (c) 2011 France Telecom R&D Beijing
 * Advanced Software Center
 * Raycom InfoTech Park C, Science Institute South Road Beijing China
 * All rights reserved.
 * 
 * Orange DoSomeGood Project
 * File: LogHelper.java
 * Version 1.0 created Aug 24, 2011
 */

package com.beibeishow.nimoreport.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Daquan.Jing Revision History: <<Date>> <<Who>> <<What>> 4 21, 2011
 *          Daquan.jing initial version
 */
public class LogUtil {

    private static FileLog logToFile;

    private static boolean enableDefaultLog = true;

    private static boolean enableToast = true;

    private static boolean enableFileLog = true;

    private static final int RETURN_NOLOG = 99;

    private static final String RETURN_NOLOG_STRING = "RETURN_NOLOG";

    static {
        try {
            if (enableFileLog)
                logToFile = new FileLog();
        } catch (RuntimeException e) {
            Log.e("LogHelper", e.toString());
        } catch (IOException e) {
            Log.e("LogHelper", e.toString());
        }
    }

    public static void setLogEnable(boolean enableDefaultLog) {
        LogUtil.enableDefaultLog = enableDefaultLog;
    }

    public static void toast(Context context, String text, int duration) {
        if (context == null || TextUtils.isEmpty(text)) {
            return;
        }

        if (enableToast) {
            Toast.makeText(context, text, duration).show();
        }

        if (enableDefaultLog) {
            d(context.getClass().getSimpleName(), text);
        }

        if (enableFileLog) {
            efile(context.getClass().getSimpleName(), text);
        }
    }

    public static int d(String tag, String msg, Throwable tr) {

        if (msg == null)
            msg = "";

        dfile(tag, msg);
        return enableDefaultLog ? Log.d(tag, msg, tr) : RETURN_NOLOG;
    }

    public static int d(String tag, String msg) {

        if (msg == null)
            msg = "";

        dfile(tag, msg);
        return enableDefaultLog ? Log.d(tag, msg) : RETURN_NOLOG;
    }

    public static int e(String tag, String msg) {
        if (msg == null)
            msg = "";

        efile(tag, msg);
        return enableDefaultLog ? Log.e(tag, "Pid = " + Process.myPid() + "," + msg) : RETURN_NOLOG;
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (msg == null)
            msg = "";

        efile(tag, msg);
        return enableDefaultLog ? Log.e(tag, msg, tr) : RETURN_NOLOG;
    }

    public static String getStackTraceString(Throwable tr) {
        return enableDefaultLog ? Log.getStackTraceString(tr) : RETURN_NOLOG_STRING;
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (msg == null)
            msg = "";

        return enableDefaultLog ? Log.i(tag, msg, tr) : RETURN_NOLOG;
    }

    public static int i(String tag, String msg) {
        if (msg == null)
            msg = "";
        return enableDefaultLog ? Log.i(tag, msg) : RETURN_NOLOG;
    }

    public static boolean isLoggable(String tag, int level) {
        return enableDefaultLog ? Log.isLoggable(tag, level) : false;
    }

    public static int println(int priority, String tag, String msg) {
        return enableDefaultLog ? Log.println(priority, tag, msg) : RETURN_NOLOG;
    }

    public static int v(String tag, String msg, Throwable tr) {
        // vfile(tag, msg);
        return enableDefaultLog ? Log.v(tag, msg, tr) : RETURN_NOLOG;
    }

    public static int v(String tag, String msg) {
        // vfile(tag, msg);
        return enableDefaultLog ? Log.v(tag, msg) : RETURN_NOLOG;
    }

    public static int w(String tag, String msg) {
        return enableDefaultLog ? Log.w(tag, msg) : RETURN_NOLOG;
    }

    public static int w(String tag, Throwable tr) {
        return enableDefaultLog ? Log.w(tag, tr) : RETURN_NOLOG;
    }

    public static int w(String tag, String msg, Throwable tr) {
        return enableDefaultLog ? Log.w(tag, msg, tr) : RETURN_NOLOG;
    }

    public static void efile(String tag, String msg) {

        if (logToFile != null && enableFileLog) {
            logToFile.writeFileLog(Log.ERROR, tag, msg);
        }
    }

    public static void dfile(String tag, String msg) {

        if (logToFile != null && enableFileLog) {
            logToFile.writeFileLog(Log.DEBUG, tag, msg);
        }
    }

    // public static void writeLogToFile(String tag, String msg) {
    //
    // if (logToFile != null) {
    // logToFile.writeFileLog(Log.DEBUG, tag, msg);
    // }
    // }

}

class FileLog {

    private static final String TAG = "LogHelper";

    public static final String SD_CARD = Environment.getExternalStorageDirectory()
            .getAbsolutePath();

    public final static String APPROOTNAME = "StormOnLine";

    public final static String MEDIAPATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/" + APPROOTNAME + "/";

    public static final String FILE1 = MEDIAPATH + APPROOTNAME + ".log";

    // public final static String APPROOTNAME = "DoSomeGood";
    // public final static String MEDIAPATH =
    // Environment.getExternalStorageDirectory().getAbsolutePath()
    // + "/" + APPROOTNAME + "/";
    public static final int MAX_FILE_SIZE = 2; // M

    // bytes
    private FileWriter fileWriter;

    public FileLog() throws RuntimeException, IOException {
        File sdcard = new File(SD_CARD);
        File file1 = new File(FILE1);

        if (!sdcard.exists()) {
            throw new RuntimeException("SD card not exists!");
        } else {
            File mediaPath = new File(MEDIAPATH);
            if (!mediaPath.exists()) {
                mediaPath.mkdirs();
            }

            if (!file1.exists()) {

                if (!file1.createNewFile()) {
                    Log.e(TAG, "Create new file failed.");
                }

            } else {
                long fileSize = (file1.length() >>> 20);// convert to M bytes
                if (fileSize > MAX_FILE_SIZE) {
                    try {
                        file1.createNewFile();
                    } catch (IOException e) {
                        LogUtil.e(TAG, e.getMessage());
                    }
                }
            }
            fileWriter = new FileWriter(file1, true);
        }
    }

    // we use one space to separate elements
    public void writeFileLog(int priority, String tag, String message) {

        Date date = new Date();
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy:MM:dd kk:mm:ss.SSS");
        String strLog = simpleDateFormate.format(date);

        StringBuffer sb = new StringBuffer(strLog);
        sb.append(' ');
        sb.append(strPriority[priority]);
        sb.append(' ');
        sb.append(tag);
        sb.append(' ');
        sb.append(message);
        sb.append('\n');
        strLog = sb.toString();

        try {
            fileWriter.write(strLog);
            fileWriter.flush();
        } catch (IOException e) {
            Log.e("FileLog", "", e);
        }
    }

    private static String strPriority[];
    static {
        strPriority = new String[8];
        strPriority[0] = "";
        strPriority[1] = "";
        strPriority[2] = "verbose";
        strPriority[3] = "debug";
        strPriority[4] = "info";
        strPriority[5] = "warn";
        strPriority[6] = "error";
        strPriority[7] = "ASSERT";
    }
}
