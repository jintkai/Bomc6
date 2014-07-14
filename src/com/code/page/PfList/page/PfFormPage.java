package com.code.page.PfList.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.page.envList.EnvListPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/11.
 */
public class PfFormPage extends FormPage {

    @FindBy(id="btn-select-host")
    WebElement selectHostBtn;
    @FindBy(id="instanceName")
    WebElement instanceName;
    @FindBy(id="installPath")
    WebElement installPath;
    @FindBy(id="pmMqId")
    WebElement pmMqId;
    @FindBy(id="processKey")
    WebElement processKey;
    @FindBy(id="lang")
    WebElement lang;
    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(instanceName,tools.getMapValue(map,"Performance名称"));
        tools.sendKeys(installPath,tools.getMapValue(map,"安装路径"));
        tools.selectByVisibleText(pmMqId,tools.getMapValue(map,"ActiveMQ"));
        tools.sendKeys(processKey,tools.getMapValue(map,"进程关键字"));
        tools.sendKeys(lang,tools.getMapValue(map,"LANG"));
        if(!tools.getMapValue(map, "部署主机").isEmpty())
        {
            //tools.click(selectHostBtn);
            tools.openModelDialog(selectHostBtn);
            String hand = tools.swithToWindowByTitle("监控指标列表");
            tools.switchToFrame("entityQueryFrame");
            EnvListPage envList = new EnvListPage();
            envList.searchByName(tools.getMapValue(map, "部署主机"));
            new GridPage().selectTr(0);
            envList.envBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.click(this.btnSubmit);
        tools.alertAccept();
    }
    public void addPF(Map<String,String> map)
    {
        inputForm(map);
    }
}
