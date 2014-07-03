package com.code.common;

import junit.framework.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jinkai on 02/07/2014.
 */
public class Tools {

    WebDriver driver= TestCase.eventDriver;

    public boolean isElementExist(By by)
    {
        try {
            driver.findElement(by);
            return true;
        }catch(NoSuchElementException e)
        {
            System.out.println(e);
            return false;
        }
    }
    /*
    通过By类型查找元素；
     */
    public WebElement findBy(By by)
    {
        WebElement ele=null;
        if (isElementExist(by))
            ele=driver.findElement(by);
        return ele;
    }


    public void sendKeys(WebElement ele,String value)
    {
        ele.sendKeys(value);
    }

    public void clinck(WebElement ele)
    {
        ele.click();
    }
    public void assertEqual(String actual,String expected)
    {
        try {
            Assert.assertEquals(actual, expected, "执行错误");
        }catch(AssertionError e){
            screen();
            System.out.println(e);
            Assert.assertEquals(actual, expected, "执行错误");
            driver.close();
        }
    }
    public void switchToFrame(int index){
        driver.switchTo().defaultContent();
        try {driver.switchTo().frame(index);}
        catch(Exception e)
        {
            System.out.println("通过index跳转iframe时错误");
            System.out.println(e);
            screen();
        }
    }
    public void switchToFrame(String name)
    {
        driver.switchTo().defaultContent();
        try{driver.switchTo().frame(name);}
        catch(Exception e)
        {
            System.out.println("通过name跳转iframe时错误");
            System.out.println(e);
            screen();
        }
    }
    public void switchToFrame(WebElement ele)
    {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(ele);
    }



    public String getCurrentDateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd-HHmmss");
        dateFormat.format(new Date());
        return dateFormat.format(new Date());
    }
    /*
    负责进行屏幕截图
     */
    public void screen()
    {
        String imageFormat = ".bmp";// 图像文件的格式
        String picDir="D:\\Test\\Bomc6\\pictures\\";
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            // 拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0, 0,(int) d.getWidth(), (int) d.getHeight() - 40));
            String filename = picDir + getCurrentDateTime() + imageFormat;	//设置截屏保存的路径名称
            File f = new File(filename);
            System.out.print("Save File " + filename);
            ImageIO.write(screenshot, imageFormat, f);		// 将screenshot对象写入图像文件
            System.out.print("..Finished!\n");
            Reporter.log("picName:" + filename);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    /*
    通过AntoIT来处理安全、下载等对话框；
     */
    public void executeByAutoIT(String path)
    {
        Runtime rt=Runtime.getRuntime();
        try {
            rt.exec(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
