package com.code.page.ibnmsConfig.alarmPolicy.page;

import com.code.common.GridPage;
import com.code.common.Page;
import com.code.page.ibnmsConfig.kpilist.KpiListFramePage;
import com.code.page.ibnmsConfig.kpilist.page.KpiFormPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/7/30.
 */
public class SearchAlarmPolicyPage extends Page{
    @FindBy(name="filter.policyName")
    WebElement policyName;
    @FindBy(name="unitNamePrefix")
    WebElement unitNamePrefix;
    @FindBy(name="filter.policyType")
    WebElement policyType;
    @FindBy(name="kpiName")
    WebElement kpiName;
    @FindBy(id="btn-search")
    WebElement searchBtn;
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(policyName,tools.getMapValue(map,"策略名称_ALAARMPOLICY"));
        String unitName=tools.getMapValue(map, "应用范围_ALARMPOLICY");
        tools.sendKeys(unitNamePrefix, unitName);
        if (!unitName.isEmpty()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actions.sendKeys(Keys.ARROW_DOWN);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          actions.sendKeys(Keys.ENTER).perform();
        }
        tools.selectByVisibleText(policyType, tools.getMapValue(map, "策略类型_ALARMPOLICY"));
        String str=tools.getMapValue(map,"应用指标_ALARMPOLICY");
        if (!str.isEmpty())
        {
            tools.click(kpiName);
            KpiListFramePage kpiFrame=new KpiListFramePage(eventDriver);
            String hand=tools.switchToWindowByTitle(kpiFrame.title);
            GridPage gridPage=kpiFrame.search(map);
            gridPage.selectTr(1);
            kpiFrame.kpiBtn.select();
            tools.switchToWindowByHand(hand);
        }
        tools.click(searchBtn);
        return new GridPage(eventDriver);
    }
}
