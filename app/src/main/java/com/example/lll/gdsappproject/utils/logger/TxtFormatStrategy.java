package com.example.lll.gdsappproject.utils.logger;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;

import com.example.lll.gdsappproject.utils.CommonUtils;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogStrategy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.orhanobut.logger.Logger.ASSERT;
import static com.orhanobut.logger.Logger.DEBUG;
import static com.orhanobut.logger.Logger.ERROR;
import static com.orhanobut.logger.Logger.INFO;
import static com.orhanobut.logger.Logger.VERBOSE;
import static com.orhanobut.logger.Logger.WARN;
import static com.orhanobut.logger.Logger.t;

public class TxtFormatStrategy implements FormatStrategy {

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String SEPARATOR = " ";
    private final Date date;
    private final SimpleDateFormat dateFormat;
    private final LogStrategy logStrategy;
    private final String tag;

    private TxtFormatStrategy(Builder builder) {
        date = builder.date;
        dateFormat = builder.dateFormat;
        logStrategy = builder.logStrategy;
        tag = builder.tag;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public void log(int priority, String onceOnlyTag, String message) {
        String tag = formatTag(onceOnlyTag);
        date.setTime(System.currentTimeMillis());
        StringBuilder header = new StringBuilder();

        header.append(Long.toString(date.getTime()));
        header.append(SEPARATOR);
        header.append(dateFormat.format(date));

        header.append(SEPARATOR);
        header.append(logLevel(priority));

        header.append(SEPARATOR);
        header.append(tag);
        header.append(SEPARATOR);

        StringBuilder builder = new StringBuilder();
        builder.append(header);

        //message
        if (message.contains(NEW_LINE)) {
            message = message.replaceAll(NEW_LINE, NEW_LINE + header.toString());
        }
        builder.append(message);
        builder.append(NEW_LINE);
        logStrategy.log(priority, tag, builder.toString());

    }

    public static final class Builder {
        Date date;
        SimpleDateFormat dateFormat;
        LogStrategy logStrategy;
        String tag = "PRETTY_LOGGER";

        private Builder() {

        }

        public Builder dete(Date val) {
            date = val;
            return this;
        }

        public Builder dateFormat(SimpleDateFormat val) {
            dateFormat = val;
            return this;
        }

        public Builder logStrategy(LogStrategy val) {
            logStrategy = val;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public TxtFormatStrategy build(String pkgName, String appName) {
            if (date == null) {
                date = new Date();
            }
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.UK);

            }
            if (logStrategy == null) {
                String disPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                String folder = disPath + File.separatorChar + "Android" + File.separatorChar
                        + "data" + File.separatorChar + pkgName + File.separatorChar
                        + "log" + File.separatorChar;
                HandlerThread ht = new HandlerThread("AndroidFileLogger" + folder);
                ht.start();
                Handler handler = new DiskLogStrategy.WriteHandler(ht.getLooper(), folder, appName);
                logStrategy = new DiskLogStrategy(handler);
            }
            return new TxtFormatStrategy(this);
        }
    }

    private String logLevel(int value) {
        switch (value) {
            case VERBOSE:
                return "VERBOSE";
            case DEBUG:
                return "DEBUG";
            case INFO:
                return "INFO";
            case WARN:
                return "WARN";
            case ERROR:
                return "ERROR";
            case ASSERT:
                return "ASSERT";
            default:
                return "UNKNOWN";
        }
    }


    private String formatTag(String tag) {
        if (!TextUtils.isEmpty(tag) && !CommonUtils.isEquals(this.tag, tag)) {
            return this.tag + " - " + tag;

        }
        return this.tag;
    }
}
