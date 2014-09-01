package com.code.page.ibnmsConfig.MQList.Page;

import com.code.common.BtnPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jinkai on 2014/7/9.
 */
public class MQBtnPage extends BtnPage {
    public void deploy()
    {
        tools.click(deploy);
        tools.click(startDeploy);
        try {
            Thread.sleep(35000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tools.click(deploy_ok);
    }
}
