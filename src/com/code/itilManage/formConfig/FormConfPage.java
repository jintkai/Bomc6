package com.code.itilManage.formConfig;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.code.common.DBTools;
import com.code.common.Page;

public class FormConfPage  extends Page{
	
	 public DBTools dbTools=new DBTools();
//    @FindBy
//    public WebElement configIframe;
    @FindBy(id="getAppealDetailForm")
    public WebElement formDetail;
    @FindBy(id="table1")
    public WebElement formtable;

	public FormConfPage(EventFiringWebDriver eventDriver) {
		super(eventDriver);		
	} 
	

	public void selectOpertion( String Opertion){


//    	WebElement configIframe = eventDriver.findElement(By.id("configIframe"));
//
//	       tools.switchToFrame2(configIframe);
	       
	       
//	       	WebElement formDetail = eventDriver.findElement(By.id("getAppealDetailForm"));
//         	 WebElement formtable = eventDriver.findElement(By.id("table1"));
           List<WebElement> elements=tools.findElements(formtable,By.tagName("tr"));
            //System.out.println(elements.size());
           int row = elements.size();
        System.out.println(row);
        WebElement new_element = tools.findBy(formtable,By.xpath("//tr["+row+"]"));
        new_element = tools.findBy(formtable,By.xpath(".//tbody//tr["+row+"]"));
        String xpath="//tbody/tr["+row+"]/td[3]/input";
        
           WebElement new_elementname = tools.findBy(new_element,By.xpath("//td[3]"));
        WebElement edit = tools.findBy(new_elementname,By.tagName("input"));
           //WebElement edit = tools.findBy(formtable,By.xpath(xpath));
          
           System.out.println(edit.getAttribute("name"));
           System.out.println(edit.getAttribute("type"));
           System.out.println(edit.getAttribute("type"));
           System.out.println(tools.getAttribute(edit,"title"));
           System.out.println(tools.getAttribute(edit,"value"));
        new_element=null;
        new_element = tools.findBy(formtable,By.xpath("//tr[6]"));

        if(!(new_element.isDisplayed())){
            System.out.println("Element is not displayed!");
        }
          // edit=tools.findBy(new_element,By.xpath("//td[2]/input"));

        if(!(edit.isDisplayed())){
            System.out.println("Element is not displayed!");
        }
           tools.sendKeys(edit,"2222");
           /*

	List<WebElement> btn=tools.findElements(eventDriver, By.id("testAdd"));
	
	 for(int i = 0; i < btn.size(); i++)
     {
   	  String btnvalue=btn.get(i).getAttribute("value");
   	  //System.out.println(btnvalue);
         if (btnvalue.equals(Opertion)){
       	  
        	 tools.click(btn.get(i));
          	  
          if (Opertion =="添加") {            	  
        	  /* 
        	 String strSql="SELECT COUNT(*) FROM swfformelement t  WHERE t.FORM_ID ='F4015103' AND t.ENABLE='1'";        	 
        	 List<Map> list=dbTools.queryMapListHandler(strSql);
        	 System.out.println(list.size());*/
        	/*
          	WebElement formDetail = eventDriver.findElement(By.id("getAppealDetailForm"));
          	 WebElement formtable = eventDriver.findElement(By.id("table1"));
            List<WebElement> elements=formtable.findElements(By.tagName("tr"));
             //System.out.println(elements.size());
            WebElement new_element = formtable.findElement(By.xpath("//tr["+elements.size()+"]"));
         
            WebElement new_elementname = new_element.findElement(By.xpath("//td[3]"));
           
            WebElement edit = new_elementname.findElement(By.tagName("input"));
           
            System.out.println(edit.getAttribute("name"));
            System.out.println(edit.getAttribute("type"));
            tools.sleep(10000);
            tools.sendKeys(edit, "555");
            
          
           // System.out.println(new_elementname.getAttribute("align"));*/
           
   
            }
          
          	  
           //  }	
    
         }
    // }
	 


//}
