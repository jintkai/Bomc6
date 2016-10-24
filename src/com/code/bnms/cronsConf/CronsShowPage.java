package com.code.bnms.cronsConf;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.bnms.cronsConf.domain.CronsFormDomain;
import com.code.bnms.cronsConf.page.CronsFormPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jon on 2016/10/20.
 */
public class CronsShowPage extends Page {
    @FindBy(id="btn-insertCron")
    WebElement insertCronBtn;
    @FindBy(id="btn-search")
    WebElement searchBtn;
    @FindBy(name="filter.name")
    WebElement filterName;
    @FindBy(id="btn-deleteCron")
    WebElement deleteCronBtn;
    @FindBy(id="btn-updateCron")
    WebElement updateCronBtn;
    @FindBy(id="btn-citeCron")
    WebElement citeCronBtn;
    public String title=  "业务采集调度周期配置";
    CronsFormPage cronsFormPage;
    public CronsShowPage(EventFiringWebDriver driver){
        super(driver);
        cronsFormPage=new CronsFormPage(driver);
    }

    public void operate(String operation, String cronsName,CronsFormDomain domain){
        if (operation.equals("增加")){
            tools.click(insertCronBtn);
            cronsFormPage.operate("增加",domain);
        }
        if(operation.equals("删除"))
        {
            search(cronsName);
            GridPage gridPage=new GridPage(eventDriver);
            gridPage.selectTr(1);
            tools.click(deleteCronBtn);
            tools.alertAccept();
        }
        if(operation.equals("修改")){
            search(cronsName);
            GridPage gridPage=new GridPage(eventDriver);
            gridPage.selectTr(1);
            tools.click(updateCronBtn);
            cronsFormPage.operate("修改",domain);
            //tools.alertAccept();
        }
    }

    public Page search(String cronName){
        tools.sendKeys(filterName,cronName);
        tools.click(searchBtn);
        return this;
    }
    public void select(String cronName){
        search(cronName);
        (new GridPage(eventDriver)).selectTr(1);
        tools.click(citeCronBtn);
        tools.alertAccept();
    }
}
