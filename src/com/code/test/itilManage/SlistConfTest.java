package com.code.test.itilManage;

import java.util.HashMap;

import com.code.itilManage.formConfig.*;
import com.code.itilManage.formConfig.Domain.FormConfAdd;
import com.code.itilManage.slistConf.Domain.DictTypeDomain;
import com.code.itilManage.slistConf.Domain.EditDictTypeDomain;
import com.code.itilManage.slistConf.Page.DictTypeListPpage;
import com.code.itilManage.slistConf.Page.DictTypeSearchPage;
import com.code.itilManage.slistConf.Page.EditDictTypePage;
import com.code.itilManage.webProDesigner.page.GraphlistlinkPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.code.common.TestCase2;
import com.code.portal.login.LoginPage;
import com.code.portal.main.MainPage;
import com.code.portal.main.PortalHeader;


public class SlistConfTest extends TestCase2{

    LoginPage loginPage;
    MainPage    mainPage;
    PortalHeader header;
    FormSelectPage formselectPage;
    FormConfPage FormconfPage;
    GraphlistlinkPage graphlistlinkPage;
    DictTypeListPpage dictlistpage = new DictTypeListPpage(eventDriver);
    EditDictTypePage editdicttypepage = new EditDictTypePage(eventDriver);
    DictTypeSearchPage dicttypesearchpage= new DictTypeSearchPage(eventDriver);
    EditDictTypeDomain editdicttypedomain=new EditDictTypeDomain();
    DictTypeDomain dicttypedomain=new DictTypeDomain();

    @Parameters({"node"})
	public SlistConfTest(String node) {
        super(node);
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

    @Test(priority = 0)
    public void searchDictType(){
    	
    	
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        tools.switchToFrame(e); 
        graphlistlinkPage.selectMenu("数据字典");
        tools.switchToFrame(e);
        dicttypedomain.setbusintype("下拉框");
        dicttypedomain.setbusintypename("处理人");
        dicttypesearchpage.SearchdictType(dicttypedomain);
        
        
        tools.switchToFrame(e);
    	tools.assertTrue(dictlistpage.searchresult(dicttypedomain),"查询正确");
        
    }
    
    
    @Test(priority = 1)
    public void AddDictType(){
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        tools.switchToFrame(e); 
        graphlistlinkPage.selectMenu("数据字典");
        tools.switchToFrame(e);        
        dictlistpage.AdddictType();  
        tools.switchToFrame(e);
        editdicttypedomain.settype_desc("新增数据字典"+System.currentTimeMillis());
        editdicttypedomain.settype_name("新增"+System.currentTimeMillis());
        editdicttypedomain.setfunc_type("下拉框");
        editdicttypepage.EditDictType( editdicttypedomain);
        tools.sleep(3000);
        tools.switchToFrame(e);
        dicttypesearchpage.SearchdictType(dicttypedomain);  
        tools.switchToFrame(e);
        dicttypedomain.setbusintype(editdicttypedomain.getfunc_type());
        dicttypedomain.setbusintypename(editdicttypedomain.gettype_name());
        dicttypesearchpage.SearchdictType(dicttypedomain); 
        tools.switchToFrame(e);
    	tools.assertTrue(dictlistpage.searchresult(dicttypedomain),"新增成功");
        
    }
    
    @Test(priority = 2)
    public void EditDictType(){
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        tools.switchToFrame(e); 
        graphlistlinkPage.selectMenu("数据字典");
        tools.switchToFrame(e);        
        dicttypedomain.setbusintype("下拉框");
        dicttypedomain.setbusintypename("新增");
        dicttypesearchpage.SearchdictType(dicttypedomain);
        tools.switchToFrame(e);
        dictlistpage.EditdictType(); 
        tools.switchToFrame(e);
        editdicttypedomain.settype_name("修改"+System.currentTimeMillis());
        editdicttypedomain.settype_desc("修改描述"+System.currentTimeMillis());
        editdicttypedomain.setfunc_type("下拉框");       
        editdicttypepage.EditDictType( editdicttypedomain);   
        tools.sleep(3000);
        tools.switchToFrame(e);
        dicttypedomain.setbusintype(editdicttypedomain.getfunc_type());
        dicttypedomain.setbusintypename(editdicttypedomain.gettype_name());   
        dicttypesearchpage.SearchdictType(dicttypedomain); 
        tools.switchToFrame(e);
    	tools.assertTrue(dictlistpage.searchresult(dicttypedomain),"修改成功");
        
    }
    
   
    @Test (priority = 3)
    public void DelDictType(){
    	WebElement e=mainPage.switchWindow("webProcessDesigner/graphMgt.jsp");
        tools.switchToFrame(e); 
        graphlistlinkPage.selectMenu("数据字典");
        tools.switchToFrame(e);        
        dicttypedomain.setbusintype("下拉框");
        dicttypedomain.setbusintypename("新增");
        dicttypesearchpage.SearchdictType(dicttypedomain);
        int row0=dictlistpage.rownum()-1;
        String rownum0=String.valueOf(row0);
        tools.switchToFrame(e);
        dictlistpage.DeldictType(dicttypedomain);        
        tools.switchToFrame(e);
        dicttypesearchpage.SearchdictType(dicttypedomain);
        int row1=dictlistpage.rownum();
        String rownum1=String.valueOf(row1);
        tools.assertEquals(rownum1, rownum0, "删除表单元素成功");

        
    }
       
    @AfterMethod
    public void afterMethod(){
    mainPage.logout();
    tools.execJS("window.onbeforeunload=null;");
    }

	
}
