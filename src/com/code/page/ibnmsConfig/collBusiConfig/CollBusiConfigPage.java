package com.code.page.ibnmsConfig.collBusiConfig;

import com.code.common.Page;
import com.code.page.ibnmsConfig.collBusiConfig.domain.ShellFormDomain;
import com.code.page.ibnmsConfig.collBusiConfig.page.ShellConfigPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jon on 2016/10/20.
 */
public class CollBusiConfigPage extends Page {

    public CollBusiConfigPage(EventFiringWebDriver driver){
        super(driver);
    }

    @FindBy(id="btn-addSchedule")
    WebElement addScheduleBtn;
    @FindBy(id="btn-addShellButton")
    WebElement addShellBtn;

    public void addShellColl(ShellFormDomain domain){

        tools.click(addScheduleBtn);
        tools.click(addShellBtn);
        ShellConfigPage shellConfigPage=new ShellConfigPage(eventDriver);
        shellConfigPage.opertate("增加",domain);
    }
}
