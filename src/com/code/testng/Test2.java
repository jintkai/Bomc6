package com.code.testng;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by jon on 2016/10/26.
 */

/**
 * @Test的目标可以放到类、方法、构造函数中,表示类中所有的public方法都是测试方法;
 */
@Test(groups = "test2")
public class Test2 {

    public void test222(){
        System.out.println("依赖test002");
        int r= (int) (Math.random()*10);
        if(r>5)
        Assert.assertTrue(false);
        else
            Assert.assertTrue(true);
    }
    private void test223(){
        System.out.println("私有方法不会执行");

    }

}
