package com.utils;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 版本号管理测试类
 * @author Administrator
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AppVersionTest extends BaseJunit{


    /**
     * 测试get请求
     */
    @Test
    @Ignore
    public void testGet(){
        try {
            mockMvcGet("/admin/AppVersionGridModel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试post请求
     * 请求的参数格式：json
     */
    @Test
    @Ignore
    public void testPost(){
        try {
        	//String json =  "{\"announcement_id\": 110,\"user_id\": 10003,\"content\": \"请输入内容\"}";

            String json = "{\"versionCode\":\"1.0.3\",\"updateType\":\"choose\",\"status\":\"wfb\",\"description\":\"更新详细\",\"created\":1410939046000,\"appType\":\"ios\"}";
            mockMvcPost("/admin/saveOrUpdateAppversion", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
