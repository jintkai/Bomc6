package com.code.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 06/07/2014.
 */
public class FormPage extends  Page{
    @FindBy(how= How.ID,using="btn-submit")
    public  WebElement btnSubmit;

}
