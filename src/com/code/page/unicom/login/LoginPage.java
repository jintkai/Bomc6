package com.code.page.unicom.login;

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
    @FindBy(how= How.ID,using = "userName")
    WebElement userName;
    @FindBy(how=How.ID,using="password")
    WebElement password;
    @FindBy(how=How.ID,using="login-button")
    WebElement login_button;
    public void login(Map<String,String> map){
        tools.sendKeys(userName,tools.getMapValue(map,"用户名"));
        tools.sendKeys(password,tools.getMapValue(map,"密码"));
        tools.click(login_button);
    }
}
