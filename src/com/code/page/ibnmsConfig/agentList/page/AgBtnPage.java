package com.code.page.ibnmsConfig.agentList.page;

import com.code.common.BtnPage;

/**
 * Created by jinkai on 2014/7/14.
 */
public class AgBtnPage extends BtnPage {
    @Override
    public void delete()
    {
        tools.click(delBtn);
        tools.alertAccept();
        tools.alertAccept();
    }
}