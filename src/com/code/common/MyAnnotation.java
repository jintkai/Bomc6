package com.code.common;

/**
 * Created by jon on 2016/10/15.
 */
public @interface MyAnnotation {
    //为注解添加属性
    String color();

    String value() default "我是..."; //为属性提供默认值

    int[] array() default {1, 2, 3};
}
