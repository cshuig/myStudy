package com.cshuig.controller;

import com.framework.mvcConfig.SpringConfig;
import com.framework.mvcConfig.SpringMvcConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

/**
 * 这是:集成Web环境方式
 * Created by Administrator on 2014/9/15.
 */

/**
    //XML风格
    @RunWith(SpringJUnit4ClassRunner.class)
    @WebAppConfiguration(value = "src/main/webapp")
    @ContextHierarchy({
            @ContextConfiguration(name = "parent",locations = "classpath:spring-config.xml"),
            @ContextConfiguration(name = "child",locations = "classpath:spring-mvc.xml")
    })
**/

/**
 * 注解风格
 * @WebAppConfiguration：测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的；value指定web应用的根
 * @ContextHierarchy：指定容器层次，即spring-config.xml是父容器，而spring-mvc.xml是子容器
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent",classes = SpringConfig.class),
        @ContextConfiguration(name = "child", classes = SpringMvcConfig.class)
})
public class Controller2Test {

    @Inject
    private WebApplicationContext wc;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }

    @Test
    public void test01() throws Exception {
        /**
         * 1、mockMvc.perform    ： 执行一个请求
         * 2、MockMvcRequestBuilders.get("/user/1")：构造一个请求
         * 3、ResultActions.andExpect:添加执行完成后的断言
         * 4、ResultActions.andDo：添加一个结果处理器，表示对结果做点什么
         *                      比如此处使用MockMvcResultHandlers.print()输出整个相应结果的信息
         *5、ResultActions.andReturn：表示执行完成后，返回相应的结果
         */
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(MockMvcResultMatchers.view().name("user/view"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertNotNull(mvcResult.getModelAndView().getModel().get("user"));
    }

}
