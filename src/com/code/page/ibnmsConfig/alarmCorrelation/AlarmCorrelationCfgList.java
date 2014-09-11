package com.code.page.ibnmsConfig.alarmCorrelation;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmCorrelation.page.AlarmCorrelationBtnPage;
import com.code.page.ibnmsConfig.alarmCorrelation.page.AlarmCorrelationCfgPage;

import java.util.Map;

/**
 * Created by Jin on 2014/9/11.
 * 数据库宕告警关联配置
 */
public class AlarmCorrelationCfgList extends Page {
    public String title="数据库宕告警关联配置列表";
    AlarmCorrelationBtnPage btnPage=new AlarmCorrelationBtnPage();
    AlarmCorrelationCfgPage correlationPage=new AlarmCorrelationCfgPage();
    GridPage gridTable=new GridPage();
    public GridPage operate(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("增加")) {
            tools.openModelDialog(btnPage.addBtn);
            //btnPage.add();
            String hand=tools.switchToWindowByTitle2(correlationPage.title);
            correlationPage.operate(map);
            tools.switchToWindowByHand(hand);
        }
        gridTable=new GridPage();
        return  gridTable;
    }
}
