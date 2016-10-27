package com.code.test.itilManage;

import java.util.HashMap;

import com.code.itilManage.formConfig.Domain.FormConfAdd;
import com.code.itilManage.formConfig.FormConfPage;
import com.code.itilManage.formConfig.FormSelectPage;
import com.code.itilManage.formjsConfig.Domain.FormjsDomain;
import com.code.itilManage.formjsConfig.Page.FormcomentPage;
import com.code.itilManage.formjsConfig.Page.FormjslistPage;
import com.code.itilManage.webProDesigner.page.GraphlistlinkPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.code.common.TestCase2;
import com.code.portal.login.LoginPage;
import com.code.portal.main.MainPage;
import com.code.portal.main.PortalHeader;

public class FormJsConfigTest extends TestCase2{

    LoginPage loginPage;
    MainPage    mainPage;
    PortalHeader header;
    FormSelectPage formselectPage;
    FormConfPage FormconfPage;
    GraphlistlinkPage graphlistlinkPage;
    //@Parameters({"node"})
	public FormJsConfigTest() {
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
	   public void FormJsConfig(){
		   
	    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
	        tools.switchToFrame(e); 
	        graphlistlinkPage.selectMenu("流程图列表");
	        tools.switchToFrame(e); 
	        graphlistlinkPage.selectMenu("表单配置");
	        tools.switchToFrame(e); 
	        FormSelectPage formselectpage =new FormSelectPage(eventDriver);   	
	    	formselectpage.FormjsConf();
	    	FormjsDomain formjsdomain = new FormjsDomain();
	        FormjslistPage formjslistpage =new FormjslistPage(eventDriver);
	    	FormcomentPage formcomentpage =new FormcomentPage(eventDriver);
	    	formjslistpage.FormJsConfig();	    	
	    	formjsdomain.setfunc_param("#ActivityId#");
	    	formjsdomain.setjs_relation("");
	    	formjsdomain.setJS_INIT("");
	    	formjsdomain.setjs_validate("getIsEmpty('column05','新增文本框')");
	    	formjsdomain.setLINK_URL("http://172.21.10.21:10005/portal");
	    	formjsdomain.setcomments("元素说明");
	    	formcomentpage.InsertComments(formjsdomain);
	    	formjslistpage.FormJsConfig();
	    	tools.assertEquals(formcomentpage.getFUNC_PARAM(),"#ActivityId#", "参数配置成功");	    	
	    }   
	   
	    @AfterMethod
	    public void afterMethod(){
	    mainPage.logout();
	    tools.execJS("window.onbeforeunload=null;");
	    }

}
