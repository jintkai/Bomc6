package com.code.testng;

import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * Created by jon on 2016/10/23.
 */

public class Test1 {

    /**
     * invocationCount:方法执行次数,串行方式。不是真正意义的并发。timeOut执行超时时长;
     */

    @Test(description = "test001",invocationCount = 2,timeOut=5000,dependsOnGroups ="test2")
    public void test()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Assert.assertTrue(false);
    }

    /**
     *  依赖:dependsOnMethods:String数组,缺点:参数是所依赖的方法名组成的数组,若方法名修改,此处必须修改;
     *  dependsOnGroups:参数为组名,组可以随时修改;
     *  testng会将相互依赖的方法放到同一个线程中执行;
     *  分组可以充分利用:比如按测试类型分、操作系统分、执行周期分等
     */
    @Test(description = "测试方法",invocationCount = 2,timeOut=4000,dependsOnMethods = "test")
    public void test2()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Test的目标可以放到类、方法、构造函数中,表示类中所有的public方法都是测试方法;
     */
    @Test(groups = "test2")
    public class Test2 {

        public void test222(){
            System.out.println("依赖test002");
        }
        private void test223(){
            System.out.println("私有方法不会执行");
        }
    }




}
