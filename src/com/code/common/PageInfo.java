package com.code.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by jon on 2016/10/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, TYPE, CONSTRUCTOR, ElementType.FIELD})
public @interface PageInfo {
    String info() default "";
}



