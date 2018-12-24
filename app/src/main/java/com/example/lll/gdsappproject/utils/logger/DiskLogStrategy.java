package com.example.lll.gdsappproject.utils.logger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.orhanobut.logger.LogStrategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DiskLogStrategy implements LogStrategy {

    private final Handler handler;

    DiskLogStrategy(Handler handler) {
        this.handler = handler;
    }


    @Override
    public void log(int priority, String tag, String message) {
        handler.sendMessage(handler.obtainMessage(priority, message));
    }

    static class WriteHandler extends Handler {
        private static final long SAVE_DAYS = 1000 * 60 * 60 * 24;

        private SimpleDateFormat fileFormat = new SimpleDateFormat("yyyy-MM-dd_HH", Locale.ENGLISH);
        private final String folder;
        private String appName = "Logger";

        WriteHandler(Looper looper, String folder, String appName) {
            super(looper);
            this.folder = folder + new SimpleDateFormat("yyyy-MM-dd_HH", Locale.ENGLISH).format(new Date());
            this.appName = appName;
            deleteLoggerFile(folder);

        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String content = (String) msg.obj;
            FileWriter fileWriter = null;
            File logFile = getLogFile(folder, appName);
            try {
                fileWriter = new FileWriter(logFile, true);
                writeLog(fileWriter, content);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                if (fileWriter != null) {
                    try {
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        }

        /**
         * This is always called on a single background thread.
         *  Implementing classes must ONLY wirte to the fileWriter and nothing more.
         *  The abstract class takes care of everything else including close the stream and catching IOException
         * @param fileWriter
         * @param content
         * @throws IOException
         */
        private void writeLog(FileWriter fileWriter, String content) throws IOException {
            fileWriter.append(content);
        }

        private File getLogFile(String folderName, String filename) {
            File folder = new File(filename);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return new File(folder, String.format("%s_%s.log", filename, fileFormat.format(new Date())));
        }

        //删除 SAVE_DAYS 天前的日志
        private synchronized void deleteLoggerFile(String path) {
            File file = new File(path);
            if (!file.exists()) {
                return;
            }
            File[] files = file.listFiles();
            for (File fil : files) {
                //删除最后修改日志遭遇三天前的日志
                if (System.currentTimeMillis() - fil.lastModified() > SAVE_DAYS) {
                    deleteDirWhiteFile(fil);
                }
            }
        }

        private void deleteDirWhiteFile(File dir) {
            if (dir == null || !dir.exists() || !dir.isDirectory()) {
                return;
            }
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    //删除所有文件
                    file.delete();
                } else if (file.isDirectory()) {
                    deleteDirWhiteFile(file);
                }

            }
            //删除目录本身
            dir.delete();
        }
    }


}
