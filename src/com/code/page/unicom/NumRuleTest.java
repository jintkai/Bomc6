package com.code.page.unicom;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.TestCase2;
import com.code.unicom.common.ResultDivPage;
import com.code.unicom.main.MainPage;
import com.code.unicom.main.page.NumRulePage;
import jxl.read.biff.BiffException;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

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
    String numRuleName;
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
        numRuleName=tools.getMapValue(map,"靓号规则名称");
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
        map.put("靓号规则名称",numRuleName+nowTime);
        map.put("规则状态","新增待刷");
        map.put("靓号级别","三级");
        numRulePage.numRuleSearch(map);
        resultDiv.getTabelTh();
        tools.assertEquals(resultDiv.getCount(),1,map);
    }
    @Test(description = "查询靓号规则" , dataProvider="numRule",dependsOnMethods = "addNumRuleDataProvider")
    public void searchNumRuleDataProvider(String[] str){
        tools.refresh();
        mainPage.menuPage.selectMenu("号码属性维护","靓号规则维护");
        map=tools.changeStringToMap(excelHead,str);
        map.put("靓号规则名称",tools.getMapValue(map,"靓号规则名称")+nowTime);
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


    @DataProvider(name="numRule")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("靓号规则维护",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }

    @Test(description = "增加普通靓号" , dataProvider="numRule")
    public void addNumRuleDataProvider(String[] str){
        tools.refresh();
        mainPage.menuPage.selectMenu("号码属性维护","靓号规则维护");
        map=tools.changeStringToMap(excelHead,str);
        map.put("靓号规则名称",tools.getMapValue(map,"靓号规则名称")+nowTime);
        /*
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
        */
        //map.put("备注","系统测试"+nowTime);
        String result=numRulePage.add(map);
        System.out.println(result);
        tools.assertTrue(result.contains("操作成功"),"期望值：操作成功；实际值："+result);
        tools.alertAccept();
    }

}
