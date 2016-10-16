package com.code.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jinkai on 2014/6/21.
 * https://www.seleniummonster.com/eventfiringwebdriver-and-event-listeners-in-selenium/
 * http://facebook.github.io/php-webdriver/classes/ChromeDriver.html
 *
 * 脚本运行时,selenium提供了一些事件监听、跟踪的方法;
 * 监听器用于监听已经注册WebDriver的事件,比如页面跳转、点击元素、输入框输入、异常等;
 *
 * 步骤:
 * 1、创建自己的监听类;
 *    创建监听类的两种方法:
 *    1.1 实现WebDriverEventListener接口;
 *    1.2 继承AbstractWebDriverEventListener类;
 * 2、创建一个 EventFiringWebDriver对象,这是一个webdriver的实例;
 *      WebDriver driver=new FirefoxDriver();
 *      EventFiringWebDriver eventDriver=new EventFiringWebDriver(driver);
 * 3、EventFiringWebDriver实例中注册监听器;
 *      若存在多个监听器,可以注册多个监听器;
 *      eventDriver.register(new EventListener());
 *      eventDriver.findElement(By.id("id"));
 * 4、注销监听器:若不想使用监听器,则可以注销;
 *      eventDriver.unregister(new EventListener());
 *
 */
public class EventListener implements WebDriverEventListener {

    Logger mylog=Logger.getLogger(this.getClass());


    public EventListener(){
        PropertyConfigurator.configure( "./config/log4j.properties" );
        Logger logger  =  Logger.getLogger(this.getClass() );
    }
    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {


    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        //mylog.info("跳转url:"+s);
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
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(final By by, final WebElement webElement, WebDriver webDriver) {
        WebDriverWait wait=new WebDriverWait(webDriver,Data.timeOut/2,Data.sleepTime);
        if(webElement!=null)
        {
            //mylog.error("定位元素:"+by.toString());
            try {
                WebElement element = wait.until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        return webElement.findElement(by);
                    }
                });
            }
            catch(TimeoutException e)
            {
                mylog.error("定位元素超时."+by.toString());
            }
        }
        else
        {
            try {
                //mylog.error("定位元素:"+by.toString());
                WebElement element = wait.until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        return webDriver.findElement(by);
                    }
                });
            }
            catch(TimeoutException e)
            {
                mylog.error("定位元素超时."+by.toString());
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
                //System.out.println("点击元素失败");
            }
        }

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        /*mylog.info("点击按钮:"+" -> ["+webElement.getText()+"] -> "+webElement.toString());
        Reporter.log("点击按钮:"+" -> ["+webElement.getText()+"] -> "+webElement.toString());
        */

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
                mylog.error("修改元素值失败."+webElement);
            }
        }
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
        //mylog.info("修改元素"+webElement+"输入框值后:"+webElement.getAttribute("value"));
        //Reporter.log("修改元素"+webElement+"输入框值后:"+webElement.getAttribute("value"));
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
        Reporter.log("TakesScreenshot截图");
        String imageFormat = "png";// 图像文件的格式
        String picDir= Data.baseDir+"/pictures/";
        String str= String.valueOf((int)(Math.random()));
        File srcFile=((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        BufferedImage image = null;
        try {
            image = ImageIO.read(srcFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics g = image.getGraphics();
        g.setFont(new Font("Serif",Font.BOLD,15));
        g.setColor(Color.red);
        g.drawString(str, 10, 15);

        String r=String.valueOf((int)(Math.random()*10000));
        String filename=picDir+r+"."+imageFormat;
        try {
            ImageIO.write(image, "png", new File(filename));
            Reporter.log("onException:TakesScreenshot Save File:"+filename+"....Finished!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
