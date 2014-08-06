package com.code.test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by Jin on 2014/8/4.
 */
public class JqueryTest {
        WebDriver driver=new FirefoxDriver();
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        //@Test
        public void jQueryTest()
        {
            driver.get("http://www.jquery.com/");
            List<WebElement> elements=(List<WebElement>)jse.executeScript("return jQuery.find"+
                    "('.menu-item a:even')");
            assertEquals(3,elements.size());
            assertEquals("Download",elements.get(0).getText());
            assertEquals("Blog",elements.get(1).getText());
            assertEquals("Browser Support",elements.get(2).getText());
            driver.close();
        }

        public boolean jQueryLoaded(){
            boolean loaded;
            try {
                loaded = (Boolean) jse.executeScript("return jQuery()!=null");
            }catch(WebDriverException e)
            {
                loaded=false;
            }
            return loaded;
        }
        public void injectjQuery()
        {
            jse.executeScript(
                    "var headID=document.getElementsByTagName('head')[0];"
            +"var newScript=document.createElement('script');"
            +"newScript.type='text/javascript';"
            +"newScript.src='http://code.jquery.com/jquery-1.11.1.min.js';"
            +"headID.appendChild(newScript);");
        }
        public void injectjQueryIfNeeded()
        {
            if(!jQueryLoaded())
                injectjQuery();
        }
//        @Test
        public void test()
        {
            driver.get("http://www.dsrj.com.cn/");
            injectjQueryIfNeeded();
            List<WebElement> element=(List<WebElement>)jse.executeScript("return jQuery.find(\".nav3\")");
            System.out.println(element.get(0).getTagName());
            element.get(0).findElement(By.xpath(".//a")).click();
            driver.close();
        }
     @Test
        public void testTakesScreenshot()
        {
            WindowsUtils.tryToKillByName("firefox.exe");
            driver.get("http://172.21.0.31:8084");
            File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile,new File("C:\\screenshot2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //driver.close();

        }
}
