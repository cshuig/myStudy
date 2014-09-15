package com.framework.mvcConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 此类等价于 spring-config.xml
 * Created by Administrator on 2014/9/15.
 * 备注：
 *  1、ComponentScan注解中需要排除：Controller注解的扫描，因为springmvc，默认已经扫描了，会冲突
 */
@Configuration
@ComponentScan(basePackages = "com.cshuig",
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = {Controller.class}
        )
        }
)
public class SpringConfig {
}
