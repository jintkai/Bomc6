package com.code.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jinkai on 2014/6/21.
 */
public class Page {
    public Actions actions=new Actions(TestCase.eventDriver);
    public Tools tools=new Tools();
    public Page()
    {
        PageFactory.initElements(TestCase.eventDriver,this);
    }
}
