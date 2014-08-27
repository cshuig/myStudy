package com.cshuig.freemarker;


import com.cshuig.model.Student;
import com.cshuig.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/8/26 0026.
 */
public class TestFreemarker {

    private FreemarkerUtil freemarkerUtil;
    @Before
    public void setUp(){
        freemarkerUtil = new FreemarkerUtil();
    }

    /**
     * 测试的用的是一个简单的ftl
     */
    @Test
    public void test01(){
        //数据模型
        Map<String,Object> dataModel = new HashMap<String, Object>();
        dataModel.put("userName","张三");
        //将数据模型 和 数据模板 进行组合，结果输出到控制台
        freemarkerUtil.print("hello.ftl",dataModel);
    }

    /**
     * 测试的用的是一个简单的ftl
     */
    @Test
    public void test02(){
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("userName","李四");
        freemarkerUtil.fprint("hello.ftl",dataModel,"hello.html");
    }

    /**
     * 测试的用的是一个带有html格式的 ftl
     */
    @Test
    public void test03(){
        //数据模型
        Map<String,Object> dataModel = new HashMap<String, Object>();
        dataModel.put("userName","张三");
        //将数据模型 和 数据模板 进行组合，结果输出到控制台
        freemarkerUtil.print("hello2.ftl",dataModel);
    }

    /**
     * 测试的用的是一个带有html格式的 ftl
     */
    @Test
    public void test04(){
        //数据模型
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("userName","李四");
        //将数据模型 和 数据模板 进行组合，结果输出到hello.html文件中
        freemarkerUtil.fprint("hello2.ftl",dataModel,"hello.html");
    }

    /**
     * 测试的用的是一个带有html格式的 ftl
     * 测试指令：<#if user.id lt 12>
     *              输出：小于12情况
     *           <#elseif user.id lt18>
     *              输出：大于12 小于 18 的情况
     *           <#else>
     *              输出：大于18的情况
     *           </#if>
     * 数据模型：是一个对象 user
     * 模型文件：hello3.ftl
     */
    @Test
    public void test05(){
        //数据模型
        Map<String, Object> dataModel = new HashMap<String, Object>();
        User user = new User(1,"王五",100);
        dataModel.put("user",user);
        //将数据模型 和 数据模板 进行组合，结果输出到hello.html文件中
        freemarkerUtil.fprint("hello3.ftl",dataModel,"hello.html");
    }


    /**
     * 测试的用的是一个带有html格式的 ftl
     * 测试指令：<#list users as user>
     *              输出： ${user.name}
     *            </#list>
     * 数据模型：是一个对象 list
     * 模型文件：hello4.ftl
     */
    @Test
    public void test06(){
        //数据模型
        Map<String, Object> dataModel = new HashMap<String, Object>();
        List<User> list = new ArrayList<User>();
        list.add(new User(1,"王五1",100));
        list.add(new User(2,"王五2",100));
        list.add(new User(11,"王五3",100));
        list.add(new User(22,"王五4",100));
        list.add(new User(101,"王五5",100));
        list.add(new User(44,"王五6",100));

        dataModel.put("users",list);
        //将数据模型 和 数据模板 进行组合，结果输出到hello.html文件中
        freemarkerUtil.fprint("hello4.ftl",dataModel,"hello.html");
    }

    /**
     * 测试的用的是一个带有html格式的 ftl
     * 测试指令：<#list users as user>
     *              输出： ${user.name}
     *            </#list>
     * 数据模型：是一个对象 list
     * 模型文件：hello5.ftl  包含(include)  top.ftl_
     */
    @Test
    public void test07(){
        //数据模型
        Map<String, Object> dataModel = new HashMap<String, Object>();
        List<User> list = new ArrayList<User>();
        list.add(new User(1,"王五1",100));
        list.add(new User(2,"王五2",100));
        list.add(new User(11,"王五3",100));
        list.add(new User(22,"王五4",100));
        list.add(new User(101,"王五5",100));
        list.add(new User(44,"王五6",100));

        dataModel.put("users",list);
        dataModel.put("userName","貂蝉");
        //将数据模型 和 数据模板 进行组合，结果输出到hello.html文件中
        freemarkerUtil.fprint("hello5.ftl",dataModel,"hello.html");
    }

    /**
     * 测试的用的是一个带有html格式的 ftl
     * 测试空值：模板用到的数据是一个null，就会报错
     * 数据模型：是一个对象 list
     * 模型文件：hello6.ftl
     */
    @Test
    public void test08(){
        //数据模型
        Map<String, Object> dataModel = new HashMap<String, Object>();
        //此时student对象并没有group的值，这是如果页面使用group就直接报错
        //2.3之后，freemarker提供了一个处理null的方式 --->  null!"没有值"
        dataModel.put("student",new Student(12,"诸葛亮"));
        //将数据模型 和 数据模板 进行组合，结果输出到hello.html文件中
        freemarkerUtil.fprint("hello6.ftl",dataModel,"hello.html");
    }
}
