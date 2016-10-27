package com.code.itilManage.slistConf.Page;

import java.util.List;

import com.code.itilManage.slistConf.Domain.DictTypeDomain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.code.common.Page;

public class DictTypeListPpage extends Page{

    public DictTypeListPpage(EventFiringWebDriver webDriver)
    {
        super(webDriver);
    }
    
    //点击环节后右侧的IFRAME
    @FindBy(id="configIframe")
    WebElement configIframe;	
    @FindBy(id="leftFrm")
    WebElement leftFrm;	
    
    @FindBy(xpath="//input[@class='btnStyle'][@value='增加']")
    WebElement addbtn;
    
    @FindBy(xpath="//input[@class='btnStyle'][@value='修改']")
    WebElement editbtn;
    
    @FindBy(css="input[class='btnStyle'][value'删除']")
    WebElement delbtn;
    
    @FindBy(css="input[class='btnStyle'][value'查询']")
    WebElement searchbtn;
    //数据列表第一行数据前的选择框
    @FindBy(xpath="//*[@id='framelistclose']/table/tbody/tr[3]/td[1]/input")
    WebElement checkbox1;
		 
    @FindBy(name="BUSINTYPE")
    WebElement busintype;	
    
    @FindBy(name="BUSINTYPENAME")
    WebElement busintypename;	
    
     
    
    public void AdddictType(){   	
    	tools.switchToFrame(configIframe,leftFrm);
    	tools.sleep(3000);
    	tools.click(addbtn);
    	tools.switchToFrame();
    	/*
        EditDictTypePage editdicttypepage = new EditDictTypePage(eventDriver);
        EditDictTypeDomain editdicttypedomain=new EditDictTypeDomain();
        editdicttypepage.EditDictType( editdicttypedomain);*/

    	
    }
    
    
    public void EditdictType(){   	
    	tools.switchToFrame(configIframe,leftFrm); 
    	tools.click(checkbox1);
       	tools.sleep(3000); 	
    	tools.click(editbtn);
    	tools.switchToFrame();
    	
    }
    
    public void DeldictType(DictTypeDomain dicttypedomain){
    	tools.switchToFrame(configIframe,leftFrm);
    	tools.click(checkbox1);
    	tools.click(delbtn);
    	tools.alertAccept();
    	tools.switchToFrame();
    	
    }
    

    
    
    public boolean searchresult(DictTypeDomain dicttypedomain){
    	tools.switchToFrame(configIframe,leftFrm);	
    List <WebElement> busintypes= tools.findElements(eventDriver, By.xpath("//*[@class='tablewhitebg']/td[2]"));
    List <WebElement> busintypenames= tools.findElements(eventDriver, By.xpath("//*[@class='tablewhitebg']/td[3]"));
    String busintype0=dicttypedomain.getbusintype().trim();
    String busintypename0=dicttypedomain.getbusintypenamevalue().trim();

	for(int i = 0; i < busintypes.size(); i++)		   		 
	{  
	    String busintype1=busintypes.get(i).getText().trim();
	    String busintypename1=busintypenames.get(i).getText().trim();
		if (!(busintype1.equals(busintype0)) || !(busintypename1.contains(busintypename0))){
			System.out.println(busintype0);
			System.out.println(busintypename0);
			System.out.println(busintype1);
			System.out.println(busintypename1);			
			System.out.println(i);
			return false;
		}
		else {
			return true;
		}
								 
    }
    
	return searchresult(dicttypedomain);	
    }   
    
	public int rownum(){				 
		List<WebElement> tr= tools.findElements(eventDriver,By.xpath("//*[@class='tablewhitebg']"));
		System.out.println(tr.size());
		int rownum=tr.size();				
	 	return rownum;			 
		 }			


}
