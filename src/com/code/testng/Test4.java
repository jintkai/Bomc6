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
        int r= (int) (Math.random()*10);
        if (r%10>3)
            Assert.assertTrue(false);
        else
            Assert.assertTrue(true);
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

    public void hello(){
        System.out.println("Hello");
    }

}
