package com.code.test.itilManage;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.code.common.TestCase2;
import com.code.portal.login.LoginPage;
import com.code.portal.main.MainPage;
import com.code.itilManage.formConfig.*;
import com.code.portal.main.PortalHeader;
import com.code.itilManage.webProDesigner.page.*;
public class FormConfTest extends TestCase2{

    LoginPage loginPage;
    MainPage    mainPage;
    PortalHeader header;
    FormSelectPage formselectPage;
    FormConfPage FormconfPage;
    GraphlistlinkPage graphlistlinkPage;
    //@Parameters({"node"})
	public FormConfTest() {
        super("");
        loginPage=new LoginPage(eventDriver);
        mainPage=new MainPage(eventDriver);
        header=new PortalHeader(eventDriver);
        graphlistlinkPage=new GraphlistlinkPage(eventDriver);
        FormconfPage=new FormConfPage(eventDriver);
        formselectPage=new FormSelectPage(eventDriver);
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
        /*
		MainTest maintest = new MainTest();
		maintest.beforeMethod();
		maintest.selectProcess();		*/
        }
		
		
    @Test
    public void addElement(){
    	
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        tools.switchToFrame(e); 
        graphlistlinkPage.selectMenu("流程图列表");
        tools.switchToFrame(e); 
        graphlistlinkPage.selectMenu("表单配置");
        tools.switchToFrame(e); 
     
      // WebElement e1=formselectPage.switchWindow("/itilManage/getAppealList.do");    	
       //tools.switchToFrame2(e1);   
        formselectPage.formConf();
        FormconfPage.selectOpertion("添加");

    

    }
    //@AfterMethod
    //public void afterMethod(){
      //mainPage.logout();
    //}
	
	
	
	

}
