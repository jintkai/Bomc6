package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.MQList.MQListPage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/9.
 */
public class MQListTest extends TestCase {
    MQListPage mqList=new MQListPage();
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        TestCase.eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="mqList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("MQ",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider="mqList", priority = 0)
    public void operateMQ(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);

        GridPage gridTable=mqList.operateMQ(map);
        ArrayList list=gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据"));
        tools.assertEquals(list.size(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }

    @Test(dataProvider="mqList",priority = 1)
    public void deployMQ(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);

        mqList.deploy(map);
        GridPage gridTable=new GridPage();
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")).get(0));

        tools.assertEquals(tools.getMapValue(MqMap,tools.getMapValue(map,"状态字段")),tools.getMapValue(map,"期望值"),map);

    }
}