package com.code.portal.login;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Administrator on 16-5-30.
 */
public class LoginPage extends Page{
    public LoginPage(EventFiringWebDriver webDriver){super(webDriver);}
    @FindBy(how= How.ID,using = "username")
    WebElement userName;
    @FindBy(id="password")
    WebElement password;

    WebElement submitBtn;
    public void login(Map<String,String> map){
        tools.sendKeys(userName,tools.getMapValue(map,"用户名"));
        tools.sendKeys(password,tools.getMapValue(map,"密码"));
        tools.click(submitBtn);
    }
}
