package org.orion.assistant.config.web;

import java.util.List;

import org.orion.assistant.utils.FileUtilsWorker;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The `WebConfig` class in Java configures CORS settings for a Spring application based on allowed
 * origins loaded from a CSV file.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final List<String> allowedOrigins;

    public WebConfig() {
        this.allowedOrigins = FileUtilsWorker.loadCsvAsList("/allowed_origins.csv");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] originsArray = allowedOrigins.toArray(new String[0]);
        registry.addMapping("/**") // This will apply CORS to all paths
                .allowedOrigins(originsArray) // Allow this origin to access your API
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify which methods to allow
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true);
    }
}