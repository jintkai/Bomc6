package com.code.bnms.alarmTemplate.page;

import com.code.common.GridPage;
import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Jin on 2014/8/4.
 */
public class TemplateFormPage extends Page {
    public TemplateFormPage(EventFiringWebDriver eventDriver)
    {
        super(eventDriver);
    }
    @FindBy(name="alarmTemplateForm.templateName")
    WebElement templateName;
    @FindBy(name="alarmTemplateForm.templateType")
    WebElement templateType;
    @FindBy(name="alarmTemplateForm.templateDesc")
    WebElement templateDesc;
    @FindBy(id="btn-save")
    WebElement saveBtn;
    @FindBy(id="btn-add-s")
    WebElement addKpiBtn;

    GridPage gridTable=new GridPage(eventDriver);

    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(templateName,tools.getMapValue(map,"模板名称"));
        tools.sendKeys(templateDesc,tools.getMapValue(map,"模板描述"));
        tools.selectByVisibleText(templateType,tools.getMapValue(map,"模板类型"));

    }
    public void operate(Map<String,String> map)
    {
        inputForm(map);
        tools.click(saveBtn);
        tools.alarmDismiss();
    }
    public void operateKPI(Map<String,String> map)
    {
        tools.click(addKpiBtn);
        eventTemplateKpiForm templateKpi=new eventTemplateKpiForm();
        templateKpi.operate(map);
    }
}
