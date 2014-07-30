package com.code.test.ibnmsConfig;

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
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="alarmpolicy")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("ALARMPOLICY_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "alarmpolicy",description = "告警策略维护查询")
    public void search(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=alarmPlicy.search(map);
        tools.assertEquals(gridTable.getRowNum(),map,"期望值","更新列表数据与预期值不一致");
    }
    @Test(dataProvider = "alarmpolicy",description = "新增告警策略")
    public void add(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        alarmPlicy.addGenerat(map);
    }
}
