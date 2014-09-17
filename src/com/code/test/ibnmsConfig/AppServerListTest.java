package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.appServer.AppServerListPage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jin on 2014/8/10.
 */
public class AppServerListTest extends TestCase {
    AppServerListPage appServerList;
    @Parameters({"node"})
    public AppServerListTest(String node)
    {
        super(node);
        appServerList=new AppServerListPage(eventDriver);
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="AppServerList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("APPSERVER",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }

    @Test(dataProvider = "AppServerList",priority = 0,description = "增加、修改、删除appserver")
    public void operateAPP(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=appServerList.operateApp(map);
        tools.assertEquals(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")).size(),
                Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }

    @Test(dataProvider = "AppServerList",priority = 1,description = "部署、启动、停止、卸载appserver")
    public void deployAPP(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=appServerList.deployApp(map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,tools.getMapValue(map,"状态字段")),tools.getMapValue(map,"期望值"),map);
    }
}
