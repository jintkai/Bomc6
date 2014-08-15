package com.code.page.ibnmsConfig.collModelList.page;

import com.code.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jinkai on 2014/7/16.
 */
public class CollModelForm extends Page {
    @FindBy(name="form.modelName")
    WebElement modelName;
    @FindBy(name="form.description")
    WebElement description;
    @FindBy(id="mbeanName")
    WebElement mbeanName;
    @FindBy(id="jmxObjName")
    WebElement jmxObjName;
    @FindBy(id="className")
    WebElement className;
    @FindBy(id="form_modelType")
    WebElement form_modelType;
    @FindBy(id="methodName")
    WebElement methodName;
    @FindBy(id="methodDesc")
    WebElement methodDesc;
    @FindBy(id="interval")
    WebElement interval;
    @FindBy( css = "input.btn-style-repeat")
    WebElement repeat;

}
