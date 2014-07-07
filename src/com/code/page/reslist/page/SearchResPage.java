package com.code.page.reslist.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public GridPage searchByUnitId(String unitIDStr)
    {
        tools.sendKeys(unitId,unitIDStr);
        tools.click(btnSearch);
        return new GridPage();
    }
    public GridPage searchByName(String name)
    {
        tools.sendKeys(deviceName,name);
        tools.click(btnSearch);
        return new GridPage();
    }

    public GridPage searchByIp(String ip)
    {
        tools.sendKeys(ipAddr,ip);
        tools.click(btnSearch);
        return new GridPage();
    }
    public GridPage searchByType(String type)
    {
        tools.selectByVisibleText(filterBzTypeKbpClass,type);
        tools.click(btnSearch);
        return new GridPage();
    }
    public GridPage searchByMulti(String id,String name,String ip,String type)
    {
        tools.sendKeys(unitId,id);
        tools.sendKeys(deviceName,name);
        tools.sendKeys(ipAddr,ip);
        tools.selectByVisibleText(filterBzTypeKbpClass,type);
        tools.click(btnSearch);
        return new GridPage();
    }
}
