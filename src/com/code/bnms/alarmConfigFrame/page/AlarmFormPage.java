package com.code.bnms.alarmConfigFrame.page;

import com.code.common.Page;
import com.code.bnms.alarmPolicy.AlarmPolicyList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/8/19.
 * 引用告警策略的一个小的页面，告警集中配置中包含引用策略重置策略等；
 */
public class AlarmFormPage extends Page {
    //引用策略
    WebElement policy;
    String policyStr;
    //@FindBy(className = "select-1c")
    String selectClass="select-1c";
    WebElement selectPolicy;
    //@FindBy(id = "useOtherPolicy")
    String useOtherID="useOtherPolicy";
    WebElement useOtherPolicy;

    @FindBy(id = "userTemplatePolicy")
    WebElement useTemplate;
    @FindBy(id="cancelEdit")
    WebElement cancelEdit;


    public void selectPolicy(Map<String,String> map)
    {
        String option=tools.getMapValue(map,"使用策略或模板");
        if (option.isEmpty())
            return;
        if (option.equals("引用策略"))
        {
            tools.click(tools.findBy(policy, By.className(selectClass)));
            tools.click(tools.findBy(policy, By.id(useOtherID)));
            AlarmPolicyList alarmPolicy=new AlarmPolicyList(eventDriver);
            String hand=tools.switchToWindowByTitle(alarmPolicy.title);
            alarmPolicy.search(map).selectTr(1);
            alarmPolicy.policyBtn.select();
            tools.switchToWindowByHand(hand);
        }
    }
    public void setPolicy(WebElement ele)
    {
        this.policy=ele;
    }
    public void setUseOtherID(String str)
    {
        useOtherID=str;
    }
}
