package bans.qaadhar.log;

import android.os.Build;

import java.io.PrintWriter;
import java.io.StringWriter;

import bans.qaadhar.BuildConfig;

/**
 * Created by bi_programmer on 6/30/2017.
 */
public class Log {

    private static final String LINE_SEPARATOR = "\n";

    //    static final boolean LOG = true;
    private static final boolean LOG = BuildConfig.DEBUG;
    private static final boolean LOG_EXCEPTION = true;//BuildConfig.DEBUG;

    public static void i(String tag, String string) {
        if (LOG) android.util.Log.i(tag, string);
    }

    public static void e(String tag, String string) {
        if (string == null) string = "null";
        if (tag == null) tag = "null";

        if (LOG) {
            int maxLogSize = 2000;
            for (int i = 0; i <= string.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i + 1) * maxLogSize;
                end = end > string.length() ? string.length() : end;
                android.util.Log.e(tag, string.substring(start, end));
            }
        }
    }

    public static String error_print(String tag, String message) {
        if (message == null) message = "null";
        if (LOG) {
            android.util.Log.e(tag, "Exception: " + message);
        }
        if (LOG_EXCEPTION) {
            add_to_log(tag + ", message: " + message, null);
        }
        return message;
    }

    public static String error_print(String tag, Exception e) {
        if (e == null) return "";
        String __log = "";
        if (LOG) {
            __log = android.util.Log.getStackTraceString(e);
            android.util.Log.e(tag, "Exception: " + __log);
        }
        if (LOG_EXCEPTION)
            add_to_log(tag, e);
        return __log;
    }

    public static void error_print(String tag, String extra_text, Exception e) {
        if (e == null) return;
        if (LOG) {
            android.util.Log.e(tag, "Exception: " + extra_text + " : " + android.util.Log.getStackTraceString(e));
        }
        if (LOG_EXCEPTION)
            add_to_log(tag, e);
    }

    public static void d(String tag, String string) {
        if (LOG) android.util.Log.d(tag, string);
    }

    public static void v(String tag, String string) {
        if (LOG) android.util.Log.v(tag, string);
    }

    public static void w(String tag, String string) {
        if (LOG) android.util.Log.w(tag, string);
    }

    private static void add_to_log(final String message, final Exception exception) {
        new Thread(() -> {
            uncaughtException(message, exception);
        }).start();
    }

    private static void uncaughtException(final String message, Exception exception) {
        try {
            StringWriter stackTrace = new StringWriter();
            if (exception != null) exception.printStackTrace(new PrintWriter(stackTrace));
            //System.err.println(stackTrace);// You can use LogCat too

            String crash_logs = stackTrace.toString();

            StringBuilder errorReport = new StringBuilder();
            errorReport.append("************ CAUSE OF Exception ************\n\n");
            errorReport.append(message);
            errorReport.append("\n");
            errorReport.append(crash_logs);
            errorReport.append("\n************ DEVICE INFORMATION ***********\n");
            //errorReport.append("User ID : " + B_App.getUser_name());
            errorReport.append("Brand: ");
            errorReport.append(Build.BRAND);
            errorReport.append(LINE_SEPARATOR);
            errorReport.append("Device: ");
            errorReport.append(Build.DEVICE);
            errorReport.append(LINE_SEPARATOR);
            errorReport.append("Model: ");
            errorReport.append(Build.MODEL);
            errorReport.append(LINE_SEPARATOR);
            errorReport.append("Id: ");
            errorReport.append(Build.ID);
            errorReport.append(LINE_SEPARATOR);
            errorReport.append("Product: ");
            errorReport.append(Build.PRODUCT);
            errorReport.append(LINE_SEPARATOR);
            errorReport.append("\n************ FIRMWARE ************\n");
            errorReport.append("SDK: ");
            errorReport.append(Build.VERSION.SDK);
            errorReport.append(LINE_SEPARATOR);
            errorReport.append("Release: ");
            errorReport.append(Build.VERSION.RELEASE);
            errorReport.append(LINE_SEPARATOR);
            errorReport.append("Incremental: ");
            errorReport.append(Build.VERSION.INCREMENTAL);
            errorReport.append(LINE_SEPARATOR);
            errorReport.append("\n************ APP ************\n");
            errorReport.append("\nTime-");
            //errorReport.append(C_Lite_utillity.getDateTime(System.currentTimeMillis()));

            Log.e("Crash_Log", " " + errorReport);
            //write_logs(errorReport.toString());
        } catch (Exception ex) {
            Log.e("Crash_Log excpt", " " + ex.getMessage());
        }
    }
//
//    private static void simple_log(final String message) {
//        try {
//            String errorReport = "************ Log ************\n\n" +
//                    message +
//                    "\n" +
//                    "************ DEVICE INFORMATION ***********\n" +
//                    "User ID : " + B_App.getUser_name() +
//                    LINE_SEPARATOR +
//                    "\n************ FIRMWARE ************\n" +
//                    "SDK: " +
//                    Build.VERSION.SDK_INT +
//                    LINE_SEPARATOR +
//                    "Release: " +
//                    Build.VERSION.RELEASE +
//                    LINE_SEPARATOR +
//                    "Incremental: " +
//                    Build.VERSION.INCREMENTAL +
//                    LINE_SEPARATOR +
//                    "\n************ APP ************\n" +
//                    "\nTime-" +
//                    C_Lite_utillity.getDateTime(System.currentTimeMillis());
//            write_logs(errorReport);
//        } catch (Exception ex) {
//            Log.e("Log excpt", " " + ex.getMessage());
//        }
//    }
//
//    private static void write_logs(String logs) throws IOException {
//        File logdir = new File(Constants.SD_CARD_LOGS);
//        File logfile = new File(Constants.SD_CARD_LOGS, Constants.LOG_ERROR);
//        if (!logdir.exists()) {
//            logdir.mkdirs();
//        }
//        if (!logfile.exists())
//            logfile.createNewFile();
//        FileWriter writer = new FileWriter(logfile, true);
//        writer.append(logs);
//        writer.flush();
//        writer.close();
//    }

}