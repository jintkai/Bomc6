package com.code.itilManage.webProDesigner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.code.common.Page;

public class GraphlistPage extends Page {
	
	
    public GraphlistPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    
    
    @FindBy(id="graphlist")
    WebElement graphlist;
    
    
    @FindBy(name="graphListArea")
    WebElement graphListArea;
    
    @FindBy(name="personActivityArea")
    WebElement personActivityArea;
    
    @FindBy(name="appealConfigArea")
    WebElement appealConfigArea;
    
    @FindBy(name="dictConfigArea")
    WebElement dictConfigArea;
    
    @FindBy(name="routingConfigArea")
    WebElement routingConfigArea;
    
    @FindBy(name="JSConfigArea")
    WebElement JSConfigArea;
    
    @FindBy(name="DBConfigArea")
    WebElement DBConfigArea;
    
    @FindBy(name="timeLimitConfigArea")
    WebElement timeLimitConfigArea;

}
