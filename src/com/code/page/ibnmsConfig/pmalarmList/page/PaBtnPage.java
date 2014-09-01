package com.code.page.ibnmsConfig.pmalarmList.page;

import com.code.common.BtnPage;
import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.envList.EnvFramePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/8/10.
 */
public class PaBtnPage extends BtnPage {

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
