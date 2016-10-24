package com.code.bnms.alarmShield.page;

import com.code.common.Page;
import com.code.bnms.monitorPoints.MonitorPointsFramePage;
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
    @FindBy(id = "btn-add")
    WebElement btnAdd;
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

        String selectMoniter=tools.getMapValue(map,"监控点增加");
        if (!selectMoniter.isEmpty() && Integer.parseInt(selectMoniter)!=0)
        {
            MonitorPointsFramePage monitorFrame=new MonitorPointsFramePage();
            Map<String,String> tempMap=map;
            for (int i=0;i<Integer.parseInt(selectMoniter);i++)
            {
                tools.openModelDialog(btnAdd);
                String hand=tools.switchToWindowByTitle(monitorFrame.title);
                tempMap.put("监控点名称_MONITORPOINT",tools.getMapValue(map,"监控点名称_MONITORPOINT"+(i+1)));
                tempMap.put("指标名称_MONITORPOINT",tools.getMapValue(map,"指标名称_MONITORPOINT"+(i+1)));
                tempMap.put("监控点KBP_MONITORPOINT",tools.getMapValue(map,"监控点KBP_MONITORPOINT"+(i+1)));
                tempMap.put("指标ID_MONITORPOINT",tools.getMapValue(map,"指标ID_MONITORPOINT"+(i+1)));
                monitorFrame.selectMonitorPoints(tempMap);
                tools.switchToWindowByHand(hand);
            }
        }

        tools.click(savaBtn);
        tools.alertAccept();
    }
}
