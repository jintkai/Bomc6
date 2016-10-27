package com.code.testng;

import com.code.testng.annotation.ScheduledTest;
import com.code.testng.listener.BnmsRetryListener;
import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Created by jon on 2016/10/26.
 */
public class Test3 {

    @Test(retryAnalyzer=BnmsRetryListener.class)
    public void test1(){
        System.out.println("Test3-test1");
        Assert.assertTrue(false);
    }

    @ScheduledTest(activeAfter = "2016-10-28 12:00")
    @Test
    public void test2(){
        System.out.println("Test3-test2");
    }
}
