package com.clayfin;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CordOrigin implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints, you can customize the mapping if needed
                .allowedOrigins("http://localhost:3000") // Allow requests from any origin, you can specify specific origins here
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
                .allowedHeaders("*")
                .allowCredentials(false).maxAge(3600); // Allow all headers, you can specify specific headers here if needed
    }
}
