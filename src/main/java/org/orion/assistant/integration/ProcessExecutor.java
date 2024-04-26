package org.orion.assistant.integration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.orion.assistant.exception.custom.ProcessExecutionException;

public class ProcessExecutor {
    private static Logger LOG = LogManager.getLogger(ProcessExecutor.class);

    public String callLocal(Map<String, String> settings, String command) throws IOException, InterruptedException, ProcessExecutionException {
        // Split the command into parts for the ProcessBuilder
        String[] commandParts = command.split("\\s+");

        // Create a new ProcessBuilder with the command
        ProcessBuilder builder = new ProcessBuilder(commandParts);

        // If there are settings, set the environment variables
        // Encode the values to base64 to avoid vulnerabilities where sending the parameter
        if (settings != null) {
            Map<String, String> env = builder.environment();
            settings.forEach((key, value) -> {
                String encodedValue = Base64.getEncoder().encodeToString(value.toString().getBytes());
                env.put(key, encodedValue);
            });
        }

        // Start the process
        Process process = builder.start();

        // Read the output from the process
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output = reader.lines().collect(Collectors.joining("\n"));

        // Wait for the process to finish
        int exitCode = process.waitFor();

        // Check the exit code and return the output
        if (exitCode == 0) {
            return output;
        } else {
            LOG.error("Process exited with error code " + exitCode);
            throw new ProcessExecutionException("Process exited with error code " + exitCode);
        }
    }
}
