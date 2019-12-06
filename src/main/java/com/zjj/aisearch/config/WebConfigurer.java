package com.zjj.aisearch.config;

import com.zjj.aisearch.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 和springmvc的webmvc拦截配置一样
 *
 * @author BIANP
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        /*registry.addInterceptor(AuthorityInterceptor()).addPathPatterns("/**");*/
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**").excludePathPatterns("/tologin", "/login", "/logout",
                "/js/**", "/css/**", "/img/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler请求路径
        //addResourceLocations 在项目中的资源路径
        //setCacheControl 设置静态资源缓存时间
        /* registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");*/
    }

    @Bean
    public AuthenticationInterceptor AuthorityInterceptor() {
        return authenticationInterceptor;
    }
}