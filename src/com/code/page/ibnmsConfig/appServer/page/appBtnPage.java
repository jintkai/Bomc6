package com.code.page.ibnmsConfig.appServer.page;

import com.code.common.BtnPage;

/**
 * Created by Jin on 2014/8/10.
 */
public class AppBtnPage extends BtnPage {
    public void remove()
    {
        tools.click(remove);
        tools.alertAccept();
        tools.click(deploy_ok);
    }

    public void deploy()
    {
        tools.click(deploy);
        tools.alertAccept();
        tools.click(deploy_ok);
    }

    public void shutdown()
    {
        tools.click(shutdown);
        tools.alertAccept();
        tools.click(deploy_ok);
    }
    public void startup()
    {
        tools.click(startup);
        tools.alertAccept();
        tools.click(deploy_ok);
    }
}
