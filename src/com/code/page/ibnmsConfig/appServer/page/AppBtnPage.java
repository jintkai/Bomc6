package com.code.page.ibnmsConfig.appServer.page;

import com.code.common.BtnPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Jin on 2014/8/10.
 */
public class AppBtnPage extends BtnPage {
    public AppBtnPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    public void remove()
    {
        tools.click(remove);
        tools.alertAccept();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tools.click(deploy_ok);
    }

    public void deploy()
    {
        tools.click(deploy);
        tools.alertAccept();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tools.click(deploy_ok);
    }

    public void shutdown()
    {
        tools.click(shutdown);
        tools.alertAccept();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tools.click(deploy_ok);
    }
    public void startup()
    {
        tools.click(startup);
        tools.alertAccept();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tools.click(deploy_ok);
    }
}
