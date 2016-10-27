package com.code.itilManage.slistConf.Page;

import com.code.itilManage.slistConf.Domain.DictTypeDomain;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.code.common.Page;

public class DictTypeSearchPage extends Page{

    public DictTypeSearchPage(EventFiringWebDriver webDriver)
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
    
    public void SearchdictType(DictTypeDomain dicttypedomain){
    	
    	tools.switchToFrame(configIframe,leftFrm);
    	tools.selectByVisibleText(busintype,dicttypedomain.getbusintype());
    	tools.sleep(2000);
    	tools.sendKeys(busintypename, dicttypedomain.getbusintypenamevalue());
    	tools.sleep(2000);
    	tools.click(searchbtn);
    	tools.switchToFrame();

    	
    }
    
}
