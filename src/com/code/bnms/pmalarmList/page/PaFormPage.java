package com.code.bnms.pmalarmList.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.bnms.envList.EnvFramePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/8/10.
 */
public class PaFormPage extends FormPage {
    public PaFormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    WebElement instanceName;
    WebElement deviceName;
    WebElement workstationId;
    WebElement processKey;
    WebElement installPath;
    WebElement swap_path;
    @FindBy(id="btn-submit")
    WebElement submitBtn;
    @FindBy(id="btn-select-host")
    WebElement selectHostBtn;

    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(instanceName,tools.getMapValue(map,"PMALARM名称"));
        if(!tools.getMapValue(map,"部署主机").isEmpty())
        {
            tools.click(selectHostBtn);
            EnvFramePage envFrame=new EnvFramePage(eventDriver);
            String hand=tools.switchToWindowByTitle(envFrame.title);
            GridPage gridTable=envFrame.search(map);
            gridTable.selectTr(0);
            envFrame.listPage.envBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.sendKeys(processKey,tools.getMapValue(map,"进程关键字"));
        tools.selectByVisibleText(workstationId,tools.getMapValue(map,"关联WORKSTATION"));
        tools.sendKeys(installPath,tools.getMapValue(map,"安装路径"));
        tools.sendKeys(swap_path,tools.getMapValue(map,"SWAP路径"));
        tools.click(submitBtn);
    }
    public GridPage operatePA(Map<String,String> map)
    {
        inputForm(map);
        return  new GridPage(eventDriver);
    }
}
