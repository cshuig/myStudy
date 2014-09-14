package com.cshuig.utils;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  类的功能说明：
 *  创建者：Administrator
 *  创建时间:2014年9月13日 下午3:54:10
 */
public class ObjectUtils {

	public static void testNull(Map<String, Object> params){
		//字符串非空判断
		System.out.println((String)params.get("name"));
        System.out.println((String)params.get("nick"));

        System.out.println(((Boolean)params.get("enabled")));
        System.out.println(((Boolean)params.get("disabled"))==null);


        System.out.println(((Date)params.get("date")).getTime());
        System.out.println(((Date)params.get("dates"))==null);

        String agentShopName = String.format("%s的小店", "貂蝉");
        System.out.println(agentShopName);
	}
	
	public static void main(String[] args) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", "张三");
		params.put("enabled", true);
		params.put("date", new Date());
		ObjectUtils.testNull(params);
	}


    /**
     * 判断一个Object是否为空
     * 目前判断的类型有：
     *      String
     *      Collection
     *      Map
     *      Object[]
     * @param obj
     * @return
     */
    public boolean isNullOrEmpty(Object obj){
        if(obj == null){
            return true;
        }
        //判断obj是否是字符串类型
        if(obj instanceof CharSequence){
            return ((CharSequence)obj).length() == 0;
        }

        //判断obj：Boolean类型
        if(obj instanceof Boolean){
            return (Boolean)obj == null ? true:(Boolean)obj;
        }

        //判断obj：Collection类型
        if(obj instanceof Collection){
            return ((Collection) obj).isEmpty();
        }

        //判断obj :Map类型
        if(obj instanceof Map){
            return ((Map)obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }

        return false;
    }
}
