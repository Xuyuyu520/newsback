package com.xyc.news.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 文件处理程序配置* @author xuyuyu
 * @ClassName FileHandlerConfig
 * @Description TODO
 * @date 2024-04-23 10:09:56
 */
@Configuration
public class FileHandlerConfig extends WebMvcConfigurationSupport {
    @Value("${upload.path}")
    private String parentDir;
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("File:"+System.getProperty("user.dir")+"/upload/");
        // registry.addResourceHandler("/upload/**").addResourceLocations("File:"+parentDir+"upload/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }



}
