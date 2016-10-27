package com.code.itilManage.formjsConfig.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.code.common.Page;
import com.code.portal.main.MainPage;

public class FormjslistPage extends Page{

    public FormjslistPage(EventFiringWebDriver webDriver)
    {
        super(webDriver);
    }

    //表单js配置选项卡名称
    @FindBy(xpath=("//a[@href='#ui-tabs-2']"))
    WebElement FormjsConf;
    
    //点击环节后右侧的IFRAME
    @FindBy(id="configIframe")
    WebElement configIframe;	
    
	//定位最后一行字段编号，td的text为编号id
    @FindBy(xpath=("//*[@id='framelistclose']/form/table/tbody/tr[last()]/td"))
    WebElement element_id;
    
	//定位最后一行字段编号下的input，input的value为F+所选流程环节号
    @FindBy(xpath=("//*[@id='framelistclose']/form/table/tbody/tr[last()]/td[1]/input"))
    WebElement form_id;
    
    
	//String elementid=element_id.getText();
	//String formid=form_id.getAttribute("value");
	//String  formid0 = formid.substring(1, formid.length());
	
	//定位最后一行插入按钮
    @FindBy(xpath=("//*[@id='framelistclose']/form/table/tbody/tr[last()]/td[last()]/a"))
    WebElement insertbtn;
	
	FormcomentPage formcomentpage =new FormcomentPage(eventDriver); 
	MainPage mainpage= new MainPage(eventDriver);
	WebElement e=mainpage.switchWindow("webProcessDesigner/graphMgt.jsp");
	
	 public void FormJsConfig(){
		 tools.switchToFrame(e,configIframe);
		 //System.out.println(insertbtn.getText());
		 tools.click(insertbtn);		 
		 tools.switchToFrame();
	 }

    
}
