package com.code.testng.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.util.RetryAnalyzerCount;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by jon on 2016/10/25.
 * IRetryAnalyzer:testng接口,定义接口,若test方法执行失败,则可通过实现retry方法判断是否需要再次执行
 */
public class BnmsRetryListener implements IRetryAnalyzer {
    private int MAX_RETRY;
    int count=1;

    @Override
    public boolean retry(ITestResult result) {
        //获取testng.xml文件的MaxReties参数;
        String maxReties=(result.getTestContext().getSuite().getParameter("MaxReties"));
        if (maxReties ==null || maxReties.equals("")){
            MAX_RETRY=0;
        }else
        MAX_RETRY= Integer.parseInt(maxReties);

        if (count<MAX_RETRY){
            result.setAttribute("Retry",count);
            System.out.println("执行次数:"+count+";"+result.getMethod());
            count++;
            return true;
        }
        return false;
    }
}

