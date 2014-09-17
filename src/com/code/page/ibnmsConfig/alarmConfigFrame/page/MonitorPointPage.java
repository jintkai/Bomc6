package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import com.code.page.ibnmsConfig.kpilist.KpiListFramePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by jinkai on 2014/7/16.
 */
public class MonitorPointPage extends Page {
    String frame="alarmConfigFrame";
    @FindBy(name="event.unitName")
    WebElement unitName;
    @FindBy(id="kbpId")
    WebElement kbpId;
   // @FindBy(css = "btn-select-kpi")
    @FindBy(className = "btn-select-kpi")
    WebElement btnSelectKpi;
    @FindBy(name="event.subUnitId")
    WebElement subUnitId;
    public void add(Map<String,String> map)
    {
        inputForm(map);
    }
    public void inputForm(Map<String,String> map)
    {


        if(tools.getMapValue(map,"指标名称").isEmpty())
            return;
//        tools.click(btnSelectKpi);
        tools.openModelDialog(btnSelectKpi);
        KpiListFramePage kpiList=new KpiListFramePage(eventDriver);
        String hand=tools.switchToWindowByTitle(kpiList.title);
        kpiList.search(map).selectAllTr();
        kpiList.kpiBtn.select();
        tools.switchToWindowByHand(hand);
        tools.switchToFrame(frame);

        tools.selectByVisibleText(kbpId,tools.getMapValue(map,"业务系统"));
        tools.selectByVisibleText(subUnitId,tools.getMapValue(map,"子实体"));
    }
}
