package com.code.test.unicom;

import com.code.common.Data;
import com.code.common.TestCase2;
import com.code.page.unicom.common.ResultDivPage;
import com.code.page.unicom.main.MainPage;
import com.code.page.unicom.main.page.NumRulePage;
import com.code.page.unicom.main.page.NumRuleSearchPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Administrator on 16-6-1.
 */
public class NumRuleTest extends TestCase2 {
    MainPage mainPage=new MainPage(eventDriver);
    NumRulePage numRulePage=new NumRulePage(eventDriver);
    //NumRuleSearchPage searchPage=new NumRuleSearchPage(eventDriver);
    ResultDivPage resultDiv=new ResultDivPage(eventDriver);
    SimpleDateFormat df = new SimpleDateFormat("yymmss");
    String nowTime=df.format(new Date());
    @Parameters({"node"})
    public NumRuleTest(String node) {
        super(node);
    }
    @BeforeTest
    @Parameters({"Base_URL"})
    public void beforeMethod(String base_url)
    {
        eventDriver.get(Data.baseUrl);
        beforeClass(base_url);
    }

    @Test(description = "增加普通靓号" )
    public void addNumRule(){
        mainPage.menuPage.selectMenu("号码属性维护","靓号规则维护");
        map=new HashMap<String, String>();
        map.put("靓号规则名称","Test"+nowTime);
        map.put("靓号规则分类","普通靓号");
        map.put("靓号级别","三级");
        map.put("靓号AB属性","靓号AB属性");
        map.put("表达式类型","ABC表达式");
        map.put("靓号规则表达式","AABBCC");
        map.put("预存款","5000");
        map.put("普通预存款","1000");
        map.put("月承诺通信费","188");
        map.put("协议期","36");
        map.put("优先级","1");
        map.put("省份编码","10000");
        map.put("地市编码","100081");
        map.put("是否需要确认","不需要确认");
        map.put("备注","系统测试"+nowTime);
        String result=numRulePage.add(map);
        System.out.println(result);
        tools.assertEquals(result,"操作成功",map);
        tools.alertAccept();
    }

    @Test(description = "查询靓号规则")
    public void searchNumRule()
    {
        tools.refresh();
        mainPage.menuPage.selectMenu("号码属性维护","靓号规则维护");
        map=new HashMap<String, String>();
        map.put("靓号规则名称","Test"+nowTime);
        map.put("规则状态","新增待刷");
        map.put("靓号级别","三级");
        numRulePage.numRuleSearch(map);
        resultDiv.getTabelTh();
        tools.assertEquals(resultDiv.getCount(),1,map);
    }
    //@Test(description = "删除靓号规则",priority = 1)
    public void deleteNumRule(){
        tools.refresh();
        mainPage.menuPage.selectMenu("号码属性维护","靓号规则维护");
        map=new HashMap<String, String>();
        numRulePage.numRuleSearch(map);
        int rowCount=resultDiv.getCount();
        numRulePage.numRuleDelete(1);
        tools.assertEquals(resultDiv.getCount(),rowCount-1,map);
    }
}
