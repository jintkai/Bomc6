package com.code.testng.listener;

import com.code.common.Data;
import com.code.common.TestCase;
import com.code.testng.Test4;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jon on 2016/10/27.
 */
public class BnmsInvokedMethodListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        int count=0;
        if(testResult.getAttribute("Retry")!=null){
            count=(int)testResult.getAttribute("Retry");
        }

        int MAX_RETRY=0;
        String maxReties=testResult.getTestContext().getSuite().getParameter("MaxReties");
        if (maxReties ==null || maxReties.equals("")){
            MAX_RETRY=0;
        }else{
            MAX_RETRY= Integer.parseInt(maxReties);
            if (testResult.getStatus()== org.testng.ITestResult.FAILURE && count<MAX_RETRY){
                testResult.setAttribute("ISRETRYCASE","YES");
                System.out.println("需要重复执行,不需要统计用例"+count);
            }
        }



    }
}
