package com.baking.bakingapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class ResConfig  implements WebMvcConfigurer {

/*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") // 匹配所有请求路径
                .addResourceLocations("file:D:/Code/idea_workspace/ruaner/uploads/"); // 指定文件系统位置
    }
*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String userDir = System.getProperty("user.dir");
        System.out.println("111"+userDir);

        String desiredDir = userDir.substring(0, userDir.lastIndexOf(File.separator) + 1); // 使用File.separator确保跨平台兼容性
        if (desiredDir.endsWith("baking")) {
            desiredDir = desiredDir.substring(0, desiredDir.length() - "baking".length());
        }
        System.out.println("222"+desiredDir);

        // 使用replace方法将反斜杠替换为正斜杠（如果需要的话，这取决于你的具体需求）
        desiredDir = desiredDir.replace(File.separator, "/"); // 如果是在Windows上运行，这将把反斜杠转换为正斜杠
        desiredDir = desiredDir + "baking-api/uploads/";
        String path = desiredDir;

        System.out.println("333"+path);
        registry.addResourceHandler("/**")
                .addResourceLocations("file:" + path );

    }
}

