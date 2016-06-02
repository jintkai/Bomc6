package com.code.page.unicom.main.page;

import com.code.common.Page;
import com.code.page.unicom.common.ResultDivPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Map;

/**
 * Created by Administrator on 16-6-1.
 */
public class NumRulePage extends Page {
    public NumRulePage(EventFiringWebDriver webDriver){
        super(webDriver);
        resultDiv=new ResultDivPage(webDriver);
        searchPage=new NumRuleSearchPage(webDriver);
    }
    public NumRuleSearchPage searchPage;
    public ResultDivPage resultDiv;
    @FindBy(how= How.ID,using="validForm")
    WebElement validForm;
    @FindBy(how=How.ID,using = "btn_add")
    WebElement btnAdd;
    @FindBy(how=How.ID,using="btn_sub")
    WebElement btnSub;
    @FindBy(how=How.ID,using = "good_name")
    WebElement goodName;
    @FindBy(how=How.ID,using="good_kind")
    WebElement goodKind;
    @FindBy(how = How.ID,using="good_level")
    WebElement goodLevel;
    @FindBy(how=How.ID,using="good_note") //靓号AB属性
    WebElement goodNote;
    @FindBy(how=How.ID,using="exp_type") //表达式类型
    WebElement expType;
    @FindBy(how=How.ID,using="good_regix")
    WebElement goodRegix;
    @FindBy(how=How.ID,using="pre_pay")
    WebElement prePay;
    @FindBy(how=How.ID,using="pre_pay_comm")
    WebElement prePayComm;
    @FindBy(how=How.ID,using="limit_fee")
    WebElement limitFee;
    @FindBy(how=How.ID,using="limit_month")
    WebElement limitMonth;
    @FindBy(how=How.ID,using="good_pr")
    WebElement goodPr;
    @FindBy(how=How.ID,using="province_code")
    WebElement provinceCode;
    @FindBy(how=How.ID,using="region_code")
    WebElement regionCode;
    @FindBy(how=How.ID,using="is_affirm")
    WebElement isAffirm;
    @FindBy(how=How.ID,using="note")
    WebElement note;
    @FindBy(how = How.ID,using="btn_addsub")
    WebElement btnAddSub;
    public void add(Map<String,String> map){
        tools.click(btnAdd);
        editForm(map);
    }
    public void editForm(Map<String,String> map){
        tools.sendKeys(goodName,tools.getMapValue(map,"靓号规则名称"));
        tools.selectByVisibleText(goodKind,tools.getMapValue(map,"靓号规则分类"));
        tools.selectByVisibleText(goodLevel,tools.getMapValue(map,"靓号级别"));
        tools.sendKeys(goodNote,tools.getMapValue(map,"靓号AB属性"));
        tools.selectByVisibleText(expType,tools.getMapValue(map,"表达式类型"));
        tools.sendKeys(goodRegix,tools.getMapValue(map,"靓号规则表达式"));
        tools.sendKeys(prePay,tools.getMapValue(map,"预存款"));
        tools.sendKeys(prePayComm,tools.getMapValue(map,"普通预存款"));
        tools.sendKeys(limitFee,tools.getMapValue(map,"月承诺通信费"));
        tools.sendKeys(limitMonth,tools.getMapValue(map,"协议期"));
        tools.sendKeys(goodPr,tools.getMapValue(map,"优先级"));
        tools.sendKeys(provinceCode,tools.getMapValue(map,"省份编码"));
        tools.sendKeys(regionCode,tools.getMapValue(map,"地市编码"));
        tools.selectByVisibleText(isAffirm,tools.getMapValue(map,"是否需要确认"));
        tools.sendKeys(note,tools.getMapValue(map,"备注"));
        tools.click(btnAddSub);
        tools.alertAccept();
    }
    public void numRuleSearch(Map<String,String> map){
        searchPage.searchNumRule(map);
    }
    public void numRuleDelete(int rowNum){
        resultDiv.deleteRow(rowNum-1);
    }

}
