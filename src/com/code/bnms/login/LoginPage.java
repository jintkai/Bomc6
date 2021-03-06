package com.code.bnms.login;

import com.code.common.HomePage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jinkai on 2014/6/22.
 */
public class LoginPage extends Page {
    @FindBy(how= How.ID,using="userId")
    WebElement userId;

    WebElement password;

    public LoginPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    public HomePage login(String name,String passwd)
    {
        tools.sendKeys(userId,name);
        tools.sendKeys(password, passwd);
        tools.sleep();
        tools.submit(userId);
        return new HomePage();
    }
}
