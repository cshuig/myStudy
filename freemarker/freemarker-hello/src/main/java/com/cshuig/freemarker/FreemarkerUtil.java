package com.cshuig.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by cshuig on 2014/8/26 0026.
 */
public class FreemarkerUtil {
    /**
     * 获取freemarker模板
     * @param name
     * @return
     */
    public Template getTemplate(String name){
        try {
            //通过Freemarker的Configuration读取相应的ftl
            Configuration configuration = new Configuration();
            //设定读取ftl模板文件的 位置
            configuration.setClassForTemplateLoading(this.getClass(),"../ftl");
            //在模板文件目录中找到相应的模板
            Template template = configuration.getTemplate(name);
            return template;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将数据模型 和 数据模板 进行组合，结果输出到控制台
     * @param name  数据模板
     * @param dataModel  数据模型
     */
    public void print(String name, Map<String,Object> dataModel){
        try {
            Template template = this.getTemplate(name);
            //通过Template可以将模板文件输出到相应的流中
            template.process(dataModel, new PrintWriter(System.out));
            System.out.println();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据模型 和 数据模板 进行组合，结果输出到 指定文件
     * @param name 数据模板
     * @param dataModel 数据模型
     * @param outFile 指定输出文件
     */
    public void fprint(String name, Map<String,Object> dataModel, String outFile){
        FileWriter out = null;
        try {
            out = new FileWriter(new File("D:\\freemarker\\ftl\\" + outFile));
            Template template = this.getTemplate(name);
            template.process(dataModel, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            if(out!=null) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
