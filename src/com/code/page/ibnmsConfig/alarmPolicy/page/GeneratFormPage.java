package com.code.page.ibnmsConfig.alarmPolicy.page;

import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmConfigFrame.page.AlarmPolicyPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/7/30.
 */
public class GeneratFormPage extends Page {
    WebElement policyName;
    WebElement policyDesc;
    //Generat
    AlarmPolicyPage alarmPolicy=new AlarmPolicyPage();     //告警集中配置中的告警配置信息，直接引用；
    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(policyName,tools.getMapValue(map,"策略名称"));
        tools.sendKeys(policyDesc,tools.getMapValue(map,"策略描述"));
        //alarmPolicy.add(map);
    }
}
