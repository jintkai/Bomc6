package com.code.bnms.workstation.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.bnms.envList.EnvFramePage;
import com.code.bnms.workstation.domain.WKFormDomain;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/11.
 */
public class WKFormPage  extends FormPage{
    public WKFormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(id = "form.instanceName")
    WebElement instanceName;
    @FindBy(id = "btn-select-host")
    WebElement selectHostBtn;
    @FindBy(id="mqId")
    WebElement mqId;
    @FindBy(id="processKey")
    WebElement processKey;
    @FindBy(id="installPath")
    WebElement installPath;

    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(instanceName, tools.getMapValue(map, "WORKSTATION名称"));
        tools.sendKeys(processKey, tools.getMapValue(map, "进程关键字"));
        tools.sendKeys(installPath, tools.getMapValue(map, "安装路径"));
        tools.selectByVisibleText(mqId, tools.getMapValue(map, "ActiveMQ"));
        if(!tools.getMapValue(map,"部署主机").isEmpty()){
            tools.openModelDialog(selectHostBtn);
            EnvFramePage envFrame=new EnvFramePage(eventDriver);
            String hand = tools.switchToWindowByTitle(envFrame.title);
            envFrame.search(map).selectTr(0);
            envFrame.listPage.envBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.click(this.btnSubmit);
        tools.alertAccept();
    }
    public GridPage operateWK(Map<String,String> map)
    {
        inputForm(map);
        return  new GridPage(eventDriver);
    }

    public GridPage operateWK(String operation ,WKFormDomain wkFormDomain)
    {
        inputForm(operation,wkFormDomain);
        return  new GridPage(eventDriver);
    }

    public void inputForm(String operation, WKFormDomain wkFormDomain)
    {

        tools.sendKeys(installPath, wkFormDomain.getInstallPath());

        if(operation.equals("增加")){
            tools.click(selectHostBtn);
            EnvFramePage envFrame=new EnvFramePage(eventDriver);
            String hand = tools.switchToWindowByTitle(envFrame.title);
            envFrame.search(wkFormDomain.getEnvSearchDomain()).selectTr(0);
            envFrame.listPage.envBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.click(this.btnSubmit);
        tools.alertAccept();
    }
}
