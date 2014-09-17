package com.code.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jinkai on 2014/6/21.
 */
public class Page {
    public EventFiringWebDriver eventDriver;
    public Actions actions;//=new Actions(eventDriver);
    public Tools tools;
    public Page(EventFiringWebDriver eventDriver)
    {
        this.eventDriver=eventDriver;
        PageFactory.initElements(eventDriver,this);
        actions=new Actions(eventDriver);
        tools=new Tools(eventDriver);
    }
    public Page()
    {

    }
}
