package com.code.common;

import junit.framework.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by jinkai on 02/07/2014.
 * 该类主要对selenium等方法进行了封装，并且封装了一些常用的方法；
 */
public class Tools {

    WebDriver driver= TestCase.eventDriver;
    public WebDriver getDriver()
    {
        return driver;
    }
    /**
    *描述判断元素是否存在；
     * 根据传入的父节点，以及待定位节点的定位方式，返回节点是否存在；
     *@param d   表示父节点；
     *@param by By类型，待查询的子节点元素定位方式。
     *@return 若子节点在父节点中存在则返回true，否则返回false；
    */
    public boolean isElementExist(SearchContext d,By by)
    {
        try {
            d.findElement(by);
            return true;
        }catch(NoSuchElementException e)
        {
            //System.out.println(e);
            Reporter.log("findElement【"+by+"】失败。");
            //screen();
            takesScreenshot("WebElement不存在:"+by);
            d.findElement(by);
            return false;
        }
    }
    /**
     *描述判断元素是否存在；
     * 该方法主要用于查询多个元素组，由于查询元素组时存在响应时间问题，
     * 姑使用sleep来强制等待1s，等待页面加载完成；
     * 根据传入的父节点，以及待定位节点的定位方式，返回节点是否存在；
     *@param d   表示父节点；
     *@param by By类型，待查询的子节点元素定位方式。
     *@return 若子节点在父节点中存在则返回true，否则返回false；
     */
    public boolean isElementsExist(SearchContext d,By by)
    {
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        List<WebElement> list=d.findElements(by);
        if (list.size()>0)
            return  true;
        else {
            System.out.println("无法定位元素集，通过："+by);

            return false;
        }
    }
    /**
     *查找元素是否存在；
     *根据传入值来判断待查找的元素是否存在
     *@param d   表示父节点；
     *@param by By类型，待查询的子节点元素定位方式。
     *@return 返回待查找的元素；
     */
    public WebElement findBy(SearchContext d,By by)
    {
        WebElement ele=null;
        if (isElementExist(d,by))
            ele=d.findElement(by);
        return ele;
    }
    /**
     *查找元素组是否存在；
     *根据传入值来判断待查找的元素是否存在
     *@param d   表示父节点；
     *@param by By类型，待查询的子节点元素定位方式。
     *@return 返回待查找的元素；
     */
    public java.util.List<WebElement> findElements(SearchContext d,By by)
    {
        List<WebElement> eles=null;
        if(isElementsExist(d,by))
        {
            eles=d.findElements(by);
        }
        return  eles;
    }
    /**
     *对指定的WebElement中输入字符；
     */
    public void sendKeys(WebElement ele,String value)
    {
        if(! value.isEmpty()) {
            clear(ele);
            ele.sendKeys(value);
        }
        else
        {
            System.out.println("未输入值，不用处理");
        }

    }
    public void clear(WebElement ele){
        try {
            ele.clear();
        }catch(Exception e)
        {
            e.printStackTrace();
            Reporter.log("清除元素内容失败");
            takesScreenshot("调用WebElement.clear()方法失败");
            ele.clear();
        }
    }
    public void submit(WebElement ele){ele.submit();}
    public void selectByVisibleText(WebElement ele,String text)
    {
        if(text==null)
            return;
        try{
            if(!text.isEmpty())
            (new Select(ele)).selectByVisibleText(text);}
        catch(Exception e)
        {
            e.printStackTrace();
            //System.out.println("select控件中无该值："+text);
            Reporter.log("Select控件中无法通过该值：【"+text+"】进行选择");
            takesScreenshot("Select控件中无法通过该值：【"+text+"】进行选择");
            (new Select(ele)).selectByVisibleText(text);
        }
    }

    public List<WebElement> getOptions(WebElement ele)
    {
        return new Select(ele).getOptions();
    }
    public String getTitle(WebDriver driver)
    {
        return driver.getTitle();
    }

    /**
     *返回WebElement某个属性的属性值；
     * @param ele WebElement元素
     * @param str Attribute属性
     * @return 返回某元素的某个属性值；
     */
    public String getAttribute(WebElement ele,String str)
    {
        return ele.getAttribute(str);
    }
    public void click(WebElement ele) {
        try{ele.click();}
        catch(Exception e)
        {
            e.printStackTrace();
            Reporter.log("点击元素失败！");
            takesScreenshot("点击元素失败!");
            ele.click();
        }
    }

    public void assertEquals(Object actual,Object expected,Map<String,String> map)
    {
        try {
            Assert.assertEquals(actual, expected, map.toString());
        }catch(AssertionError e){
            e.printStackTrace();
            takesScreenshot(getMapValue(map,"用例编号")+":"+getMapValue(map,"用例描述")+"------------实际值>>>>>"+actual.toString()+"；期望值>>>>>"+expected.toString());
            Reporter.log(map.toString());
            Assert.assertEquals(actual, expected, map.toString());

        }
    }

    public void assertNotEquals(Object actual,Object expected,Map<String,String> map) {
        try {
            Assert.assertNotEquals(actual, expected, map.toString());
        } catch (AssertionError e) {
            //screen();
            takesScreenshot(getMapValue(map,"用例编号")+":"+getMapValue(map,"用例描述")+"------------实际值>>>>>"+actual.toString()+"；期望值>>>>>"+expected.toString());
            e.printStackTrace();
            Reporter.log(map.toString());
            Assert.assertNotEquals(actual, expected, map.toString());
        }
    }
    /**
     *封装Testng的AssertEquals方法；
     * @param  actual 实际值
     * @param  msg 提示信息
     * @param exception map中的key，通过map，exception来返回预期值；
     */

    public void assertEquals(int actual,String exception,String msg)
    {
        try {
            Assert.assertEquals(actual, Integer.parseInt( exception), msg);
        }catch(AssertionError e) {

            takesScreenshot(msg);
            Assert.assertEquals(actual, Integer.parseInt( exception), msg);
        }
    }
    /**
     *封装Testng的AssertEquals方法；
     * @param  actual 实际值
     * @param  msg 错误提示信息
     * @param exception map中的key，通过map，exception来返回预期值；
     */
    public void assertEquals(String actual,String exception,String msg)
    {
        try {
            Assert.assertEquals(actual, exception, msg);
        }catch(AssertionError e) {

            takesScreenshot(msg);
            System.out.println(e);
            Assert.assertEquals(actual, exception, msg);
        }
    }
    public void assertTrue(boolean actual,String msg)
    {
        try {
            Assert.assertTrue(actual,msg);
        }
        catch(AssertionError e)
        {
            takesScreenshot(msg);
            System.out.println(e);
            Assert.assertTrue(actual,msg);
        }
    }

    /**
     * 通过窗口title来跳转窗口
     * @param title windows的title
     * @return 返回跳转前的窗口句柄
     */
    public String switchToWindowByTitle(String title)
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        Reporter.log("切换窗口失败，无法按Title【"+title+"】切换窗口。");
        takesScreenshot("切换窗口失败，无法按Title【"+title+"】切换窗口。");
        return "FALSE";
    }
    /**
     * 通过窗口句柄来切换窗口
     */
    public void switchToWindowByHand(String hand)
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            driver.switchTo().window(hand);
        }catch(NoSuchWindowException e)
        {
            Reporter.log("切换窗口失败，无法按Hand【"+hand+"】切换窗口。");
            takesScreenshot("切换窗口失败，无法按Hand【"+hand+"】切换窗口。");
        }
    }
    /**
     * 切换到默认的窗口
     */
    public void switchToWindos()
    {
        driver.switchTo().defaultContent();
    }

    /**
     * 根据Frame的index来切换Frame
     * @param index
     */
    public void switchToFrame(int index){
        driver.switchTo().defaultContent();
        driver.switchTo().frame(index);
    }
    /**
     * 根据到默认的Frame
     */
    public void switchToFrame(){
            driver.switchTo().defaultContent();
    }
    /**
     * 根据Frame的name,id来切换Frame
     * @param name
     */
    public void switchToFrame(String name)
    {
        try{
            driver.switchTo().frame(name);
        }
        catch(NoSuchFrameException e)
        {
            Reporter.log("无法切换IFrame："+name);
            takesScreenshot("无法切换IFrame："+name);
        }
    }
    /**
     * 根据WebElement来切换Frame
     * @param ele
     */
    public void switchToFrame(WebElement ele)
    {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(ele);
    }


    /**
     * 根据当前时间生成时间字符串
     * @return
     */
    public String getCurrentDateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.format(new Date());
        return dateFormat.format(new Date());
    }

    /**
     *对当前屏幕进行截图
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
    /**
     * 截图
     */
    public void takesScreenshot()
    {
        Reporter.log("TakesScreenshot截图");
        String imageFormat = "png";// 图像文件的格式
        String picDir= Data.baseDir+"\\pictures\\";
        File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filename=picDir+getCurrentDateTime()+"."+imageFormat;
        try {
            FileUtils.copyFile(srcFile,new File(filename));
            System.out.print("TakesScreenshot Save File:"+filename+"....Finished!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void takesScreenshot(String str)
    {
        Reporter.log("TakesScreenshot截图");
        String imageFormat = "png";// 图像文件的格式
        String picDir= Data.baseDir+"\\pictures\\";
        File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
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


        String filename=picDir+getCurrentDateTime()+"."+imageFormat;
        try {
            ImageIO.write(image, "png", new File(filename));
            System.out.print("TakesScreenshot Save File:"+filename+"....Finished!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *执行AntoIT脚本
     * @param path AntoIT脚本文件的位置
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

    /**
     * 将2个字符串数组组合成一个Map对象
     * @param keys String对象，用于Map的key；
     * @param values String对象，用于Map的value
     * @return 返回组合的Map对象
     */
    public Map changeStringToMap(String keys[],String values[])
    {
        Map<String ,String > map=new HashMap<String, String>();
        for (int i=0;i<keys.length;i++)
        {
            map.put(keys[i],values[i]);
        }
        return map;
    }
    /**
     * 返回Map中key的值
     * @param map
     * @param key
     * @return
     */
    public String getMapValue(Map<String,String> map,String key)
    {
        String value="";
        boolean isExeist=false;
        isExeist=map.containsKey(key);
        if(!isExeist)
        {
            System.out.println("Key("+key+")不存在");
            return  value;
        }
        try{
             value=map.get(key);
        }
        catch(NullPointerException e)
        {
            System.out.println("获取value失败，key："+key);
            value="";
        }
        return value;
    }

    /**
     * 处理Alert
     */
    public void alertAccept()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().alert().accept();
    }
    public void alarmDismiss()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().alert().dismiss();
    }
    public void alertSetText(String str)
    {
        driver.switchTo().alert().sendKeys(str);
        driver.switchTo().alert().accept();
    }
    public void printMap(Map<String,String> map)
    {
        for (int i = 0; i <map.size() ; i++) {
            System.out.println(map.get(i));
        }
    }



    public static void highlightElement(WebElement element){
        for (int i = 0; i < 3; i++) {
            WrapsDriver wrappedElement = (WrapsDriver) element;
            JavascriptExecutor driver = (JavascriptExecutor) wrappedElement
                    .getWrappedDriver();
//为元素设置style来高亮
            try {
                driver.executeScript(
                        "arguments[0].setAttribute('style',arguments[1]);",
                        element, "color: green; border: 2px solid yellow;");
//取消高亮将style清掉
                Thread.sleep(800);
                driver.executeScript(
                        "arguments[0].setAttribute('style',arguments[1]);",
                        element, "");
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理模态对话框
     * @param element
     */
    public void openModelDialog(WebElement element)
    {
        element.sendKeys("a");
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void moveToElement(WebElement element)
    {
        Actions builder=new Actions(driver);
        builder.moveToElement(element).perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void setScroll(int height){

        try {
            String setscroll = "document.documentElement.scrollTop=" + height;
            JavascriptExecutor jse=(JavascriptExecutor) driver;
            jse.executeScript(setscroll);
        }
        catch (Exception e) {
            System.out.println("Fail to set the scroll.");
            Reporter.log("Fail to set the scroll.");
        }
    }
    public void  execJS(String js)
    {
        try {
            Thread.sleep(2000);
            JavascriptExecutor exec = (JavascriptExecutor) driver;
            exec.executeScript(js);

        }
        catch(Exception e)
        {
            System.out.println("执行JavaScript脚本失败");
            Reporter.log("执行JavaScript脚本失败");
            JavascriptExecutor exec = (JavascriptExecutor) driver;
            exec.executeScript(js);
        }
    }
    public void closeWindow()
    {
        driver.close();
    }
    public void closeModelDialog()
    {
        String js="window.open(\"\", \"_self\");window.close();";
        execJS(js);
    }

}
