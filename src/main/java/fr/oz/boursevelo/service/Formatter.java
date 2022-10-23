package fr.oz.boursevelo.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Formatter implements WebMvcConfigurer {
@Override
    public void addFormatters(@NotNull FormatterRegistry registry){
    registry.addFormatter(new DateFormatter("MM-dd-yyyy"));
}
}
