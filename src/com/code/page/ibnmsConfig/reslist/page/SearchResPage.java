package com.code.page.ibnmsConfig.reslist.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 07/07/2014.
 */
public class SearchResPage extends Page {
    @FindBy(name="filter.unitId")
    WebElement unitId;
    @FindBy(name="filter.deviceName")
    WebElement deviceName;
    @FindBy(name="filter.ipAddr")
    WebElement ipAddr;
    WebElement filterBzTypeKbpClass;
    @FindBy(id = "btn-search")
    WebElement btnSearch;

    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(unitId,tools.getMapValue(map,"资源UNIT_ID_RES"));
        tools.sendKeys(deviceName,tools.getMapValue(map,"资源名称_RES"));
        tools.sendKeys(ipAddr,tools.getMapValue(map,"IP地址_RES"));
        tools.selectByVisibleText(filterBzTypeKbpClass,tools.getMapValue(map,"业务类型_RES"));
        tools.click(btnSearch);
        return  new GridPage();
    }
}
