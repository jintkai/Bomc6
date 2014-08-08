package com.code.page.ibnmsConfig.agentList.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.envList.EnvFramePage;
import com.code.page.ibnmsConfig.envList.EnvListPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/14.
 */
public class AgFormPage extends FormPage {

    @FindBy(id="btn-select-host")
    WebElement selectHostBtn;
    WebElement instanceName;
    WebElement workstaionId;
    WebElement performanceId;
    WebElement processKey;
    WebElement installPath;
    WebElement hsqldbPort;
    WebElement jmxPort;
    WebElement lang;
    public GridPage operateAG(Map<String,String> map)
    {

        inputForm(map);
        return new GridPage();
    }
    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(instanceName,tools.getMapValue(map,"Agent名称"));
        tools.selectByVisibleText(workstaionId, tools.getMapValue(map, "关联的Workstation"));
        tools.selectByVisibleText(performanceId,tools.getMapValue(map,"关联的Performance"));
        tools.sendKeys(processKey,tools.getMapValue(map,"进程关键字"));
        tools.sendKeys(installPath,tools.getMapValue(map,"安装路径"));
        tools.sendKeys(hsqldbPort,tools.getMapValue(map,"HSQLDB端口"));
        tools.sendKeys(jmxPort,tools.getMapValue(map,"JMX端口"));
        tools.sendKeys(lang,tools.getMapValue(map,"LANG"));
        if (!tools.getMapValue(map,"部署主机").isEmpty())
        {
            tools.click(selectHostBtn);
            EnvFramePage envFrame=new EnvFramePage();
            String hand=tools.swithToWindowByTitle(envFrame.title);
            envFrame.search(map).selectTr(0);
            envFrame.listPage.envBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.click(this.btnSubmit);
    }
}
