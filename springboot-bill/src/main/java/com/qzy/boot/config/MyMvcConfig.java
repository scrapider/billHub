package com.qzy.boot.config;


import com.qzy.boot.component.MyLocalResolver;
import com.qzy.boot.converter.DateConverter;
import com.qzy.boot.interceptor.LoginHandleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MyMvcConfig{

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
       return new WebMvcConfigurer(){
           @Override
           public void addFormatters(FormatterRegistry registry) {
               registry.addConverter(new DateConverter());
           }

           @Override
           public void addViewControllers(ViewControllerRegistry registry){
                registry.addViewController("/").setViewName("main/login");
                registry.addViewController("/index.html").setViewName("main/login");
                registry.addViewController("/main.html").setViewName("main/index");
                registry.addViewController("/bill/list.html").setViewName("bill/list");
           }

           @Override
           public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandleInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/","/index.html","/login")
                        .excludePathPatterns("/css/*","/img/*","/js/*");
           }
       };
    }



    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_method");
        return hiddenHttpMethodFilter;
    }




}
