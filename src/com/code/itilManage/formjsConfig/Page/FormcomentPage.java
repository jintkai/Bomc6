package com.code.itilManage.formjsConfig.Page;

import java.util.List;

import com.code.itilManage.formjsConfig.Domain.FormjsDomain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.code.common.Page;

public class FormcomentPage  extends Page{

    public FormcomentPage(EventFiringWebDriver webDriver)
    {
        super(webDriver);
    }
    
    //点击插入后的弹窗iframe
    @FindBy(id="TB_iframeContent")
    WebElement TB_iframeContent;
    //参数
    @FindBy(id="FUNC_PARAM")
    WebElement FUNC_PARAM;
    
    //验证js输入区
    @FindBy(id="JS_VALIDATE_NAME")
    WebElement JS_VALIDATE_NAME;
    
    //动作js输入区
    @FindBy(id="JS_RELATION_NAME")
    WebElement JS_RELATION_NAME;
    
    //位置行数
    @FindBy(id="TABLE_DIV")
    WebElement TABLE_DIV;
    
    //初始化js输入区
    @FindBy(id="JS_INIT")
    WebElement JS_INIT;
    
    //链接地址输入区
    @FindBy(id="LINK_URL")
    WebElement LINK_URL;
    
    //元素说明输入区
    @FindBy(id="comments")
    WebElement comments;       

    
	public void InsertComments( FormjsDomain formjsdomain){
		tools.switchToFrame(TB_iframeContent);
		tools.sendKeys(FUNC_PARAM,formjsdomain.getFUNC_PARAM());
		tools.sendKeys(JS_VALIDATE_NAME,formjsdomain.getjs_validate());
		tools.sendKeys(JS_RELATION_NAME,formjsdomain.getjs_relation());
		tools.sendKeys(JS_INIT, formjsdomain.getjs_init());
		tools.sendKeys(LINK_URL,formjsdomain.getlink_url());
		tools.sendKeys(comments,formjsdomain.getelecomment());	
	    WebElement  savebtn= eventDriver.findElement(By.cssSelector("input[class='btnStyle'][value'保存']"));
		tools.click(savebtn);
		tools.switchToFrame();
	}	
	
	
	public String getFUNC_PARAM(){
		tools.switchToFrame(TB_iframeContent);	
		String FUNC_PARAMvalue=FUNC_PARAM.getText();
		tools.switchToFrame();
		return FUNC_PARAMvalue;
	}	
	
	public String getJS_VALIDATE_NAME(){
		tools.switchToFrame(TB_iframeContent);	
		String JS_VALIDATEvalue=JS_VALIDATE_NAME.getText();
		tools.switchToFrame();
		return JS_VALIDATEvalue;
	}	
	
	public String getJS_RELATION_NAME(){
		tools.switchToFrame(TB_iframeContent);	
		String JS_RELATIONvalue=JS_RELATION_NAME.getText();
		tools.switchToFrame();
		return JS_RELATIONvalue;
	}	
	
	public String getJS_INIT(){
		tools.switchToFrame(TB_iframeContent);		
		String JS_INITvalue=JS_INIT.getText();
		tools.switchToFrame();
		return JS_INITvalue;
	}	
	
	public String getLINK_URL(){
		tools.switchToFrame(TB_iframeContent);		
		String LINK_URLvalue=LINK_URL.getText();
		tools.switchToFrame();
		return LINK_URLvalue;
	}	
	
	public String getcomments(){
		tools.switchToFrame(TB_iframeContent);	
		String commentsvalue=comments.getText();
		tools.switchToFrame();
		return commentsvalue;
	}		

}
