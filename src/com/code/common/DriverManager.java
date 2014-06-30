package com.code.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import javax.security.auth.login.LoginException;

/**
 * Created by jinkai on 2014/6/21.
 */
public class DriverManager {
    public static WebDriver driver;
    public static EventFiringWebDriver eventDriver;
    public static EventListener logListener=new EventListener();
    public static void setDriver(int BrowerType)
    {
        driver=new FirefoxDriver();
        eventDriver=new EventFiringWebDriver(driver);
        eventDriver.register(logListener);
    }
    public static EventFiringWebDriver getEventDriver()
    {
        return eventDriver;
    }

}
