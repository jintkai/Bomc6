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
            Thread.sleep(2000);
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

            ele.sendKeys(value);

    }
    public void clear(WebElement ele){ele.clear();}
    public void submit(WebElement ele){ele.submit();}
    public void selectByVisibleText(WebElement ele,String text)
    {
        try{(new Select(ele)).selectByVisibleText(text);}
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("select控件中无该值："+text);
            Reporter.log("select控件中无该值："+text);
            (new Select(ele)).selectByVisibleText(text);
        }
    }
    public String getTitle(WebDriver driver)
    {
        return driver.getTitle();
    }
    public String getAttribute(WebElement ele,String str)
    {
        return ele.getAttribute(str);
    }
    public void click(WebElement ele) {
        ele.click();
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
    public void assertEquals(int actual,Map<String,String> map,String exception)
    {
        try {
            Assert.assertEquals(actual, Integer.parseInt(this.getMapValue(map, exception)), "执行错误");
        }catch(AssertionError e) {
            System.out.println(map);
            Reporter.log("打印测试数据：" + String.valueOf(map));
            screen();
            System.out.println(e);
            Assert.assertEquals(actual, Integer.parseInt(this.getMapValue(map, exception)), "执行错误");
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
    public String swithToWindowByTitle(String title)
    {
        /*
        hand保存当前的hand
         */
        String hand=driver.getWindowHandle();
        Set<String> handsSet=driver.getWindowHandles();
        String handsStr[]=new String[handsSet.size()];
        handsSet.toArray(handsStr);
        for(int i=0;i<handsStr.length;i++)
        {
            driver.switchTo().window(handsStr[i]);
            if (driver.getTitle().contains(title))
                return hand;
        }
        Reporter.log("切换窗口失败，无法按Title切换窗口。");
        return hand;
    }
    public void switchToWindowByHand(String hand)
    {
        driver.switchTo().window(hand);
    }
    public void switchToWindos()
    {
        driver.switchTo().defaultContent();
    }
    public void switchToFrame(int index){
        driver.switchTo().defaultContent();
        driver.switchTo().frame(index);
    }
    public void switchToFrame(){
            driver.switchTo().defaultContent();
    }
    public void switchToFrame(String name)
    {
    driver.switchTo().frame(name);
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
        Reporter.log("截图");
        String imageFormat = "jpg";// 图像文件的格式
        String picDir= Data.baseDir+"\\pictures\\";
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
        String values=null;
        try{
             values=map.get(key);
        }
        catch(NullPointerException e)
        {
            Reporter.log("获取Map中key的value错误，不存在key："+key);
            System.out.println("获取Map中key的value错误，不存在key："+key);
            map.get(key);
        }
        return values;
    }
    public void alertAccept()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().alert().accept();
    }
    public void printMap(Map<String,String> map)
    {
        for (int i = 0; i <map.size() ; i++) {
            System.out.println(map.get(i));
        }
    }
}
