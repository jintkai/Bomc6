package com.code.bnms.alarmShield;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.bnms.alarmShield.page.AlarmShieldBtnPage;
import com.code.bnms.alarmShield.page.AlarmShieldFormPage;
import com.code.bnms.alarmShield.page.SearchAlarmShieldPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/9/4.
 * 告警屏蔽过滤器
 */
public class AlarmShieldCfgPage extends Page {
    public AlarmShieldCfgPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    GridPage gridTable=new GridPage(eventDriver);
    AlarmShieldBtnPage shieldBtn=new AlarmShieldBtnPage(eventDriver);
    SearchAlarmShieldPage searchShield=new SearchAlarmShieldPage();
    AlarmShieldFormPage shieldForm=new AlarmShieldFormPage();

    public GridPage search(Map<String,String> map)
    {
        gridTable=searchShield.search(map);
        return  gridTable;
    }
    public GridPage opearte(Map<String,String> map)
    {
        String operation=tools.getMapValue(map,"操作类型");
        if (operation.equals("增加")) {
            shieldBtn.add();
            String hand=tools.switchToWindowByTitle(shieldForm.title);
            shieldForm.opearete(map);
            tools.switchToWindowByHand(hand);
        }
        if (operation.equals("修改")) {
            search(map).selectTr(0);
            shieldBtn.edit();
            String hand=tools.switchToWindowByTitle(shieldForm.title);
            shieldForm.opearete(map);
            tools.switchToWindowByHand(hand);
        }
        if (operation.equals("删除")) {
            search(map).selectTr(0);
            shieldBtn.delete();
            tools.alertAccept();
        }
        return gridTable;
    }
}
