package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/16.
 * 告警集中配置增加页面
 */
public class AlarmConfFormPage extends Page {
    public AlarmPolicyPage alarmPolicy=new AlarmPolicyPage();
    public MonitorPointPage monitorPoint=new MonitorPointPage();
    public AlarmUpgradeFrame upgrade=new AlarmUpgradeFrame();
    public AlarmFilterPage filter=new AlarmFilterPage();
    public AlarmClearPage clear=new AlarmClearPage();
    @FindBy(xpath="//button[@data-type=\"save\"]")
    WebElement save;
    @FindBy(xpath="//button[@data-type=\"saveas\"]")
    WebElement saveEdit;
    public void add(Map<String,String> map,int type)
    {

        tools.switchToFrame();
        tools.switchToFrame("alarmConfigFrame");
        monitorPoint.add(map);
        tools.switchToFrame();
        tools.switchToFrame("alarmConfigFrame");
        tools.switchToFrame("generationFrame");
        alarmPolicy.add(map);
        tools.switchToFrame();
        tools.switchToFrame("alarmConfigFrame");
        tools.switchToFrame("upgradeFrame");
        upgrade.add(map);
        tools.switchToFrame();
        String str="var a =window.top.document.getElementById(\"alarmConfigFrame\").contentWindow.document.body.scrollTop=600";
        tools.execJS(" var a=document.getElementsByTagName(\"iframe\")[1].contentWindow.document.documentElement.scrollTop=600;return a;");
        tools.switchToFrame("alarmConfigFrame");
        tools.switchToFrame("filterFrame");
        filter.add(map);
        tools.switchToFrame();
        tools.execJS(" var a=document.getElementsByTagName(\"iframe\")[1].contentWindow.document.documentElement.scrollTop=600;return a;");
        tools.switchToFrame("alarmConfigFrame");
        tools.switchToFrame("clearFrame");
        clear.add(map);
        tools.switchToFrame();
        tools.execJS(" var a=document.getElementsByTagName(\"iframe\")[1].contentWindow.document.documentElement.scrollTop=600;return a;");
        tools.switchToFrame();
        tools.switchToFrame("alarmConfigFrame");
        if (type==1)
            tools.click(save);
        if (type==2)
            tools.click(saveEdit);
        String hand=tools.switchToWindowByTitle("表达式验证");
        new ExpressionAssertPage().submit();
        tools.switchToWindowByHand(hand);
        tools.alertAccept();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
