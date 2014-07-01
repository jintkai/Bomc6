package com.code.test;

import com.code.common.ExcelDriver;
import com.code.common.GridTablePage;
import com.code.common.TestCase;
import com.code.page.kpilist.KpiListPage;
import jxl.read.biff.BiffException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KpiListTest extends TestCase {
    KpiListPage kpiList=new KpiListPage();

    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionURL)
    {
        TestCase.eventDriver.get(baseUrl+actionURL);
        kpiList.init();
    }

    @DataProvider(name="kpiList")
    public Iterator dataForKpi(Method method) throws IOException, BiffException {
        return new ExcelDriver("CASE",method.getName());
    }

    @Test(dataProvider="kpiList")
    public void searchKpiByID(String[] obj)
    {
        GridTablePage gridTable= kpiList.getGridTableByID(obj[5]);
        Assert.assertTrue(gridTable.equalsSearch("./td[2]", Integer.parseInt(obj[3]), obj[5]), "searchByClass,”√¿˝ ß∞‹£¨Case ID:" + obj[1]);
    }

    @Test(dataProvider="kpiList")
    public void searchKpiByName(String[] obj)
    {
        GridTablePage gridTable = kpiList.getGridTableByName(obj[5]);
        Assert.assertTrue(gridTable.equalsSearch("./td[4]",Integer.parseInt(obj[3]),obj[5]),"searchByCaption,”√¿˝ ß∞‹£¨Case ID:"+obj[1]);
    }

    @Test(dataProvider="kpiList")
    public void searchKpiByMulit(String[] obj)
    {
        GridTablePage gridTable = kpiList.getGridTableByMulit(obj[5],obj[6]);
        Assert.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(obj[3]),obj[5]),"searchMulit,ID”√¿˝ ß∞‹£¨Case ID:"+obj[1]);
        Assert.assertTrue(gridTable.equalsSearch("./td[4]",Integer.parseInt(obj[3]),obj[6]),"searchMulit,Name”√¿˝ ß∞‹£¨Case ID:"+obj[1]);
    }
}
