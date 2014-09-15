package com.cshuig.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 这是:独立测试方式
 * Created by Administrator on 2014/9/15.
 */
public class Controller1Test {
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        UserController userController = new UserController();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
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
