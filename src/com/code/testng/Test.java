package com.code.testng;

import org.testng.Assert;

/**
 * Created by jon on 2016/10/23.
 */
public class Test {
    @org.testng.annotations.Test
    public void test()
    {
        System.out.println("1+1=2");
    }
    @org.testng.annotations.Test
    public void test2()
    {
        System.out.println("1+1=3");
        Assert.assertTrue(false,"错误");
    }
}
