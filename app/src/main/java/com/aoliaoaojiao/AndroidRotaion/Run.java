package com.aoliaoaojiao.AndroidRotaion;

import android.os.Build;

import java.util.Scanner;

public final class Run {

    private Run() {
        // not instantiable
    }

    private static void runRotationWatch() throws ConfigurationException {
        final Device device = new Device();

        try {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.equals("exit")){
                    return;
                }
            }
        } catch (RuntimeException e){
            Ln.e(e.getMessage());
        }
    }

    public static void main(String... args) {
        int status = 0;
        try {
            internalMain(args);
        } catch (Throwable t) {
            Ln.e(t.getMessage(), t);
            status = 1;
        } finally {
            // By default, the Java process exits when all non-daemon threads are terminated.
            // The Android SDK might start some non-daemon threads internally, preventing the scrcpy server to exit.
            // So force the process to exit explicitly.
            System.exit(status);
        }
    }

    private static void internalMain(String... args) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            Ln.e("Exception on thread " + t, e);
        });

        Ln.disableSystemStreams();

        Ln.i("Device: [" + Build.MANUFACTURER + "] " + Build.BRAND + " " + Build.MODEL + " (Android " + Build.VERSION.RELEASE + ")");

        try {
            runRotationWatch();
        } catch (Exception e) {
            Ln.e("error", e);
            // Do not print stack trace, a user-friendly error-message has already been logged
        }
    }
}
