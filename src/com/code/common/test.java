package com.code.common;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Jin on 2014/8/19.
 */
@Listeners({  DotTestListener.class })
public class test {
    @Test
    public test()
    {
        Assert.assertEquals(1,1);
        for (int i=1;i<50;i++){
            System.out.println(i);
            Assert.assertEquals(1,1);
        }
    }
    @Test
    public void test2(){
        WebElement a=new WebElement() {
            @Override
            public void click() {

            }

            @Override
            public void submit() {

            }

            @Override
            public void sendKeys(CharSequence... keysToSend) {

            }

            @Override
            public void clear() {

            }

            @Override
            public String getTagName() {
                return null;
            }

            @Override
            public String getAttribute(String name) {
                return null;
            }

            @Override
            public boolean isSelected() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public String getText() {
                return null;
            }

            @Override
            public List<WebElement> findElements(By by) {
                return null;
            }

            @Override
            public WebElement findElement(By by) {
                return null;
            }

            @Override
            public boolean isDisplayed() {
                return false;
            }

            @Override
            public Point getLocation() {
                return null;
            }

            @Override
            public Dimension getSize() {
                return null;
            }

            @Override
            public Rectangle getRect() {
                return null;
            }

            @Override
            public String getCssValue(String propertyName) {
                return null;
            }

            @Override
            public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
                return null;
            }
        };
        Object b="";
        Object c="";
        Integer d=0;
        if(a instanceof WebElement){
            System.out.println("element");
        }
        if(b instanceof String){
            System.out.println("String");
        }
        if(d instanceof Integer){
            System.out.println("Integer");
        }
        System.out.println();
    }
}
