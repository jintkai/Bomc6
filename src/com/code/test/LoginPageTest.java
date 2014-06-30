package com.code.test;

import com.code.common.LoginPage;
import com.code.common.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by jinkai on 2014/6/21.
 */
public class LoginPageTest extends TestCase {
    @Test
    public void login()
    {

        LoginPage loginPage=new LoginPage();
        TestCase.driver.get("http://172.21.0.31:8084");
        loginPage.login();
        Assert.assertTrue(driver.getTitle().contains("综合业务门户"),"Successful");

    }
}
