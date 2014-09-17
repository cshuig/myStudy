package com.cshuig.sitemesh.fillter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

import javax.servlet.annotation.WebFilter;

/**
 * Created by Administrator on 2014/9/17.
 *
 *
 *
 *
 * <filter>
     <filter-name>sitemesh</filter-name>
     <filter-class>org.sitemesh.config.ConfigurableSiteMeshFilter</filter-class>
   </filter>
   <filter-mapping>
     <filter-name>sitemesh</filter-name>
     <url-pattern>/*</url-pattern>
   </filter-mapping>

属性说明：
 asyncSupported        否     该Filter是否支持异步操作模式
 dispatcherType        否     指定Filter对那种dispatcher模式进行过滤 该属性支持 Async,Error Forward,include,request
 displayName           否     File 显示的名称
 filterName            是     Filter的名称
 initParams            否     Filter的配置参数
 servletNames          否     可以指定多个,表示对这几个特定的的serlet 进行过滤
 urlPatterns/value     否     指定 Filter拦截的 URL
 *
 */
@WebFilter(filterName = "sitemesh",urlPatterns = "/*")
public class SitemeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        super.applyCustomConfiguration(builder);
    }
}
