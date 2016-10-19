package com.code.test.itilManage;

import com.code.common.TestCase2;
import com.code.portal.login.LoginPage;
import com.code.portal.main.MainPage;
import com.code.portal.main.PortalHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.code.itilManage.webProDesigner.page.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jon on 2016/10/13.
 */
public class MainTest extends TestCase2 {
    LoginPage loginPage;
    MainPage    mainPage;
    GraphlistlinkPage graphlistlinkPage;
   // @Parameters({"node"})
    public MainTest() {
        super("");
        loginPage=new LoginPage(eventDriver);
        mainPage=new MainPage(eventDriver);
        graphlistlinkPage=new GraphlistlinkPage(eventDriver);
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
        PortalHeader header=new PortalHeader(eventDriver);
        header.selectItem("服务管理测试");
        MainPage mainPage=new MainPage(eventDriver);
    }
    @Test
    public void selectProcess(){
       // PortalHeader header=new PortalHeader(eventDriver);
       // header.selectItem("服务管理测试");
       // MainPage mainPage=new MainPage(eventDriver);
        //WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        //tools.switchToFrame(e);
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        tools.switchToFrame(e);
    	graphlistlinkPage.selectMenu("流程图列表");
    	
    }
    @Test
    public void selectForm(){
       // PortalHeader header=new PortalHeader(eventDriver);
       // header.selectItem("服务管理测试");
       // MainPage mainPage=new MainPage(eventDriver);
        //WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        //tools.switchToFrame(e);
    	
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        tools.switchToFrame(e);
    	graphlistlinkPage.selectMenu("表单配置");
    	
    }
    @Test
    public void selectDataConf(){
       // PortalHeader header=new PortalHeader(eventDriver);
       // header.selectItem("服务管理测试");
       // MainPage mainPage=new MainPage(eventDriver);
        //WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        //tools.switchToFrame(e);
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        tools.switchToFrame(e);
    	graphlistlinkPage.selectMenu("数据字典");
    	
    }
}
