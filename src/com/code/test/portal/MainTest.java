package com.code.test.portal;

import com.code.common.TestCase2;
import com.code.portal.login.LoginPage;
import com.code.portal.main.MainPage;
import com.code.portal.main.PortalHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jon on 2016/10/13.
 */
public class MainTest extends TestCase2 {
    LoginPage loginPage;
    MainPage    mainPage;
    PortalHeader header;
    //@Parameters({"node"})
    public MainTest() {
        super("");
        loginPage=new LoginPage(eventDriver);
        mainPage=new MainPage(eventDriver);
        header=new PortalHeader(eventDriver);
    }
    @BeforeMethod
    public void beforeMethod()
    {
        //eventDriver.get(Data.baseUrl);
        eventDriver.get("http://172.21.10.31:10005/portal");
        map=new HashMap<String, String>();
        map.put("用户名","admin");
        map.put("密码","12345678");
        loginPage.login(map);

    }
    @Test
    public void selectItem(){

        header.selectItem("服务管理测试");
        MainPage mainPage=new MainPage(eventDriver);
        WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        tools.switchToFrame(e);

        List<WebElement> elementList=tools.findElements(eventDriver, By.className("menuLable"));

        for(WebElement tr:elementList){
            System.out.println(elementList.size());
            System.out.println(tr.getText());
        }
        tools.sleep(5000);
        Assert.assertEquals(tools.getDriver().getTitle(),"服务管理测试","服务管理");
    }
    @AfterMethod
    public void afterMethod(){
        mainPage.logout();
    }
}
