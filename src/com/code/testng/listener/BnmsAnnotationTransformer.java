package com.code.testng.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by jon on 2016/10/25.
 * AnnotationTransformer:
 * 解决的几个问题:
 * 1、如果@Test要加一些属性,存在大量方法时如何解决;
 * 2、如果要修改一些属性,如何解决;
 * transform的后面3个参数:分别表示@Test注解的作用域:类、方法、构造器。三个参数不能同时存在;
 * 用法:
 * 主要用于动态修改@Test属性;
 * 1、timeOut:设置超时时间;
 * 2、enabled:修改enable标识,相比group等更加灵活;
 * 3、invocationCount:修改方法的执行次数,测试线程安全问题;
 * 4、threadPoolSize:可以读取当前计算机配置信息,比如cpu。动态调整线程池个数;
 * 5、successPercentage:与invocation一块使用,允许一定的失败率;
 * 6、description:
 */
public class BnmsAnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

        annotation.setDescription(""+annotation.getDescription());
        IRetryAnalyzer analyzer=annotation.getRetryAnalyzer();
        if (analyzer==null){
            annotation.setRetryAnalyzer(BnmsRetryListener.class);
        }
    }
}
