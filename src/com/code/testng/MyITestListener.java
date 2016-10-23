package com.code.testng;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by jon on 2016/10/23.
 */
public class MyITestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("执行成功");
        result.getTestName();
        System.out.println(result.getName()+"."+result.getTestClass()+","+result.getMethod());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("执行失败");
        System.out.println(result.getName()+"."+result.getTestClass()+","+result.getMethod());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("跳过执行");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("开始执行");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("结束执行");
    }
}
