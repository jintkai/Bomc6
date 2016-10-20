package com.code.page.ibnmsConfig.cronsConf.page;

import com.code.common.Page;
import com.code.page.ibnmsConfig.cronsConf.domain.CronsFormDomain;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by jon on 2016/10/20.
 */
public class CronsFormPage extends Page {
    public CronsFormPage(EventFiringWebDriver driver){
        super(driver);
    }
    @FindBy(id="cron.name")
    WebElement cronName;
    @FindBy(name="cron.nameDesc")
    WebElement  nameDesc;
    @FindBy(id="cron.seconds")
    WebElement seconds;
    @FindBy(id="cron.minute")
    WebElement minute;
    @FindBy(id="cron.hour")
    WebElement hour;
    @FindBy(id="cron.day")
    WebElement day;
    @FindBy(id="cron.month")
    WebElement month;
    @FindBy(id="cron.week")
    WebElement week;
    @FindBy(name="cron.year")
    WebElement year;

    @FindBy(id="btn-dialog-save")
    WebElement saveBtn;
    private void inputForm(CronsFormDomain domain){
        tools.sendKeys(cronName,domain.getCronName());
        tools.sendKeys(nameDesc,domain.getCronDesc());
        tools.sendKeys(seconds,domain.getSeconds());
        tools.sendKeys(minute,domain.getMinute());
        tools.sendKeys(hour,domain.getHour());
        tools.sendKeys(day,domain.getDay());
        tools.sendKeys(month,domain.getMonth());
        tools.sendKeys(week,domain.getWeek());
        tools.sendKeys(year,domain.getYear());
        tools.click(saveBtn);
    }
    public void operate(String operation,CronsFormDomain domain){

        inputForm(domain);


    }
}
