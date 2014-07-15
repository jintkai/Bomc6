package com.code.page.ibnmsConfig.workstation.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.envList.EnvFramePage;
import com.code.page.ibnmsConfig.envList.EnvListPage;
import com.code.page.ibnmsConfig.reslist.ResListFramePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/11.
 */
public class WKFormPage  extends FormPage{
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
        if(!tools.getMapValue(map,"主机名称").isEmpty()){
            tools.openModelDialog(selectHostBtn);
            String hand = tools.swithToWindowByTitle("监控指标列表");
            EnvFramePage envFrame=new EnvFramePage();
            envFrame.search(map).selectAllTr();
            envFrame.listPage.envBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.click(this.btnSubmit);
        tools.alertAccept();
    }
    public GridPage add(Map<String,String> map)
    {
        inputForm(map);
        return  new GridPage();
    }
}
