package com.code.testng;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.code.common.PageInfo;

import java.lang.reflect.Field;

/**
 * Created by jon on 2016/10/27.
 */

public class Test4 {

    @PageInfo(info="主机名称")
    public String test="123";
    @PageInfo()
    public String test2="123";
    @PageInfo(info = "主机ip")
    public String test4="123";
    @Test(priority = 0,description = "失败的用例")
    public void test0400(){
        Assert.assertTrue(false);
    }

    @Test
    public void test8(){
        Field arr[]=this.getClass().getFields();
        for(int i=0;i<arr.length;i++){
            PageInfo pageInfo=arr[i].getAnnotation(PageInfo.class);
            if(pageInfo!=null)
            System.out.println("pageInfo:"+pageInfo.info());
        }
    }
    @Test(priority = 1,description = "随机的用例")
    public void test0401(){
        System.out.println("o4o1");
        int r=(int)(Math.random()*10);
        System.out.println("随机数:"+r);
        if (r>2)
            Assert.assertTrue(false);
        else
            Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = "test0401",description = "依赖随机用例")
    public void test0402(){
        System.out.println("o4o2");
    }

}
