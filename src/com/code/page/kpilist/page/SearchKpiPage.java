package com.code.page.kpilist.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 06/07/2014.
 */
public class SearchKpiPage extends Page {
    @FindBy(name = "filter.kpiId")
    WebElement kpiId;
    @FindBy(name = "filter.kpiName")
    WebElement kpiName;
    @FindBy(id = "btn-search")
    WebElement btnSearch;

    public GridPage searchByID(String id) {
        tools.clear(kpiName);
        tools.sendKeys(kpiId, id);
        //tools.submit(kpiId);
        tools.click(btnSearch);
        return new GridPage();
    }

    public GridPage searchByName(String name) {
        tools.clear(kpiId);
        tools.sendKeys(kpiName, name);
        //tools.submit(kpiId);
        tools.click(btnSearch);
        return new GridPage();
    }

    public GridPage searchByMulti(String id, String name)
    {
        tools.sendKeys(kpiId,id);
        tools.sendKeys(kpiName,name);
        //tools.submit(kpiId);
        tools.click(btnSearch);
        return new GridPage();
    }
    public GridPage search(Map<String,String> map)
    {
        tools.sendKeys(kpiId,tools.getMapValue(map,"指标编号"));
        tools.sendKeys(kpiName,tools.getMapValue(map,"指标名称"));
        tools.click(btnSearch);
        return new GridPage();
    }

}
