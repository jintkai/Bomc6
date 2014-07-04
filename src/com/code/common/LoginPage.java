package com.code.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by jinkai on 2014/6/22.
 */
public class LoginPage extends Page {
    @FindBy(how= How.ID,using="userId")
    WebElement userId;

    WebElement password;
    /*
    public LoginPage init()
    {
        DriverManager.setDriver(1);
        DriverManager.getEventDriver().get("http://172.21.0.31:8084");
        System.out.println(DriverManager.driver.getCurrentUrl());
        System.out.println(DriverManager.getEventDriver().getCurrentUrl());
        return  this;
    }
    */
    public HomePage login(String name,String passwd)
    {
        userId.clear();
        userId.sendKeys(name);
        password.clear();
        password.sendKeys(passwd);
        userId.submit();
        return new HomePage();
    }
}
