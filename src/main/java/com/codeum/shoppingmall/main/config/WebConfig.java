package com.codeum.shoppingmall.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${custom.ImgSavePath}")
    public String imgSavedPath;

    private String resourcePath = "/upload/**"; // view 에서 접근할 경로
    //private String localSavedPath = "/files/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations("file://" + imgSavedPath);
    }

}
