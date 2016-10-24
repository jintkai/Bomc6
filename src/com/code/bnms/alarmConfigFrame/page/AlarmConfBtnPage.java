package com.code.bnms.alarmConfigFrame.page;

import com.code.common.BtnPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by jinkai on 2014/7/16.
 * 按钮
 */
public class AlarmConfBtnPage extends BtnPage {
    public AlarmConfBtnPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(id="btn-add-select")
    WebElement btnAddSelect;
    @FindBy(id="btn-export-as-excel")
    WebElement exportBtn;

    @FindBy(id="btn-to-be-filtered")
    WebElement filteredBtn;
    @FindBy(id="queryFilter_add")
    WebElement queryAddBtn;
    //过滤器下拉列表
    @FindBy(id="queryFilter")
    public WebElement queryFilter;
    @FindBy(id="queryFilter_remove")
    public WebElement removeFilter;
    public AlarmConfFormPage addConf(String str)
    {
        tools.moveToElement(this.addBtn);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tools.selectByVisibleText(btnAddSelect,str);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AlarmConfFormPage();
    }
    public void exportExcel()
    {
        tools.click(exportBtn);
        tools.executeByAutoIT(".\\res\\download.exe");
    }
    public void addfilter()
    {
        tools.click(filteredBtn);
    }
    public void openEditedList()
    {
        List<WebElement> lists=tools.findElements(tools.getDriver(), By.tagName("a"));
        WebElement[] elements=new WebElement[lists.size()];
        lists.toArray(elements);
        for (int i=0;i<elements.length;i++)
        {
            if(tools.getAttribute(elements[i],"Title").contains("变更"))
            {tools.click(elements[i]);
                return;}
            System.out.println("无法点击");
        }
    }

    public void openFilteredList()
    {
        List<WebElement> lists=tools.findElements(tools.getDriver(), By.tagName("a"));
        WebElement[] elements=new WebElement[lists.size()];
        lists.toArray(elements);
        for (int i=0;i<elements.length;i++)
        {
            if(tools.getAttribute(elements[i],"Title").contains("过滤"))
            {tools.click(elements[i]);
                return;}
            System.out.println("无法点击");
        }
    }
    public void queryFilterAdd(String str)
    {
        tools.click(queryAddBtn);
        tools.alertSetText(str);
    }
    public void queryFilterRemove(String str)
    {
        tools.selectByVisibleText(queryFilter,str);
        tools.click(removeFilter);
        tools.alertAccept();
    }
}
