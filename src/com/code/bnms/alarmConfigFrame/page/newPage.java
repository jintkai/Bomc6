package com.code.bnms.alarmConfigFrame.page;

import com.code.common.Page;

import java.util.Map;

/**
 * Created by Jin on 2014/8/26.
 * 告警集中配置的页面；,单指标
 */
public class newPage extends Page {
    public AlarmExpressionPage expressionPage=new AlarmExpressionPage();
    public AlarmClearPage clearPage=new AlarmClearPage();
    public AlarmFilterPage filterPage=new AlarmFilterPage();
    public AlarmUpgradeFrame upgradeFrame=new AlarmUpgradeFrame();

    public void add(Map<String,String> map)
    {
        expressionPage.operate(map);
    }
}
