package com.framework.mvcConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 此类等价于 spring-mvc.xml
 * Created by Administrator on 2014/9/15.
 * 备注：springmvc配置：只需要扫描Controller注解，就ok，因为其他注解，spring已经扫描了，会冲突
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.cshuig",useDefaultFilters = false,
               includeFilters = {@ComponentScan.Filter(type=FilterType.ANNOTATION,
                                                       value = {Controller.class})})
public class SpringMvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INFO/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }
}
