package com.code.page.unicom;

import com.code.common.Data;
import com.code.common.TestCase2;
import com.code.unicom.login.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by Administrator on 16-5-30.
 */
public class LoginTest extends TestCase2 {
    LoginPage loginPage;
    @Parameters({"node"})
    public LoginTest(String node) {
        super(node);
        loginPage=new LoginPage(eventDriver);
    }
    @BeforeMethod

    public void beforeMethod()
    {
        eventDriver.get(Data.baseUrl);

    }
    @Test
    public void login(){
        map=new HashMap<String, String>();
        map.put("用户名","bj0001");
        map.put("密码","1");
        loginPage.login(map);
        tools.sleep(10000);
        Assert.assertEquals(tools.getDriver().getTitle(),"资源号卡管理","用户登录");
    }

}
