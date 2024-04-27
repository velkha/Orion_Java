package org.orion.assistant.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtilsWorker {
    
    public static List<String> loadCsvAsList(String filePath) {
        List<String> origins = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                origins.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return origins;
    }

}
