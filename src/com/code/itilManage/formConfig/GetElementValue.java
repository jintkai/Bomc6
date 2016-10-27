package com.code.itilManage.formConfig;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.code.common.Page;

public class GetElementValue extends Page{

    public GetElementValue(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
		
		public String setline( String ss){  
			
			Select select = new Select(line);
			List<WebElement> options= select.getAllSelectedOptions();
		 	 for(int a = 0; a < options.size(); a++)
		     {
		   		String lines =options.get(a).getAttribute("value");   		
		   		int row =Integer.parseInt(lines)+1; 
		   		ss=String.valueOf(row);   	
		      }
		 	 return ss;
			}
		   //定位最后一行的行数选择框  
	    @FindBy(xpath=("//*[@id='table1']/tbody/tr[last()]/td[7]/select"))
	    WebElement line;

}
