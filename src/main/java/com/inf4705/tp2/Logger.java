package com.inf4705.tp2;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.inf4705.tp2.model.Dynamite;

public class Logger {
    public static boolean p = false;
    public static boolean t = false;

    public static void logLine(String text) {
        if (canLog()) {
            System.out.println(text);
        }
    }

    public static void logTime(long time) {
        if (t) {
            System.out.println(time);
        }
    }

    public static void logResult(List<Dynamite> res) {
        if (p) {
            System.out.println(res.stream().map(d -> String.valueOf(d.getId())).collect(Collectors.joining(" ")));

        }
    }

    public static void createCSVResults(String algo, int weight, int precision, long time, double fx) throws Exception {
        CSVFileWriter fileWriter = new CSVFileWriter();
        File resultsFolder = new File(getJarPath() + "/resultats");
        if (!resultsFolder.exists()) {
            resultsFolder.mkdir();
        }
        fileWriter.appendResultToFile(resultsFolder.getPath() + "/results.csv", algo, weight, time, fx, precision);
    }

    private static boolean canLog() {
        return !p && !t;
    }

    private static String getJarPath() throws UnsupportedEncodingException {
        URL url = ApplicationStartup.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        String parentPath = new File(jarPath).getParentFile().getPath();
        return parentPath;
    }
}
