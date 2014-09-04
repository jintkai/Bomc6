package com.code.page.ibnmsConfig.alarmShield.page;

import com.code.common.FormPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/9/4.
 */
public class AlarmShieldFormPage extends Page {

    public String title="告警屏蔽过滤器配置";

    @FindBy(id="alarmShieldCfg.filterName")
    WebElement filterName;
    @FindBy(id = "alarmShieldCfg.beginTime")
    WebElement beginTime;
    @FindBy(id = "alarmShieldCfg.endTime")
    WebElement endTime;
    @FindBy(id = "token-input-alarmShieldCfg.grantUser")
    WebElement grantUser;
    @FindBy(id = "alarmShieldCfg.grantBeginTime")
    WebElement grantBeginTime;
    @FindBy(id = "alarmShieldCfg.grantEndTime")
    WebElement grantEndTime;
    @FindBy(className = "bnms-btn-save")
    WebElement savaBtn;
    public void opearete(Map<String,String> map)
    {
        input(map);
    }
    public void input(Map<String,String> map)
    {
        tools.sendKeys(filterName,tools.getMapValue(map,"过滤器名称"));
        tools.inputSubscripLogin(grantUser,tools.getMapValue(map,"授权执行人"));
        String js="document.getElementById(\""+tools.getAttribute(beginTime,"id")+"\").removeAttribute(\"readOnly\");";
        js=js+"document.getElementById(\""+tools.getAttribute(beginTime,"id")+"\").value=\""+tools.getMapValue(map,"屏蔽时段开始")+"\";";
        tools.execJS(js);
        js="document.getElementById(\""+tools.getAttribute(endTime,"id")+"\").removeAttribute(\"readOnly\");";
        js=js+"document.getElementById(\""+tools.getAttribute(endTime,"id")+"\").value=\""+tools.getMapValue(map,"屏蔽时段结束")+"\";";
        tools.execJS(js);

        js="document.getElementById(\""+tools.getAttribute(grantBeginTime,"id")+"\").removeAttribute(\"readOnly\");";
        js=js+"document.getElementById(\""+tools.getAttribute(grantBeginTime,"id")+"\").value=\""+tools.getMapValue(map,"授权有效开始")+"\";";
        tools.execJS(js);
        js="document.getElementById(\""+tools.getAttribute(grantEndTime,"id")+"\").removeAttribute(\"readOnly\");";
        js=js+"document.getElementById(\""+tools.getAttribute(grantEndTime,"id")+"\").value=\""+tools.getMapValue(map,"授权有效结束")+"\";";
        tools.execJS(js);
        tools.click(savaBtn);
        tools.alertAccept();
    }
}
