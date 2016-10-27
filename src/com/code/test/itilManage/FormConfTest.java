package com.code.test.itilManage;

import java.util.HashMap;

import com.code.itilManage.formConfig.Domain.FormConfAdd;
import com.code.itilManage.formConfig.FormConfPage;
import com.code.itilManage.formConfig.FormSelectPage;
import com.code.itilManage.webProDesigner.page.GraphlistlinkPage;
import com.code.portal.main.PortalHeader;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.code.common.TestCase2;
import com.code.portal.login.LoginPage;
import com.code.portal.main.MainPage;

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
	public FormConfAdd FormConfAdddomain=new FormConfAdd();
	@BeforeMethod
    public void beforeMethod()
    {
		
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
    public void addElement(){
    	
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        tools.switchToFrame(e); 
        graphlistlinkPage.selectMenu("流程图列表");
        tools.switchToFrame(e); 
        graphlistlinkPage.selectMenu("表单配置");
        tools.switchToFrame(e); 

        formselectPage.formConf();
        int rownum=FormconfPage.rownum()+1;
        String rownum0=String.valueOf(rownum);
        FormconfPage.selectOpertion("添加","文本框");
        FormconfPage.selectOpertion("保存",null);

        int rownumn=FormconfPage.rownum();
        String rownum1=String.valueOf(rownumn);
        tools.assertEquals(rownum1, rownum0, "添加表单元素成功");
        
        /*
        String FUNC_LENGTH=FormConfAdddomain.setlength();        
        String elename= FormconfPage.lastelename();    
        String FUNC_NAME= FormconfPage.lastfuncname();
        String length= FormconfPage.lastlength();
        String line= FormconfPage.lastline();*/
    }



 
    
    @Test
    public void delElement(){
    	tools.switchToFrame();
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
       tools.switchToFrame(e); 
       graphlistlinkPage.selectMenu("流程图列表");
       tools.switchToFrame(e); 
        graphlistlinkPage.selectMenu("表单配置");
        tools.switchToFrame(e);   
        formselectPage.formConf();
        int rownum=FormconfPage.rownum()-1;
        String rownum0=String.valueOf(rownum);
        FormconfPage.selectOpertion("删除",null);
        FormconfPage.selectOpertion("保存",null);
        int rownumn=FormconfPage.rownum();
        String rownum1=String.valueOf(rownumn);
       tools.assertEquals(rownum1, rownum0, "删除表单元素成功");
    }
    
    @Test
    public void formPreview(){
    	tools.switchToFrame();
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
       tools.switchToFrame(e); 
       graphlistlinkPage.selectMenu("流程图列表");
       tools.switchToFrame(e); 
        graphlistlinkPage.selectMenu("表单配置");
        tools.switchToFrame(e);   
        formselectPage.formConf();
        int rownum=FormconfPage.rownum()-1;
        String rownum0=String.valueOf(rownum);
        FormconfPage.selectOpertion("删除",null);
        FormconfPage.selectOpertion("保存",null);
        int rownumn=FormconfPage.rownum();
        String rownum1=String.valueOf(rownumn);
       tools.assertEquals(rownum1, rownum0, "删除表单元素成功");
    }
    @AfterMethod
    public void afterMethod(){
    mainPage.logout();
    tools.execJS("window.onbeforeunload=null;");
    }

}
