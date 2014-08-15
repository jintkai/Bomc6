package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.alarmConfUpdate.AlarmConfUpdateList;
import com.code.page.ibnmsConfig.alarmPolicy.AlarmPolicyList;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by Jin on 2014/7/30.
 */
public class AlarmPolicyTest extends TestCase {
    public AlarmPolicyList alarmPlicy=new AlarmPolicyList();
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        TestCase.eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="alarmpolicy")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("ALARMPOLICY",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "alarmpolicy",priority = 0,description = "告警策略维护查询")
    public void searchAlarmPolicy(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=alarmPlicy.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider = "alarmpolicy",priority = 1,description = "告警策略绑定数量")
    public void bind(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        int row=alarmPlicy.bind(map);
        tools.assertEquals(row,Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }

    @Test(dataProvider = "alarmpolicy",description = "新增告警预警生成策略")
    public void operateGenAlarmPolicy(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        alarmPlicy.addPolicy(map);
        int row=alarmPlicy.search(map).getRowNum();
        tools.assertEquals(row,Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider = "alarmpolicy",description = "新增告警升级策略")
    public void operateUpdatePolicy(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        alarmPlicy.addPolicy(map);
        int row=alarmPlicy.search(map).getRowNum();
        tools.assertEquals(row,Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
}
