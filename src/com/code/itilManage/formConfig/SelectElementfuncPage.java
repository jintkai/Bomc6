package com.code.itilManage.formConfig;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.code.common.Page;

public class SelectElementfuncPage  extends Page{
	
	public SelectElementfuncPage(EventFiringWebDriver eventDriver) {
		super(eventDriver);			
	}
	//定位数据格式选择弹窗中的IFRAME  
    @FindBy(id=("TB_iframeContent"))
    WebElement TB_iframeContent;				
	public void SelectElementfunc( String funcname){	
		tools.switchToFrame(TB_iframeContent);		        
		List<WebElement> elementfuncs=tools.findElements(eventDriver, By.tagName("a"));    	
		    	
		for(int k = 0; k < elementfuncs.size(); k++)		   		 
		    {  
		   		String elementfunc_name=elementfuncs.get(k).getText();
		   		if (elementfunc_name.equals(funcname)){ 	   	
		   		tools.sleep(1000);
		   		tools.click(elementfuncs.get(k));		   	  
		        }		   		 
		     }			
						 
	}		 

}
