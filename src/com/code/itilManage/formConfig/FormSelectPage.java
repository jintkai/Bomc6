package com.code.itilManage.formConfig;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.code.common.Page;

public class FormSelectPage extends Page{
	
	    public FormSelectPage(EventFiringWebDriver eventDriver)
	    {
	        super(eventDriver);
	    }

	    //点击环节后右侧的IFRAME
	   // @FindBy(id="configIframe")
	    //WebElement configIframe;	    
	    
	    //表单配置选项卡名称
	    @FindBy(xpath=("//a[@href='#ui-tabs-1']"))
	    WebElement FormConf;
	    
	    //表单js配置选项卡名称
	    @FindBy(xpath=("//a[@href='#ui-tabs-2']"))
	    WebElement FormjsConf;
	    
	    //默认值配置选项卡名称
	    @FindBy(xpath=("//a[@href='#ui-tabs-3']"))
	    WebElement DefaultValue;
	    
	    //转义配置选项卡名称
	    @FindBy(xpath=("//a[@href='#ui-tabs-4']"))
	    WebElement TransferConf;
	    
	    //处理人配置选项卡名称
	    @FindBy(xpath=("//a[@href='#ui-tabs-5']"))
	    WebElement DealerConf;
	    
	    public void formConf(){
	    WebElement configIframe = eventDriver.findElement(By.id("configIframe"));	
	    tools.switchToFrame(configIframe);
	    tools.click(FormConf);	    	
	    	
	    }
	    
	    public void FormjsConf(){
	    	
	    	WebElement configIframe = eventDriver.findElement(By.id("configIframe"));
	       tools.switchToFrame(configIframe);
	      // System.out.println(FormjsConf.getTagName());
		    tools.click(FormjsConf);
		    tools.switchToWindos();
		    	
		    }
	    
	    public void DefaultValue(){
	    	WebElement configIframe = eventDriver.findElement(By.id("configIframe"));
		       tools.switchToFrame(configIframe);
		    tools.click(DefaultValue);
		    	
		    	
		    }
	    
	    public void TransferConf(){
	    	WebElement configIframe = eventDriver.findElement(By.id("configIframe"));
	        tools.switchToFrame(configIframe);
		    tools.click(TransferConf);
		    	
		    	
		    }
	    
	    public void DealerConf(){
	    	WebElement configIframe = eventDriver.findElement(By.id("configIframe"));
	        tools.switchToFrame(configIframe);
		    tools.click(DealerConf);
		    	
		    	
		    }	    

	              
}
