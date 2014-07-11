package com.code.test;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.MQList.MQListPage;
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
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="mqList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("MQ",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }

    @Test(dataProvider="mqList")
    public void addMQ(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        mqList.addMQ(map);
        GridPage gridTable=new GridPage();
    }
    @Test(dataProvider="mqList")
    public void deployMQ(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        mqList.deployMQ(map);
        GridPage gridTable=new GridPage();
        Map<String,String> MqMap=gridTable.getTrOfAllTd(gridTable.getListOftr("MQ名称",tools.getMapValue(map,"MQ名称")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,tools.getMapValue(map,"验证列")),map,"期望值");
    }
}
