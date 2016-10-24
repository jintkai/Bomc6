package com.code.bnms.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Jin on 2014/8/12.
 */
public class AlarmConfListPage extends Page {
    public String title="告警配置列表";
    public AlarmConfBtnPage btnPage;
    public AlarmConfListPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
        btnPage=new AlarmConfBtnPage(eventDriver);
    }
}
