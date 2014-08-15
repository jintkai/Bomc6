package com.code.page.ibnmsConfig.alarmConfigFrame.page;

import com.code.common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

/**
 * Created by Jin on 2014/8/12.
 */
public class AlarmExpressionPage extends Page {
    //告警描述表达式-编辑，按钮
    @FindBy(className = "expression-desc-btn")
    WebElement expressionDescBtn;
    //泛函数
    @FindBy(name = "funFormUrl")
    WebElement funFormUrl;

    //告警等级表达式通用的，增加按钮
    //@FindBy(className = "btn-add-fun")
    //@FindBy(xpath = ".//[@name=\"btn-add-fun\"]")
    List<WebElement> funAddBtn;

    //告警等级表达式，编辑，按钮
    List<WebElement> expressionLevelBtn;
    /*@FindBy(className = "expression-level-btn")
    WebElement expressionLevelBtn;*/
    //条件设置，类型；
    /*@FindBy(className = "conditionType")
    WebElement conditionType;
    //条件值，数字
    @FindBy(name="number")
    WebElement number;
    //区间值
    @FindBy(name="minimum")
    WebElement minimum;
    @FindBy(name="maximum")
    WebElement maximum;
    //关键值
    @FindBy(name="keyword")
    WebElement keyword;
    //包含
    @FindBy(name="equalsStr")
    WebElement equalsStr;
    //时间段
    @FindBy(name = "beginTime")
    WebElement beginTime;
    @FindBy(name = "endTime")
    WebElement endTime;
*/
    //@FindBy(className = "conditionType")
    List<WebElement> conditionType;
    //条件值，数字
    //@FindBy(name="number")
    List<WebElement> number;
    //区间值
    //@FindBy(name="minimum")
    List<WebElement> minimum;
    //@FindBy(name="maximum")
    List<WebElement> maximum;
    //关键值
    //@FindBy(name="keyword")
    List<WebElement> keyword;
    //包含
    //@FindBy(name="equalsStr")
    List<WebElement> equalsStr;
    //时间段
    //@FindBy(name = "beginTime")
    List<WebElement> beginTime;
    //@FindBy(name = "endTime")
    List<WebElement> endTime;

    public void initWebElements()
    {
        funAddBtn= tools.findElements(tools.getDriver(), By.className("btn-add-fun"));
        expressionLevelBtn=tools.findElements(tools.getDriver(),By.className("expression-level-btn"));
        conditionType=tools.findElements(tools.getDriver(),By.className("conditionType"));
        number=tools.findElements(tools.getDriver(),By.name("number"));
        minimum=tools.findElements(tools.getDriver(),By.name("minimum"));
        maximum=tools.findElements(tools.getDriver(),By.name("maximum"));
        equalsStr=tools.findElements(tools.getDriver(),By.name("equalsStr"));
        keyword=tools.findElements(tools.getDriver(),By.name("keyword"));

    }
    public void operate(Map<String,String> map)
    {
        initWebElements();

        tools.click(expressionDescBtn);
        tools.selectByVisibleText(funFormUrl,tools.getMapValue(map,"告警描述表达式泛函数"));
        tools.click(funAddBtn.get(0));
        tools.click(expressionDescBtn);
        for(int i=0;i<3;i++) {
            String str="";
            if (i==0)
                str="严重告警";
            if (i==1)
                str="重要告警";
            if (i==2)
                str="一般告警";
            if (tools.getMapValue(map,"条件设置_"+str).isEmpty())
                continue;
            tools.click(expressionLevelBtn.get(i));
            tools.selectByVisibleText(conditionType.get(i), tools.getMapValue(map, "条件设置_"+str));
            tools.sendKeys(number.get(i), tools.getMapValue(map, "NUMBER_"+str));
            tools.sendKeys(minimum.get(i),tools.getMapValue(map,"MINIMUM_"+str));
            tools.sendKeys(maximum.get(i),tools.getMapValue(map,"MAXIMUM_"+str));
            tools.sendKeys(equalsStr.get(i),tools.getMapValue(map,"EQUALSSTR_"+str));
            tools.sendKeys(keyword.get(i),tools.getMapValue(map,"KEYWORD_"+str));
            tools.click(funAddBtn.get(i+1));
            tools.click(expressionLevelBtn.get(i));
        }


    }
}