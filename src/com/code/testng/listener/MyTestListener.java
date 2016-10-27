package com.code.testng.listener;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

import java.util.List;

/**
 * Created by jon on 2016/10/26.
 */
public class MyTestListener extends TestListenerAdapter {
    @Override
    public void onFinish(ITestContext testContext) {

    }
    @Override
    public void onTestFailure(ITestResult result){
        super.onTestFailure(result);
        String isRetryCase="";
        System.out.println("ISRETRYCASE:"+result.getAttribute("ISRETRYCASE"));
        if (result.getAttribute("ISRETRYCASE") !=null)
         isRetryCase= (String) result.getAttribute("ISRETRYCASE");
        System.out.println("isRetryCase:"+isRetryCase);
        if (isRetryCase.equals("YES")){
            System.out.println("无效用例");
            List<ITestResult> list=this.getFailedTests();
            list.remove(result);
            this.setFailedTests(list);
        }
    }

}
