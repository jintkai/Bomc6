package com.code.test;

import com.code.common.ExcelMapDriver;
import com.code.common.LoginPage;
import com.code.common.TestCase;
import com.code.common.Tools;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jinkai on 2014/6/21.
 */
public class LoginPageTest  {
    String[] excelHead;
    Tools tools=new Tools();
    @DataProvider(name="test")
    public Iterator test() throws IOException, BiffException {
        ExcelMapDriver excelDriver=new ExcelMapDriver("KBP","Login");
        excelHead=excelDriver.getHead(0);
        return  excelDriver;
    }

    @Test(dataProvider="test")
    public void login(String[] obj)
    {
        WebDriver driver=new FirefoxDriver();
        Map<String,String> map=tools.change(excelHead, obj);
        //LoginPage loginPage=new LoginPage();
        driver.findElement(By.id("userId")).sendKeys(map.get("用户名"));
        driver.findElement(By.id("password")).sendKeys(map.get("密码"));
        driver.findElement(By.id("password")).submit();
        Assert.assertTrue(driver.getTitle().contains("综合业务门户"),"Successful");

    }

}
