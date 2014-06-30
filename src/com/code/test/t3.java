package com.code.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by jinkai on 26/06/2014.
 */
public class t3 {
    WebDriver driver=new FirefoxDriver();
   @Test
   public void open()
   {
       driver.get("file:///C:/Users/jinkai/Desktop/tt.html");
       List<WebElement> webList=driver.findElements(By.xpath("//tr"));
       webList.get(1).findElement(By.xpath("./td")).getText();
   }
}
