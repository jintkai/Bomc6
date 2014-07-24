package com.code.test.ibnmsConfig;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.alarmConfUpdate.AlarmConfUpdateList;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/7/23.
 */
public class AlarmConfUpdateListTest extends TestCase {
    public AlarmConfUpdateList alarmConfUpdate=new AlarmConfUpdateList();
    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="alarmFrame")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("ALARMCONFUPDATA_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "alarmFrame",description = "查询更新列表")
    public void search(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=alarmConfUpdate.search(map);
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }
}
