package com.code.page.ibnmsConfig.alarmShield;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.alarmShield.page.AlarmShieldBtnPage;
import com.code.page.ibnmsConfig.alarmShield.page.AlarmShieldFormPage;
import com.code.page.ibnmsConfig.alarmShield.page.SearchAlarmShieldPage;

import java.util.Map;

/**
 * Created by Jin on 2014/9/4.
 * 告警屏蔽过滤器
 */
public class AlarmShieldCfgPage extends Page {
    GridPage gridTable=new GridPage();
    AlarmShieldBtnPage shieldBtn=new AlarmShieldBtnPage();
    SearchAlarmShieldPage searchShield=new SearchAlarmShieldPage();
    AlarmShieldFormPage shieldForm=new AlarmShieldFormPage();

    public GridPage search(Map<String,String> map)
    {
        gridTable=searchShield.search(map);
        return  gridTable;
    }
    public GridPage opearte(Map<String,String> map)
    {
        shieldBtn.add();
        String hand=tools.switchToWindowByTitle(shieldForm.title);
        shieldForm.opearete(map);
        tools.switchToWindowByHand(hand);
        return gridTable;
    }
}
