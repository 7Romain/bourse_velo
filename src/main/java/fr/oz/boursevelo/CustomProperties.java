package fr.oz.boursevelo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "fr.oz.boursevelo")
public class CustomProperties {
        private String apiUrl;
}
