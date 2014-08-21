package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmPolicy.AlarmPolicyList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/8/19.
 */
public class AlarmFormPage extends Page {
    //引用策略
    //@FindBy(id = "policy_expression")
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
            AlarmPolicyList alarmPolicy=new AlarmPolicyList();
            String hand=tools.switchToWindowByTitle(alarmPolicy.title);
            alarmPolicy.search(map).selectTr(1);
            alarmPolicy.policyBtn.select();
            tools.switchToWindowByHand(hand);
        }
    }
}
