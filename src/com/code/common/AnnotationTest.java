package com.code.common;

import org.testng.annotations.Test;

/**
 * Created by jon on 2016/10/15.
 */
public class AnnotationTest {

    @Test

    public void test() {
        //检查类AnnotationTest是否含有@MyAnnotation注解
        if(AnnotationTest.class.isAnnotationPresent(MyAnnotation.class)){
            //若存在就获取注解
            MyAnnotation annotation=(MyAnnotation)AnnotationTest.class.getAnnotation(MyAnnotation.class);
            System.out.println(annotation);
            //获取注解属性
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            //数组
            int[] arrs=annotation.array();
            for(int arr:arrs){
                System.out.println(arr);
            }

        }
    }
}
