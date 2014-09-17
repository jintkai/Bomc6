package com.code.page.ibnmsConfig.agentList.page;

import com.code.common.BtnPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jinkai on 2014/7/14.
 */
public class AgBtnPage extends BtnPage {
    public AgBtnPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @Override
    public void delete()
    {
        tools.click(delBtn);
        tools.alertAccept();
        tools.alertAccept();
    }
}
