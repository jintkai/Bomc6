package com.code.test.bnms;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.bnms.kpilist.KpiListFramePage;
import jxl.read.biff.BiffException;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 06/07/2014.
 */
public class KpiFrameTest extends TestCase {
    KpiListFramePage kpiFrame;
    GridPage gridTable;
    @Parameters({"node"})
    public KpiFrameTest(String node)
    {
        super(node);
        kpiFrame=new KpiListFramePage(eventDriver);
        gridTable=new GridPage(eventDriver);
        Reporter.log("selenium Grid:" + node);
    }
//    @BeforeMethod
//    @Parameters({"Action_URL"})
//    public void beforeMethod(String actionUrl)
//    {
//        eventDriver.get(Data.baseUrl + actionUrl);
//    }

    @DataProvider(name="kpiList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("KPI管理",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider="kpiList",priority = 0,description = "查询KPI")
    public void searchKPI(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),
                map.toString());
    }
    @Test(dataProvider="kpiList",priority = 1,description = "按Tree查询KPI")
    public void searchKPIByTree(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        gridTable=kpiFrame.searchByTree(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),
                map.toString());
    }
    @Test(dataProvider = "kpiList",priority = 2,description = "KPI操作，增加、修改、删除；")
    public void operateKPI(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        kpiFrame.operateKpi(map);
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),
               map.toString());
    }
}

