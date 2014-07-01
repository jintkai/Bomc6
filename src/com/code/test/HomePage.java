package com.code.test;

import com.code.common.Page;
import com.code.common.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by jinkai on 01/07/2014.
 */
public class HomePage extends TestCase {
    @BeforeMethod
    public void beforMethod()
    {
        TestCase.eventDriver.get("http://172.21.0.31:8084/ibnms/config/kbpListFrame.action");
    }
    @Test
    public void test()
    {
        eventDriver.switchTo().frame("kbpTree");
        eventDriver.findElement(By.id("fuzzy")).clear();
        eventDriver.findElement(By.id("fuzzy")).sendKeys("10-10");
        Actions action=new Actions(eventDriver);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        action.sendKeys(Keys.ARROW_DOWN);
        action.build().perform();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        action.sendKeys(Keys.ENTER);
        action.build().perform();
    }
}
