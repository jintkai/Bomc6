package com.code.common;

import junit.framework.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by jinkai on 02/07/2014.
 */
public class Tools {

    WebDriver driver= TestCase.eventDriver;

    public boolean isElementExist(SearchContext d,By by)
    {
        try {
            d.findElement(by);
            return true;
        }catch(NoSuchElementException e)
        {
            System.out.println(e);
            return false;
        }
    }
    public boolean isElementsExist(SearchContext d,By by)
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> list=d.findElements(by);
        if (list.size()>0)
            return  true;
            else
            return  false;
    }
    /*
    通过By类型查找元素；
     */
    public WebElement findBy(SearchContext d,By by)
    {
        WebElement ele=null;
        if (isElementExist(d,by))
            ele=d.findElement(by);
        return ele;
    }
    public java.util.List<WebElement> findElements(SearchContext d,By by)
    {
        List<WebElement> eles=null;
        if(isElementsExist(d,by))
        {
            eles=d.findElements(by);
        }
        return  eles;
    }

    public void sendKeys(WebElement ele,String value)
    {

        try {
            ele.sendKeys(value);
        }catch(Exception e)
        {
            System.out.println(e);
            screen();
            System.out.println("sendKeys元素失败");
        }
    }
    public void clear(WebElement ele){ele.clear();}
    public void submit(WebElement ele){ele.submit();}
    public void SelectByVisibleText(WebElement ele,String text)
    {
        (new Select(ele)).selectByVisibleText(text);
    }
    public String getTitle(WebDriver driver)
    {
        return driver.getTitle();
    }
    public void click(WebElement ele)
    {
        try {
            ele.click();
        }catch(Exception e)
        {
            System.out.println(e);
            screen();
            System.out.println("点击元素失败");
        }
    }
    public void assertEquals(Object actual,Object expected)
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
    public void assertTrue(boolean actual)
    {
        try {
            Assert.assertTrue(actual, "执行错误");
        }
        catch(AssertionError e){
            screen();
            System.out.println(e);
            Assert.assertTrue(actual, "执行错误");
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
    public void switchToFrame(){
        try {
            driver.switchTo().defaultContent();
        }
        catch(Exception e)
        {
            System.out.println("切换到默认frame失败");
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
        String imageFormat = "jpg";// 图像文件的格式
        String picDir="D:\\Test\\Bomc6\\pictures\\";
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            // 拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0, 0,(int) d.getWidth(), (int) d.getHeight() - 40));
            String filename = picDir + getCurrentDateTime() +"."+imageFormat;	//设置截屏保存的路径名称
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

    /*
        ****************************************************************************************************
        * 以下方法中主要对map进行操作。组合map，按key查找value
    */
    /*
    将两个String数组转换成一个Map对象
     */
    public Map changeStringToMap(String heads[],String values[])
    {
        Map<String ,String > map=new HashMap<String, String>();
        for (int i=0;i<heads.length;i++)
        {
            map.put(heads[i],values[i]);
        }
        return map;
    }

    public String getMapValue(Map<String,String> map,String key)
    {
        return map.get(key);
    }
    public void alertAccept()
    {
        driver.switchTo().alert().accept();
    }
}
