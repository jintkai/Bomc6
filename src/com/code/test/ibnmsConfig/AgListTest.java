package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.agentList.AgentListPage;
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
 * Created by jinkai on 2014/7/14.
 */
public class AgListTest extends TestCase {
    public AgentListPage agList;
    @Parameters({"node"})
    public AgListTest(String node)
    {
        super(node);
        agList=new AgentListPage(eventDriver);
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="agList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("AGENT",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "agList",priority = 0,description = "查询Agent")
    public void searchAG(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=agList.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider="agList",priority = 1,description = "增加、修改、删除Agent")
    public void operateAG(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=agList.operateAG(map);
        gridTable=agList.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    @Test(dataProvider="agList",priority = 2,description = "部署、启动、停止、卸载Agent")
    public void deployAG(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=new GridPage(eventDriver);
        gridTable=agList.deployAG(map);
        agList.search(map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(1);
        tools.assertEquals(tools.getMapValue(MqMap,tools.getMapValue(map,"状态字段")),tools.getMapValue(map,"期望值"),map);
    }
}
