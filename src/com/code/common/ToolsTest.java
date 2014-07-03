package com.code.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import javax.tools.Tool;

/**
 * Created by jinkai on 03/07/2014.
 */
public class ToolsTest  {
    WebDriver driver=TestCase.eventDriver;
    Tools tools=new Tools();

    @Test
    public void test()
    {

        driver.get("http://www.baidu.com");

        tools.executeByAutoIT(Data.baseDir + "res\\agent.exe");

        //tools.findBy(By.id("userId"));
        //tools.sendKeys(tools.findBy(By.id("userId")),"admin123456");
        tools.assertEquals("2","3");
    }

}
