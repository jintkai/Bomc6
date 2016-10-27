package com.code.itilManage.formConfig;


import java.util.List;

import com.code.itilManage.formConfig.Domain.FormConfAdd;
import com.code.portal.main.MainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.code.common.*;

public class FormConfPage  extends Page{
	

	public FormConfPage(EventFiringWebDriver eventDriver) {
		super(eventDriver);		
	} 
	
    //点击环节后右侧的IFRAME
    @FindBy(id="configIframe")
   WebElement configIframe;	
    
    //点击环节后右侧的IFRAME
    @FindBy(id="table1")
    WebElement elementtable;	
	//定位最后一行的字段名前的复选框
    @FindBy(xpath=("//*[@id='table1']/tbody/tr[last()]/td[1]/input"))
    WebElement checkbox;
	
	//定位最后一行的字段名称输入框
    @FindBy(xpath=("//*[@id='table1']/tbody/tr[last()]/td[3]/input"))
    WebElement new_elementname;
    
    //定位最后一行的数据格式选择图标
    @FindBy(xpath=("//*[@id='table1']/tbody/tr[last()]/td[4]/img"))
    WebElement new_elementfunc;
    
    @FindBy(xpath=("//*[@id='table1']/tbody/tr[last()]/td[4]/input[1]"))
    WebElement func_name;
  
    //定位最后一行的行数选择框  
    @FindBy(xpath=("//*[@id='table1']/tbody/tr[last()]/td[7]/select"))
    WebElement line;
    
    //定位最后一行的长度输入框  
    @FindBy(xpath=("//*[@id='table1']/tbody/tr[last()]/td[9]/input"))
    WebElement length;   
    
    //定位最后一行是否显示一行选择框     
    @FindBy(xpath=("//*[@id='table1']/tbody/tr[last()]/td[10]/select"))
    WebElement is_inline;
    
    @FindBy(id="TB_iframeContent")
   WebElement TB_iframeContent;	
    
    
    
    public FormConfAdd FormConfAdddomain=new FormConfAdd();
 

	public void selectOpertion(String Opertion,String funcname){		

		Select select = new Select(line);	
	   	String lines =select.getAllSelectedOptions().get(0).getAttribute("value");   		
	   	int row =Integer.parseInt(lines)+1; 	
		
	List<WebElement> btn=tools.findElements(eventDriver, By.className("btnStyle"));
		
	
	 for(int i = 0; i < btn.size(); i++)
     {
   	  String btnvalue=btn.get(i).getAttribute("value");  
   	  String btnid=btn.get(i).getAttribute("id");     	  
          if (Opertion =="添加" && btnvalue.equals(Opertion)&&btnid.equals("testAdd")) {
        	tools.sleep(2000);       		        	
        	tools.click(btn.get(i));        	
        	tools.sendKeys(new_elementname,FormConfAdddomain.setelementname()+funcname);
        	tools.sleep(5000);
        	tools.click(func_name);
        	List<WebElement> imgbuttons=tools.findElements(eventDriver,By.tagName("img"));
            int size=imgbuttons.size()-1;
            tools.click(imgbuttons.get(size));        	     		
        	tools.sleep(5000);
        	tools.switchToFrame();   
          	SelectElementfuncPage  selectelement = new SelectElementfuncPage(eventDriver);       	
        	selectelement.SelectElementfunc(funcname);
        	
        	tools.switchToFrame();
    		MainPage mainpage= new MainPage(eventDriver);
    		WebElement e=mainpage.switchWindow("webProcessDesigner/graphMgt.jsp");
    		//tools.switchToFrame(e);
    		//tools.switchToFrame2(configIframe);
    		tools.switchToFrame(e,configIframe); 
        	select.selectByValue(String.valueOf(row));
        	tools.selectByVisibleText(is_inline, "是");
        	tools.sendKeys(length, FormConfAdddomain.setlength());       	
        	//select.selectByValue(String.valueOf(row));        	        	 
        	//select.selectByValue();            
            }
          
          if (Opertion =="保存" && btnvalue.equals(Opertion)) {

        	  tools.click(btn.get(i));   	  
        	  tools.alertAccept();   	         	          	  
          }
          
          else if (Opertion =="删除" && btnvalue.equals(Opertion)&&btnid.equals("testAdd")) {        	  
        	  tools.click(checkbox);
        	  tools.click(btn.get(i)); 
          }
          
         /* else if (Opertion =="预览" && btnvalue.equals(Opertion)) {        	  
        	  tools.click(btn.get(i)); 
        	  tools.switchToFrame();
        	  tools.switchToFrame2(TB_iframeContent);
        	  
          }*/          
    
         }
	 
     }
			
				
	public String lastfuncname(){
			 
		String lastfuncname=func_name.getAttribute("name");
		return lastfuncname;
			 
		 }
	
	public String lastlength(){
		 
		String lastlength=length.getAttribute("value");
		return lastlength;
			 
		 }
	
	public String lastelename(){
		 
		String lastelename=new_elementname.getAttribute("value");
		return lastelename;
			 
		 }
	
	public String lastline(){
		 
		Select select = new Select(line);	
	   	String lastline =select.getAllSelectedOptions().get(0).getAttribute("value");   
	   	return lastline;
			 
		 }			
	
	public int rownum(){				 
		List<WebElement> tr= tools.findElements(eventDriver,By.xpath("//*[@id='table1']/tbody/tr"));
		System.out.println(tr.size());
		int rownum=tr.size()-1;				
	 	return rownum;			 
		 }			

}
