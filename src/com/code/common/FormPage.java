package com.code.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jinkai on 06/07/2014.
 */
public class FormPage extends  Page{
    public FormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(how= How.ID,using="btn-submit")
    public  WebElement btnSubmit;



}
