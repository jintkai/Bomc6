package com.code.itilManage.webProDesigner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.code.common.FormPage;
import com.code.common.Page;
import com.code.common.Tools;
import com.code.portal.main.MainPage;
import com.code.portal.main.PortalHeader;

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
