package com.code.common;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by Jin on 2014/8/19.
 */
@Listeners({  DotTestListener.class })
public class test {
    @Test
    public test()
    {
        Assert.assertEquals(1,1);
        for (int i=1;i<50;i++){
            System.out.println(i);
            Assert.assertEquals(1,1);
        }
    }
}
