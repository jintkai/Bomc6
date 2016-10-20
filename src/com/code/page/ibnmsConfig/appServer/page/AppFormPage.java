package com.code.page.ibnmsConfig.appServer.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.appServer.domain.AppFormDomain;
import com.code.page.ibnmsConfig.envList.EnvFramePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/8/10.
 */
public class AppFormPage extends Page {
    @FindBy(id="applyName")
    WebElement applyName;
    @FindBy(id="btn-select-host")
    WebElement selectHostBtn;
    @FindBy(name = "applyForm.customProps.BMCServerType")
    WebElement serverType;
    @FindBy(id = "processKey")
    WebElement processKey;
    @FindBy(id = "scanFrequency")
    WebElement scanFrequency;
    @FindBy(id="installPath")
    WebElement installPath;
    @FindBy(id = "subsmit_save")
    WebElement submitBtn;
    public AppFormPage(EventFiringWebDriver eventFiringWebDriver){
        super(eventFiringWebDriver);
    }
    @Deprecated
    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(applyName,tools.getMapValue(map,"应用名称"));
        tools.sendKeys(processKey,tools.getMapValue(map,"进程关键字"));
        tools.sendKeys(installPath,tools.getMapValue(map,"安装路径"));
        tools.selectByVisibleText(serverType, tools.getMapValue(map, "BmcServer类型"));
        if(!tools.getMapValue(map,"部署主机").isEmpty())
        {
            tools.click(selectHostBtn);
            EnvFramePage envFrame=new EnvFramePage(eventDriver);
            String hand=tools.switchToWindowByTitle(envFrame.title);
            GridPage gridTable= envFrame.search(map);
            gridTable.selectTr(0);
            envFrame.listPage.envBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.sendKeys(scanFrequency,tools.getMapValue(map,"扫描频率 "));
        tools.click(submitBtn);
    }
    @Deprecated
    public GridPage operateApp(Map<String,String> map)
    {
        inputForm(map);
        return new GridPage(eventDriver);
    }
    public void inputForm(AppFormDomain formDomain)
    {
        tools.sendKeys(applyName,formDomain.getAppName());
        tools.sendKeys(processKey,formDomain.getProcessKey());
        tools.sendKeys(installPath,formDomain.getInstatllPath());
        //tools.selectByVisibleText(serverType, tools.getMapValue(map, "BmcServer类型"));
        if(!(formDomain.getEnvSearchDomain()==null))
        {
            tools.click(selectHostBtn);
            EnvFramePage envFrame=new EnvFramePage(eventDriver);
            String hand=tools.switchToWindowByTitle(envFrame.title);
            GridPage gridTable= envFrame.search(formDomain.getEnvSearchDomain());
            gridTable.selectTr(0);
            envFrame.listPage.envBtn.select();
            tools.switchToWindowByHand(hand);
        }
        //tools.sendKeys(scanFrequency,tools.getMapValue(map,"扫描频率 "));
        tools.click(submitBtn);
    }

    public GridPage operateApp(AppFormDomain formDomain)
    {
        inputForm(formDomain);
        return new GridPage(eventDriver);
    }
}
