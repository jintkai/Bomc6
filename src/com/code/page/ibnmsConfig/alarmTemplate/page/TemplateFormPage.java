package com.code.page.ibnmsConfig.alarmTemplate.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

/**
 * Created by Jin on 2014/8/4.
 */
public class TemplateFormPage extends Page {
    @FindBy(name="alarmTemplateForm.templateName")
    WebElement templateName;
    @FindBy(name="alarmTemplateForm.templateType")
    WebElement templateType;
    @FindBy(name="alarmTemplateForm.templateDesc")
    WebElement templateDesc;
    @FindBy(id="btn-save")
    WebElement saveBtn;
    public void inputForm(Map<String,String> map)
    {
        tools.sendKeys(templateName,tools.getMapValue(map,"模板名称"));
        tools.sendKeys(templateDesc,tools.getMapValue(map,"模板描述"));
        tools.selectByVisibleText(templateType,tools.getMapValue(map,"模板类型"));
        tools.click(saveBtn);
        tools.alarmDismiss();
    }
    public void add(Map<String,String> map)
    {
        inputForm(map);
    }
}
