package org.orion.assistant.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtilsWorker {
    private static final Logger LOG = LogManager.getLogger(FileUtilsWorker.class);

    /**
     * Load a CSV file as a list of strings
     * @param resourcePath
     * @return
     */
    public static List<String> loadCsvAsList(String resourcePath) {
        List<String> origins = new ArrayList<>();
        try (InputStream is = FileUtilsWorker.class.getResourceAsStream(resourcePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                origins.add(line.trim());
            }
            LOG.info("Resource loaded: {}", resourcePath);
        } catch (IOException e) {
            LOG.error("Error reading resource: {}", resourcePath, e);
        } catch (NullPointerException e) {
            LOG.error("Resource not found: {}", resourcePath);
        }
        return origins;
    }
}
