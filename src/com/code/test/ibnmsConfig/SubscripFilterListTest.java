package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.subscripFilter.SubscripFilterListPage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by Jin on 2014/8/24.
 */
public class SubscripFilterListTest extends TestCase {
    SubscripFilterListPage subscriptList=new SubscripFilterListPage();
    GridPage gridTable=new GridPage();
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        TestCase.eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="SubscripFilter")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("SUBSCRIPFILTER",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }

    @Test(dataProvider = "SubscripFilter",priority = 0,description = "订阅过滤器查询")
    public void searchSubscripFilter(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        gridTable=subscriptList.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map, "期望值")),map);
    }
    @Test(dataProvider = "SubscripFilter",priority = 0,description = "订阅过滤器查询")
    public void operateSubscripFilter(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        subscriptList.operate(map);
        gridTable=subscriptList.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map, "期望值")),map);
    }
}
