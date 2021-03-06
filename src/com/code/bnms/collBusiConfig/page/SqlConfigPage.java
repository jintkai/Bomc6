package com.code.bnms.collBusiConfig.page;

import com.code.common.Page;
import com.code.bnms.collBusiConfig.domain.SqlFormDomain;
import com.code.bnms.cronsConf.CronsShowPage;
import com.code.bnms.entity.EntityPage;
import com.code.bnms.kbplist.KbpListFramePage;
import com.code.bnms.kpilist.KpiListFramePage;
import com.code.bnms.kpilist.domain.KpiSearchDomain;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by jon on 2016/10/22.
 */
public class SqlConfigPage extends Page {
    @FindBy(name="schedule.serviceName")
    WebElement serviceName;
    @FindBy(id="btn-addkbp")
    WebElement addKbpBtn;
    @FindBy(name="schedule.serviceDesc")
    WebElement serviceDesc;
    @FindBy(id="btn-addCron")
    WebElement addCronBtn;
    @FindBy(id="btn-addEntity")
    WebElement addEntity;
    @FindBy(id="sqlsubmit")
    WebElement sqlsubmit;
    @FindBy(id="btn-runSql")
    WebElement runSqlBtn;

    public SqlConfigPage(EventFiringWebDriver eventFiringWebDriver){
        super(eventFiringWebDriver);
    }
    public void inputForm(SqlFormDomain domain){

        tools.sendKeys(serviceDesc,domain.getServiceName());
        tools.sendKeys(serviceName,domain.getServiceName());
        if(domain.getKbpSearchDomain()!=null){
            tools.click(addKbpBtn);
            KbpListFramePage kbpListFramePage=new KbpListFramePage(eventDriver);
            kbpListFramePage.select(domain.getKbpSearchDomain());
        }
        if(domain.getCronsName()!=null){
            tools.click(addCronBtn);
            CronsShowPage cronsShowPage=new CronsShowPage(eventDriver);
            cronsShowPage.select(domain.getCronsName());
        }
        if(domain.getEntirys()!=null){
            tools.click(addEntity);
            EntityPage entityPage=new EntityPage(eventDriver);
            String hand=tools.switchToWindowByTitle(entityPage.title);
            entityPage.selectEntity(domain.getEntirys());
            tools.switchToWindowByHand(hand);
        }
        if (domain.getSql() != null) {
            WebElement div = tools.findBy(eventDriver, By.className("CodeMirror"));
            JavascriptExecutor js = (JavascriptExecutor) eventDriver;
            String str = domain.getSql();
            js.executeScript("arguments[0].CodeMirror.setValue(\"" + str + "\");", div);

        }
        tools.click(runSqlBtn);
        tools.sleep(5000);

        if (domain.getKpiSearchDomains()!=null){
            WebElement sqlResult=tools.findBy(eventDriver,By.id("sql-column-table"));
            KpiSearchDomain kpiSearchDomain ;
            for (int i = 0; i < domain.getKpiSearchDomains().length; i++) {

                List<WebElement> e = tools.findElements(sqlResult, By.className("btn-addkpi"));
                tools.click(e.get(i));
                kpiSearchDomain = domain.getKpiSearchDomains()[i];
                KpiListFramePage kpiListFramePage = new KpiListFramePage(eventDriver);
                kpiListFramePage.select(kpiSearchDomain);
            }
            List<WebElement> radio=tools.findElements(sqlResult,By.name("sql-attr"));
            tools.click(radio.get(0));
        }
        tools.click(sqlsubmit);
    }
    public void operateSQL(SqlFormDomain domain){
        inputForm(domain);
    }
}
