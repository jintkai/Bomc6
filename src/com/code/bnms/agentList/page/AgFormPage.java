package com.code.bnms.agentList.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.bnms.agentList.domain.AgentFormDomain;
import com.code.bnms.envList.EnvFramePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/14.
 */
public class AgFormPage extends FormPage {
    public AgFormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
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
        return new GridPage(eventDriver);
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
            EnvFramePage envFrame=new EnvFramePage(eventDriver);
            String hand=tools.switchToWindowByTitle(envFrame.title);
            envFrame.search(map).selectTr(0);
            envFrame.listPage.envBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.click(this.btnSubmit);
    }
    public void inputForm(String operate, AgentFormDomain agentFormDomain){
        if(operate.equals("增加")){
            tools.click(selectHostBtn);
            EnvFramePage envFrame=new EnvFramePage(eventDriver);
            String hand=tools.switchToWindowByTitle(envFrame.title);
            envFrame.search(agentFormDomain.getEnvSearchDomain()).selectTr(0);
            envFrame.listPage.envBtn.select();
            tools.switchToWindowByHand(hand);

            tools.sendKeys(instanceName,agentFormDomain.getAgentName());
            /*
            tools.selectByVisibleText(workstaionId, tools.getMapValue(map, "关联的Workstation"));
            tools.selectByVisibleText(performanceId,tools.getMapValue(map,"关联的Performance"));
            tools.sendKeys(processKey,tools.getMapValue(map,"进程关键字"));
             */
            tools.sendKeys(installPath,agentFormDomain.getInstallPath());
            tools.sendKeys(hsqldbPort,agentFormDomain.getDbPort());
            tools.sendKeys(jmxPort,agentFormDomain.getJmxPort());
            tools.sendKeys(lang,agentFormDomain.getLang());
            tools.click(this.btnSubmit);

        }
    }
    public GridPage operateAG(String operate,AgentFormDomain agentFormDomain)
    {

        inputForm(operate,agentFormDomain);
        return new GridPage(eventDriver);
    }
}
