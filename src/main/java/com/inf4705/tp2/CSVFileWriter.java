package com.inf4705.tp2;

import java.io.*;

public class CSVFileWriter {
    private static final String SEPARATOR = ",";
    private static final String FILE_HEADER = "algo,weight,time,f(x),rapport,precision";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public void appendResultToFile(String resultFilePath, String algo, int goal, long temps, double fx, int precision) {
        File resultFile = new File(resultFilePath);
        if (!resultFile.exists()) {
            printHeader(resultFile);
        }
        try (FileWriter fr = new FileWriter(resultFile, true)) {
            double rapport = temps / fx;
            fr.write(algo + SEPARATOR + goal + SEPARATOR + temps + SEPARATOR + fx + SEPARATOR + rapport + SEPARATOR + precision);
            fr.write(NEW_LINE_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printHeader(File file) {
        try (FileWriter fr = new FileWriter(file, true)) {
            file.createNewFile();
            fr.write(FILE_HEADER);
            fr.write(NEW_LINE_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
