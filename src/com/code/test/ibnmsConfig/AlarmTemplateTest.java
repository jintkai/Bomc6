package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.alarmTemplate.AlarmTemplateListPage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by Jin on 2014/8/4.
 */
public class AlarmTemplateTest extends TestCase {
    AlarmTemplateListPage alarmTemplate;
    @Parameters({"node"})
    public AlarmTemplateTest(String node)
    {
        super(node);
        alarmTemplate=new AlarmTemplateListPage(eventDriver);
    }
    GridPage gridTable=new GridPage(eventDriver);
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="alarmTemplate")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("告警模板配置",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "alarmTemplate",priority = 0,description = "告警模板查询")
    public void searchAlarmTemplate(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=alarmTemplate.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map, "期望值")),map);
    }
    @Test(dataProvider = "alarmTemplate",priority = 1,description = "告警模板增加")
    public void operateAlarmTemplate(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        alarmTemplate.operate(map);
        gridTable=alarmTemplate.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map, "期望值")),map);
    }
    @Test(dataProvider = "alarmTemplate",priority = 2,description = "告警模板KPI增加")
    public void operateTemplateKPI(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        alarmTemplate.operateTemplateKPI(map);
    }
}
