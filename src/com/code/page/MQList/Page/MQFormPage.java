package com.code.page.MQList.Page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.page.envList.EnvListPage;
import com.code.page.envList.page.EnvFormPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class MQFormPage extends FormPage {
    @FindBy(id = "form.instanceName")
    WebElement instanceName;
    @FindBy(id = "form.port")
    WebElement port;
    @FindBy(id = "form.jmxPort")
    WebElement jmxPort;
    @FindBy(id = "form.webPort")
    WebElement webPort;
    @FindBy(id = "form.installPath")
    WebElement installPath;
    @FindBy(id = "form.processKey")
    WebElement processKey;
    @FindBy(id="btn-select-host")
    WebElement selHostBtn;
    public void addMQ(Map<String,String> map)
    {
        tools.sendKeys(instanceName,tools.getMapValue(map,"MQ名称"));
        tools.sendKeys(port,tools.getMapValue(map,"MQ端口"));
        tools.sendKeys(jmxPort,tools.getMapValue(map,"JMX端口"));
        tools.sendKeys(webPort,tools.getMapValue(map,"WEB控制台端口"));
        tools.sendKeys(installPath,tools.getMapValue(map,"安装路径"));
        tools.sendKeys(processKey,tools.getMapValue(map,"进程关键字"));
        tools.click(selHostBtn);
        String hand=tools.swithToWindowByTitle("监控指标列表");
        tools.switchToFrame("entityQueryFrame");
        EnvListPage envList=new EnvListPage();
        envList.searchEnv(map);
        new GridPage().selectTr(0);
        envList.envBtn.select();
        tools.switchToWindowByHand(hand);
        tools.click(this.btnSubmit);
        tools.alertAccept();
    }
}
