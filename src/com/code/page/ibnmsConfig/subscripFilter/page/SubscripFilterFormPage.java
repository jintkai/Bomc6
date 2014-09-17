package com.code.page.ibnmsConfig.subscripFilter.page;

import com.code.common.FormPage;
import com.code.common.GridPage;
import com.code.page.ibnmsConfig.kbplist.KbpListFramePage;
import com.code.page.ibnmsConfig.kbplist.page.KbpFormPage;
import com.code.page.ibnmsConfig.kpilist.KpiListFramePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;
import java.util.Map;

/**
 * Created by Jin on 2014/8/24.
 */
public class SubscripFilterFormPage extends FormPage {
    public SubscripFilterFormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    public String titile="时间段屏蔽";
    @FindBy(id = "token-input-input-params-loginId")
    WebElement loginId;
    @FindBy(id="btn-add-unit")
    WebElement unitAddBtn;
    @FindBy(name = "filter.filterDesc")
    WebElement filterDesc;
    String chooseClass="choose";
    KbpListFramePage kbpFrame=new KbpListFramePage(eventDriver);
    KpiListFramePage kpiFrame=new KpiListFramePage(eventDriver);
    @FindBy(id = "btn-choose-kpi")
    WebElement chooseKpiBtn;

    @FindBy(id="btn-save")
    WebElement savaBtn;

    @FindBy(name="params.period")
    WebElement period;
    @FindBy(id = "params.beginTime")
    WebElement beginTime;
    @FindBy(id = "params.endTime")
    WebElement endTime;

    @FindBy(name="filter.startTime")
    WebElement startTime;
    @FindBy(name="filter.stopTime")
    WebElement stopTime;

    public void inputForm(Map<String,String> map)
    {
        String loginStr=tools.getMapValue(map,"订阅人");
        tools.inputSubscripLogin(loginId,loginStr);
        tools.sendKeys(filterDesc,tools.getMapValue(map,"过滤器描述"));
        inputUnit(map);
        inputKpi(map);
        //document.getElementById("params.beginTime").removeAttribute("readOnly")
        tools.selectByVisibleText(period,tools.getMapValue(map,"屏蔽时段"));
        String js="document.getElementById(\""+tools.getAttribute(beginTime,"id")+"\").removeAttribute(\"readOnly\");";
        js=js+"document.getElementById(\""+tools.getAttribute(beginTime,"id")+"\").value=\""+tools.getMapValue(map,"屏蔽时段开始")+"\";";
        tools.execJS(js);
        js="document.getElementById(\""+tools.getAttribute(endTime,"id")+"\").removeAttribute(\"readOnly\");";
        js=js+"document.getElementById(\""+tools.getAttribute(endTime,"id")+"\").value=\""+tools.getMapValue(map,"屏蔽时段结束")+"\";";
        tools.execJS(js);

        js="document.getElementsByName(\""+tools.getAttribute(startTime,"name")+"\")[0].removeAttribute(\"readOnly\");";
        js=js+"document.getElementsByName(\""+tools.getAttribute(startTime,"name")+"\")[0].value=\""+tools.getMapValue(map,"生效时段开始")+"\";";
        tools.execJS(js);
        js="document.getElementsByName(\""+tools.getAttribute(stopTime,"name")+"\")[0].removeAttribute(\"readOnly\");";
        js=js+"document.getElementsByName(\""+tools.getAttribute(stopTime,"name")+"\")[0].value=\""+tools.getMapValue(map,"生效时段结束")+"\";";
        tools.execJS(js);
        tools.click(savaBtn);
        tools.alertAccept();
    }

    /**
     * 输入Unit
     * @param map
     */
    public void inputUnit(Map<String,String> map)
    {
        int unitNum=Integer.parseInt(tools.getMapValue(map, "监控实体数"));
        for (int i=0;i<unitNum;i++)
        {
            tools.click(unitAddBtn);
        }

        for (int i=0;i<unitNum;i++)
        {
            List<WebElement> list=tools.findElements(tools.getDriver(), By.className(chooseClass));
            tools.openModelDialog(list.get(i));
            String hand=tools.switchToWindowByTitle(kbpFrame.title);
            String kbpStr=tools.getMapValue(map,"监控实体_"+(i+1));
            map.put("KBP",kbpStr);
            kbpFrame.kbpTreeIFrame="unitTree";
            kbpFrame.searchByTree(map);
            tools.switchToFrame(kbpFrame.kbpListIFrame);
            kbpFrame.kbpBtn.cite();
            tools.switchToWindowByHand(hand);
        }
    }
    public void inputKpi(Map<String,String> map)
    {
        if (!tools.getMapValue(map,"告警指标").isEmpty())
        {
            tools.click(chooseKpiBtn);
            String hand=tools.switchToWindowByTitle(kpiFrame.title);
            GridPage gridPage=kpiFrame.search(map);
            gridPage.selectTr(0);
            kpiFrame.select();
            tools.switchToWindowByHand(hand);
        }
    }
}
