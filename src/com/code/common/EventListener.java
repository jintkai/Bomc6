package com.code.common;

import org.apache.bcel.ExceptionConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jinkai on 2014/6/21.
 */
public class EventListener implements WebDriverEventListener {

    Logger mylog=Logger.getLogger(this.getClass());
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
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {

        /*mylog.error(((JavascriptExecutor)webDriver).executeScript("return document.readyState"));
        */
        ExpectedCondition<Boolean> expection=new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete");
            }
        };

        //document.getElementById("kbpListFrame").contentWindow.document.readyState
        while(!((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"))
        {
            try {
                Thread.sleep(1000);
                mylog.error(((JavascriptExecutor)webDriver).executeScript("return document.readyState"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Wait<WebDriver> wait=new WebDriverWait(webDriver,30);
        wait.until(
                expection
        );

    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
       // mylog.info("AfterClickOn"+webElement.getTagName());
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {
        webElement.clear();
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

        mylog.error("ERROR--------------------");
    }
}
