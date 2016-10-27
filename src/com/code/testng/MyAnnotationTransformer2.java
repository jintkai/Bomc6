package com.code.testng;

import org.testng.IAnnotationTransformer2;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by jon on 2016/10/25.
 */
public class MyAnnotationTransformer2 implements IAnnotationTransformer2 {
    @Override
    public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        System.out.println("a:"+testMethod.getName()+",b:");
    }

    @Override
    public void transform(IDataProviderAnnotation annotation, Method method) {

    }

    @Override
    public void transform(IFactoryAnnotation annotation, Method method) {
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        System.out.println("d:"+annotation.getSuiteName());
    }
}
