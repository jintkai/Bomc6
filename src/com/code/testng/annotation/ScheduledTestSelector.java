package com.code.testng.annotation;

import org.testng.IMethodSelector;
import org.testng.IMethodSelectorContext;
import org.testng.ITestNGMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jon on 2016/10/26.
 */
public class ScheduledTestSelector implements IMethodSelector {
    @Override
    public boolean includeMethod(IMethodSelectorContext context, ITestNGMethod method, boolean isTestMethod) {

        System.out.println("includeMethod:---------");
        Method method1=method.getMethod();
        Annotation annotation=method1.getAnnotation(ScheduledTest.class);
        if(annotation!=null){
            Date now= Calendar.getInstance().getTime();
            System.out.println("Now:"+now);
            ScheduledTest scheduledTest=(ScheduledTest) annotation;
            Date activeAfter=null;
            String activeAfterString=scheduledTest.activeAfter();

            if(activeAfterString!=null && !activeAfterString.equals("")){
                activeAfter=parse(activeAfterString);
                System.out.println("activeAfterString:"+activeAfter);
                if (now.before(activeAfter)){
                    System.out.println(">>>>>>Now:"+now);
                    System.out.println(">>>>>>activeAfterString:"+activeAfter);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void setTestMethods(List<ITestNGMethod> testMethods) {

    }
    private Date parse(String strDate) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
