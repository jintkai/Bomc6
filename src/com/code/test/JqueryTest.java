package com.code.test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by Jin on 2014/8/4.
 */
public class JqueryTest {
        //WebDriver driver=new FirefoxDriver();
        //JavascriptExecutor jse=(JavascriptExecutor)driver;

        //@Test

    //@Test
    public void download()
    {
        //driver.get("http://eip.teamshub.com/t/1283566?ticket=ST-447328-e3ydFHZCAZJhMZMQyNGn-cas01.example.org");

        FirefoxProfile fxProfile = new FirefoxProfile();
        fxProfile.setPreference("browser.download.folderList",2);
        fxProfile.setPreference("browser.download.manager.showWhenStarting",false);
        fxProfile.setPreference("browser.download.dir","C:\\");
        fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/xls");
        fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/xls");
        fxProfile.setEnableNativeEvents(true);
        WebDriver driverf = new FirefoxDriver(fxProfile);
        driverf.get("http://172.21.214.75/redmine/issues/1735");
        driverf.findElement(By.name("username")).sendKeys("jinkai");
        driverf.findElement(By.name("password")).sendKeys("12345678");

        driverf.findElement(By.name("login")).click();
        WebElement link=driverf.findElement(By.partialLinkText("重庆联通综合代维管理系统需求跟踪矩阵.xls"));
        String href=link.getAttribute("href");
        driverf.navigate().to(href);
        //driver.get(href);
    }

}
