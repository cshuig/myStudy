package com.framework.initSystem;

import com.framework.mvcConfig.SpringConfig;
import com.framework.mvcConfig.SpringMvcConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * 注册web.xml中的组件
 * Created by Administrator on 2014/9/15.
 */
public class WebInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        //1、注册spring
        AnnotationConfigWebApplicationContext rootConfig = new AnnotationConfigWebApplicationContext();
        rootConfig.register(SpringConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootConfig));

        //2、注册springMVC
        AnnotationConfigWebApplicationContext springMvcConfig = new AnnotationConfigWebApplicationContext();
        springMvcConfig.register(SpringMvcConfig.class);

        //3、DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springMvcConfig);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcherServlet",dispatcherServlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");

        //4、编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        FilterRegistration filterRegistration = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
    }
}
