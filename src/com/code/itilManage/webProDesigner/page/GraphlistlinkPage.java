package com.code.itilManage.webProDesigner.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.code.common.Page;




public class GraphlistlinkPage extends Page  {
    public GraphlistlinkPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    
    //定位流程图列表
    @FindBy(id=("graphlist"))
    WebElement graphlist;
    
	//定义列表中根目录中第二个+号
    @FindBy(xpath=("//a[@href='javascript: d.o(2);']"))
    WebElement list;
    

	//定位元素ID是sd15的流程
    @FindBy(id=("sd15"))
    WebElement process;
    
    
	//定位表单配置菜单下区域
    @FindBy(id=("appealConfigTree"))
    WebElement formframe;
    
	//定位表单配置下元素ID是sd1的环节
    @FindBy(id=("sd1"))
    WebElement activity;
    
    
	//定位数据字典菜单下的区域
    @FindBy(id=("dictConfigTree"))
    WebElement dictConfigTree;
    
	//定位公共数据字典菜单
    @FindBy(id=("sd4"))
    WebElement dbConfig;
    
	//定义列表中根目录中第二个+-号的图片
    @FindBy(id=("jd2"))
    WebElement img;


	    
	public void selectMenu( String name){

        List<WebElement> elements=tools.findElements(eventDriver, By.className("menuLable"));
        

      for(int i = 0; i < elements.size(); i++)
      {
    	  String mname=elements.get(i).getText();
          if (mname.equals(name)){
        	          	
        	  tools.click(elements.get(i));           	  
              if (name =="流程图列表") {            	  
            	  tools.switchToFrame(graphlist);
            	  String imgsrc= img.getAttribute("src");
            	  
            	  if (imgsrc.contains("inc/dtree/img/plus.gif")){            	
            		  tools.click(list);
            	  }
            		  
            	  tools.click(process);
            	  tools.switchToWindos();            	  
              }              
              if (name =="表单配置") {
            	  tools.switchToFrame(formframe);           	  
            	  tools.click(activity);
            	  tools.switchToWindos();            	  
              }     
              
              if (name =="数据字典") {            	 
            	  tools.switchToFrame(dictConfigTree);           	  
            	  tools.click(dbConfig);
            	  tools.switchToWindos();            	  
              }   
              return;
          }
          
      }      
	    }  
}


/*  
if (name =="数据字典配置") {
	 tools.switchToFrame2(dictConfigTree);            	  
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
*/
