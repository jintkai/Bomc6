package com.code.itilManage.webProDesigner.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.code.common.FormPage;
import com.code.common.Page;
import com.code.common.Tools;
import com.code.portal.main.MainPage;
import com.code.portal.main.PortalHeader;

public class GraphlistlinkPage extends Page  {
    public GraphlistlinkPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    
    

	    
	public void selectMenu( String name){

        List<WebElement> elements=tools.findElements(eventDriver, By.className("menuLable"));
      for(int i = 0; i < elements.size(); i++)
      {
    	  String mname=elements.get(i).getText();
          if (mname.equals(name)){
        	  
        	
        	  tools.click(elements.get(i));
           	  
              if (name =="流程图列表") {
            	  
            	 
           	  WebElement listiframe = eventDriver.findElement(By.id("graphlist"));
           	
            	  tools.switchToFrame(listiframe);
            	  //WebElement list0  = eventDriver.findElement(By.id("dd0"));
            	  //List<WebElement> list = list0.findElements(By.xpath("//a[@href='javascript: d.o(2);']"));
            	  //定义列表中【服务管理】前的+号
            	  WebElement list  = eventDriver.findElement(By.xpath("//a[@href='javascript: d.o(2);']"));            	 
            	  //System.out.println(list2.size());
            	  //tools.click(list.get(0));
            	  tools.click(list);
            	  WebElement process = eventDriver.findElement(By.id("sd15"));
            	  tools.click(process);
            	  tools.switchToWindos();
            	  
              }
              
              if (name =="表单配置") {
            	 WebElement formframe = eventDriver.findElement(By.id("appealConfigTree"));
            	  tools.switchToFrame(formframe);
            	  WebElement activity = eventDriver.findElement(By.id("sd1"));            	  
            	  tools.click(activity);
            	  tools.switchToWindos();
            	  
              }
              
              if (name =="数据字典配置") {
             	 WebElement formframe = eventDriver.findElement(By.id("dictConfigTree"));
             	 tools.switchToFrame(formframe);
            	 WebElement dbConfig = eventDriver.findElement(By.id("sd16"));
            	  
            	  tools.click(dbConfig);
            	  
              }
              
              if (name =="路由配置") {
              	 
            	  WebElement activity = eventDriver.findElement(By.id("sd1"));
            	  
            	  tools.click(activity);
            	  
              }
              
              if (name =="js维护") {
              	 
            	  WebElement activity =eventDriver.findElement(By.id("sd1"));
            	  
            	  tools.click(activity);           	  
              }
              
              if (name =="DB规则配置") {
              	 
            	  WebElement activity = eventDriver.findElement(By.id("sd1"));
            	  
            	  tools.click(activity);
            	  
              }
              
              if (name =="时限配置") {
              	 
            	  WebElement activity = elements.get(i).findElement(By.id("sd1"));
            	  
            	  tools.click(activity);
            	  
              }
              
              return;
          }
          
      }

      
	    }

  
}
