package com.code.page.ibnmsConfig.alarmPolicy;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmPolicy.page.AlarmPolicyBtnPage;
import com.code.page.ibnmsConfig.alarmPolicy.page.GeneratFormPage;
import com.code.page.ibnmsConfig.alarmPolicy.page.SearchAlarmPolicyPage;

import java.util.Map;

/**
 * Created by Jin on 2014/7/30.
 * 告警策略维护列表
 */
public class AlarmPolicyList extends Page {
    SearchAlarmPolicyPage searchPolicy=new SearchAlarmPolicyPage();
    AlarmPolicyBtnPage policyBtn=new AlarmPolicyBtnPage();
    public GridPage search(Map<String,String> map)
    {
        searchPolicy.search(map);
        return new GridPage();
    }
    public void addGenerat(Map<String,String> map)
    {
        policyBtn.addGenerat();
        String hand=tools.swithToWindowByTitle("策略配置");
        System.out.println(tools.getDriver().getCurrentUrl());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new GeneratFormPage().inputForm(map);

    }
}
