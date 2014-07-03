package com.code.common;

import junit.framework.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
            return false;
        }
    }

    public WebElement findBy(By by)
    {
        WebElement ele=null;
        if (isElementExist(by))
            ele=driver.findElement(by);
        return ele;
    }


    public void sendKeys(WebElement ele)
    {

    }
}
