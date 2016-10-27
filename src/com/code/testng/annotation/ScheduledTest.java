package com.code.testng.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
/**
 * Created by jon on 2016/10/26.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD,TYPE,CONSTRUCTOR})
public @interface ScheduledTest {
    String activeAfter() default "";
}
