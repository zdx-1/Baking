package com.baking.bakingapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //默认MVC禁止跨域请求,我们可以配置自己的策略
        registry.addMapping("/**")//表示在后端允许匹配客户端发过来的任意请求
                .allowedHeaders("*")//请求带任意头都可以
                .allowedMethods("*")//任意请求方式都可以 get/post/put...
                .allowedOriginPatterns("*")//任意域都可以(任意请求地址或端口号)
                .allowCredentials(true)//请求可以携带会话相关信息(cookie/session)
                .maxAge(3600);//同一请求一小时内不再检测 直接放行
    }
}