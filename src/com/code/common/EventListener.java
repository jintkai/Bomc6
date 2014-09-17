package com.code.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jinkai on 2014/6/21.
 */
public class EventListener implements WebDriverEventListener {

    Logger mylog=Logger.getLogger(this.getClass());
    //public Tools tools=new Tools();
    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {

        mylog.info("beforeNavigateTo:"+s);

    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(final By by, final WebElement webElement, WebDriver webDriver) {
        WebDriverWait wait=new WebDriverWait(webDriver,Data.timeOut/2,Data.sleepTime);
        if(webElement!=null)
        {
            try {
                WebElement element = wait.until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        //System.out.println("beforeFindBy:>>>>>>"+webElement.getTagName()+">>>>>>"+Tools.getTime());
                        return webElement.findElement(by);
                    }
                });
            }
            catch(TimeoutException e)
            {
                //e.printStackTrace();
                System.out.println("定位元素超时");
            }
        }
        else
        {
            try {
                WebElement element = wait.until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        return webDriver.findElement(by);
                    }
                });
            }
            catch(TimeoutException e)
            {
                //e.printStackTrace();
                System.out.println("定位元素超时");
            }
        }

    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeClickOn(final WebElement webElement, WebDriver webDriver) {
        WebDriverWait wait=new WebDriverWait(webDriver,Data.timeOut,Data.sleepTime);
        if(webElement!=null)
        {
            System.out.println(webElement.getTagName());
            try {
                Boolean element = wait.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webElement.isDisplayed();
                    }
                });
            }
            catch(TimeoutException e)
            {
                //e.printStackTrace();
                System.out.println("点击元素失败");
            }
        }

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
       // mylog.info("AfterClickOn"+webElement.getTagName());
    }

    @Override
    public void beforeChangeValueOf(final WebElement webElement, WebDriver webDriver) {
        WebDriverWait wait=new WebDriverWait(webDriver,Data.timeOut,Data.sleepTime);
        if(webElement!=null)
        {

            try {
                Boolean element = wait.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webElement.isDisplayed();
                    }
                });
            }
            catch(TimeoutException e)
            {
                System.out.println("BeforeChangeValueOf失败");
            }
        }
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {


    }
}
