package com.code.page.ibnmsConfig.PfList.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.page.ibnmsConfig.envList.EnvFramePage;
import com.code.page.ibnmsConfig.envList.EnvListPage;
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
        tools.sendKeys(instanceName,tools.getMapValue(map,"PERFORMANCE名称"));
        tools.sendKeys(installPath,tools.getMapValue(map,"安装路径"));
        tools.selectByVisibleText(pmMqId,tools.getMapValue(map,"ActiveMQ"));
        tools.sendKeys(processKey,tools.getMapValue(map,"进程关键字"));
        tools.sendKeys(lang,tools.getMapValue(map,"LANG"));
        if(!tools.getMapValue(map,"部署主机").isEmpty()){
            tools.openModelDialog(selectHostBtn);
            EnvFramePage envFrame=new EnvFramePage();
            String hand = tools.switchToWindowByTitle(envFrame.title);
            envFrame.search(map).selectTr(0);
            envFrame.listPage.envBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.click(this.btnSubmit);
        tools.alertAccept();
    }
    public GridPage operatePF(Map<String,String> map)
    {
        inputForm(map);
        return  new GridPage();
    }

}
