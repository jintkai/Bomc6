package com.code.bnms.pmalarmList.page;

import com.code.common.BtnPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Jin on 2014/8/10.
 */
public class PaBtnPage extends BtnPage {

    public PaBtnPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    public void deploy()
    {
        tools.click(deploy);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tools.click(deploy_ok);
    }
    public void remove()
    {
        tools.click(remove);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tools.click(deploy_ok);
    }
}
