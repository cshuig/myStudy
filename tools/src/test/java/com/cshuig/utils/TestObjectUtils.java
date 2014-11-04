package com.cshuig.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/8/29 0029.
 */
public class TestObjectUtils {

    private List<String> datas;

    @Before
    public void setUp(){
        datas = new ArrayList<>();
        datas.add("0Z9999");
    }
    @Test
    public void testCode(){
        /**
         *  0A0001  -  0A9999
         *         ----
         *  0Z0001  -  0Z9999
         */
        String str = datas.get(0);
        char[] c = str.toCharArray();
        int s1 = Integer.parseInt(c[0]+"");//第一个：0～9数字
        char s2 = c[1];//第二个：A~Z字母
        int num = Integer.parseInt(str.substring(2));//获取后面的：4个数字
        int nextNum = num+1;//往后加1
        String results = "";
        if(nextNum > 9999 && s2 >= 'Z'){//当前已经到达  xZ9999，需要将x+1
            s1 +=1;
            if(s1 <= 9){
                results = s1 + "A" + this.fillZero("1");// 1A0001
            }
        }else{
            if(nextNum > 9999 && s2 < 'Z'){//例如：0A9999  ，需要将 A -> B  9999 -> 0001
                results = s1 + "" + (++s2)+ "" + this.fillZero("1");// 1A0001
            }else{
                results = s1 + "" + s2 + "" + this.fillZero(nextNum+"");// 1A0001
            }
        }
        System.out.println(results);
    }

    public String fillZero(String str){
        String results = str;
        for(int i=0; i<4-str.length() ;i++){
            results = "0" + results;
        }
        return results;
    }
}
