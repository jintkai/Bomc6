package com.code.test;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.kpilist.KpiListFramePage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
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
    KpiListFramePage kpiFrame=new KpiListFramePage();

    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="kpiList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("KPI_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider="kpiList")
    public void search(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridPage gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider="kpiList")
    public void searchByTree(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridPage gridTable=kpiFrame.searchByTree(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider = "kpiList")
    public void addKPI(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        String option=tools.getMapValue(map,"操作类型");
        if(option.equals("增加")) {
            kpiFrame.add(map);
        }
        if (option.equals("修改"))
        {
            kpiFrame.edit(map);
        }
        if (option.equals("删除"))
        {
            kpiFrame.delete(map);
        }
        GridPage gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    /*@Test(dataProvider="kpiList")
    public void searchKpiByTree(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridPage gridTable=kpiFrame.searchKpiByTree(tools.getMapValue(map,"KBP名称"));
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider="kpiList")
    public void searchKpiById(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridPage gridTable=kpiFrame.searchKpiById(tools.getMapValue(map,"KPI编号"));
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider="kpiList")
    public void searchKpiByName(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridPage gridTable=kpiFrame.searchKpiByName(tools.getMapValue(map,"KPI名称"));
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider="kpiList")
    public void searchKpiByMulti(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridPage gridTable=kpiFrame.searchKpiByMulti(tools.getMapValue(map,"KPI编号"),tools.getMapValue(map,"KPI名称"));
        tools.assertTrue(gridTable.equalsSearch("指标编号",Integer.parseInt(tools.getMapValue(map,"期望值")),
                tools.getMapValue(map,"KPI编号")));
        tools.assertTrue(gridTable.equalsSearch("指标名称",Integer.parseInt(tools.getMapValue(map,"期望值")),
                tools.getMapValue(map,"KPI名称")));
    }

    @Test(dataProvider="kpiList")
    public void addKpi(String obj[])
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridPage gridTable=kpiFrame.addKpi(map);
        tools.assertTrue(gridTable.equalsSearch("指标编号",Integer.parseInt(tools.getMapValue(map,"期望值")),
                tools.getMapValue(map,"KPI编号")));
        tools.assertTrue(gridTable.equalsSearch("指标名称",Integer.parseInt(tools.getMapValue(map,"期望值")),
                tools.getMapValue(map,"KPI名称")));
    }
    @Test(dataProvider = "kpiList",dependsOnMethods = "addKpi")
    public void delKpi(String[] obj)
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridPage gridPage=kpiFrame.delKpi(tools.getMapValue(map,"KPI名称"));
        tools.assertEquals(gridPage.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
    @Test(dataProvider = "kpiList")
    public void editKpi(String obj[])
    {
        map=tools.changeStringToMap(excelHead,obj);
        GridPage gridPage=kpiFrame.editKpi(tools.getMapValue(map,"原始KPI名称"),map);
        tools.assertEquals(gridPage.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")));
    }
*/
}

