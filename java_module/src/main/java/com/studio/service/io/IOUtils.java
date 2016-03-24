package com.studio.service.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/3/24.
 */
public class IOUtils {

    public interface OnProgress {
        int SUCCESS = 0;

        int FAILURE = 1;

        void onStart();

        void onProgress(int progress);

        void onStop(int code);
    }


    public static void readInpustream(InputStream inputStream, OutputStream outputStream, OnProgress progress) {
        OutputStream fos = outputStream;
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024 * 512];
        progress.onStart();
        try {
            bis = new BufferedInputStream(inputStream);
            int readLen;
            int sum = 0;
            while (true) {
                readLen = bis.read(buffer, 0, buffer.length);
                if (readLen == -1) {
                    break;
                }
                sum += readLen;
                fos.write(buffer, 0, readLen);
                progress.onProgress(sum);
            }
            progress.onStop(OnProgress.SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            progress.onStop(OnProgress.FAILURE);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
