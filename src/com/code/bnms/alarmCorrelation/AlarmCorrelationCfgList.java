package com.code.bnms.alarmCorrelation;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.bnms.alarmCorrelation.page.AlarmCorrelationBtnPage;
import com.code.bnms.alarmCorrelation.page.AlarmCorrelationCfgPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/9/11.
 * 数据库宕告警关联配置
 */
public class AlarmCorrelationCfgList extends Page {
    public AlarmCorrelationCfgList(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    public String title="数据库宕告警关联配置列表";
    AlarmCorrelationBtnPage btnPage=new AlarmCorrelationBtnPage(eventDriver);
    AlarmCorrelationCfgPage correlationPage=new AlarmCorrelationCfgPage(eventDriver);
    GridPage gridTable=new GridPage(eventDriver);
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
        if (operation.equals("修改")) {
            gridTable.selectTr(gridTable.getListOftr(tools.getMapValue(map,"验证列"),tools.getMapValue(map,"目标UNITID")).get(0));
            tools.openModelDialog(btnPage.editBtn);
            //btnPage.add();
            String hand=tools.switchToWindowByTitle2(correlationPage.title);
            correlationPage.operate(map);
            tools.switchToWindowByHand(hand);
        }
        if (operation.equals("删除")) {
            gridTable.selectTr(gridTable.getListOftr(tools.getMapValue(map,"验证列"),tools.getMapValue(map,"目标UNITID")).get(0));
            btnPage.delete();
            tools.alertAccept();

        }
        gridTable=new GridPage(eventDriver);
        return  gridTable;
    }
}
