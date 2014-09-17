package com.utils;

import java.util.Date;

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
     * 测试分页查询
     */
    @Test
    public void testCollectionWithPagination(){
        try {
            mockMvcGet("/admin/AppVersionGridModel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加
     */
    @Test
    public void testAdd(){
        try {
//        	String json =  "{\"announcement_id\": 110,\"user_id\": 10003,\"content\": \"请输入内容\"}";

            String json = "{\"versionCode\":\"1.0.3\",\"updateType\":\"choose\",\"status\":\"wfb\",\"description\":\"更新详细\",\"created\":1410939046000,\"appType\":\"ios\"}";
            mockMvcPost("/admin/saveOrUpdateAppversion", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testServiceAdd() throws ServiceException{
        AppVersion appVersion = new AppVersion();
        appVersion.setAppType("android");
        appVersion.setCreated(new Date());
        appVersion.setDescription("反反复复");
        appVersion.setStatus("wfb");
        appVersion.setUpdateType("choose");
        appVersion.setVersionCode("1.0.1");
        appversionService.saveAppversion(appVersion);
    }
}
