package com.code.page.ibnmsConfig.collBusiConfig;

import com.code.common.Page;
import com.code.page.ibnmsConfig.collBusiConfig.domain.ShellFormDomain;
import com.code.page.ibnmsConfig.collBusiConfig.domain.SqlFormDomain;
import com.code.page.ibnmsConfig.collBusiConfig.page.ShellConfigPage;
import com.code.page.ibnmsConfig.collBusiConfig.page.SqlConfigPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jon on 2016/10/20.
 */
public class CollBusiConfigPage extends Page {

    public CollBusiConfigPage(EventFiringWebDriver driver){
        super(driver);
    }

    @FindBy(id="btn-addSchedule")
    WebElement addScheduleBtn;
    @FindBy(id="btn-addShellButton")
    WebElement addShellBtn;
    @FindBy(id="btn-addSqlButton")
    WebElement addSqlBtn;
    @FindBy(id="serviceType")
    WebElement serviceType;
    @FindBy(name="filter.serviceName")
    WebElement serviceName;
    @FindBy(id="btn-search")
    WebElement searchBtn;
    public void addShellColl(ShellFormDomain domain){

        tools.click(addScheduleBtn);
        tools.click(addShellBtn);
        ShellConfigPage shellConfigPage=new ShellConfigPage(eventDriver);
        shellConfigPage.opertate("增加","SHELL",domain);
        tools.sleep(10000);
    }

    public void addSQLColl(SqlFormDomain domain){

        tools.click(addScheduleBtn);
        tools.click(addSqlBtn);
        SqlConfigPage sqlConfigPage=new SqlConfigPage(eventDriver);
        sqlConfigPage.operateSQL(domain);
        tools.sleep(10000);
    }

    public void search(String type,String name){
        tools.selectByVisibleText(serviceType,type);
        tools.sendKeys(serviceName,name);
        tools.click(searchBtn);
    }
}
