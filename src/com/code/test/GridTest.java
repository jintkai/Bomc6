package com.code.test;

import com.code.common.GridTablePage2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by jinkai on 04/07/2014.
 */
public class GridTest {
    GridTablePage2 grid=null;
    @Test
    public void test()
    {
        WebDriver driver=new FirefoxDriver();
        driver.get("http://172.21.0.31:8084");
        driver.findElement(By.id("userId")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("password")).submit();
        driver.get("http://172.21.0.31:8084/ibnms/config/kbpList.action");
        grid=new GridTablePage2(driver);
        grid.getHead();

    }
}
