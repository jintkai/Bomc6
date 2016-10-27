package com.code.itilManage.slistConf.Page;

import com.code.itilManage.slistConf.Domain.EditDictTypeDomain;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.code.common.Page;
public class EditDictTypePage extends Page{

    public EditDictTypePage(EventFiringWebDriver webDriver)
    {
        super(webDriver);
    }
    //点击环节后右侧的IFRAME
    @FindBy(id="configIframe")
    WebElement configIframe;	
    @FindBy(id="leftFrm")
    WebElement leftFrm;	
    //定位数据字典名称输入区
    @FindBy(name="TYPE_NAME")
    WebElement TYPE_NAME;
    //定位数据字典描述输入区
    @FindBy(name="TYPE_DESC")
    WebElement TYPE_DESC;
    //定位数据字典类型选择控件
    @FindBy(name="FUNC_TYPE")
    WebElement FUNC_TYPE;
    //定位参数输入框
    @FindBy(name="FUNC_DAO_PARAM")
    WebElement FUNC_DAO_PARAM;
    
	//定位参数选择图标
    @FindBy(xpath=("//a[@href='javascript:checkText();']"))
    WebElement SELECT_PARAM;
    
    //定位是否通过sql获取中的是
    @FindBy(css="input[name='signSQL'][value'1']")
    WebElement sqlsigny;
    
    //定位是否通过sql获取中的是
    @FindBy(css="input[name='signSQL'][value'0']")
    WebElement sqlsignn;
    
    //定位保存按钮
    @FindBy(css="input[class='btnStyle'][value'保存']")
    WebElement savebtn;
    
    //定位重置按钮
    @FindBy(css="input[class='btnStyle'][value'重置']")
    WebElement resetbtn;
    
    //定位返回按钮
    @FindBy(css="input[class='btnStyle'][value'返回']")
    WebElement backbtn;   
    
    
    public void EditDictType(EditDictTypeDomain editdicttypedomain){
    	
    	tools.switchToFrame(configIframe,leftFrm);
    	tools.sendKeys(TYPE_NAME, editdicttypedomain.gettype_name());    	
    	tools.sendKeys(TYPE_DESC, editdicttypedomain.gettype_desc());   	
    	tools.selectByVisibleText(FUNC_TYPE, editdicttypedomain.getfunc_type());    	
    	tools.click(savebtn);
    	tools.alertAccept();
    	tools.switchToFrame();
    	
    }

}
