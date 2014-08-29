package com.cshuig.utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2014/8/29 0029.
 */
public class TestDateUtils {

    @Test
    public void testNowToTargetTime(){
        System.out.println(DateUtils.nowToTargetTime("2014-08-29 16:00:00"));
    }
}
