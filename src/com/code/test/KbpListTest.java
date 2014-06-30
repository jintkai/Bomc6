package com.code.test;

import com.code.common.ExcelDriver;
import com.code.common.GridTablePage;
import com.code.common.TestCase;
import com.code.page.kbplist.KbpListPage;
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
public class KbpListTest extends TestCase{
    private KbpListPage kbplist=new KbpListPage();

    @DataProvider(name="kbplist")
    public Iterator dataForKbpClass(Method method) throws IOException, BiffException {

        System.out.println(method.getName());
        return new ExcelDriver("CASE",method.getName());
    }

    @Test(dataProvider="kbplist")
    public void searchByClass(String[] obj)
    {
        GridTablePage gridTable= kbplist.getTableByClass(obj[5]);
        Assert.assertTrue(gridTable.equalsSearch("./td[2]", Integer.parseInt(obj[3]), obj[5]), "Case ID:" + obj[1]);
    }

    @Test(dataProvider="kbplist")
    public void searchByCaption(String[] obj)
    {
        GridTablePage gridTable = kbplist.getTableByCaption(obj[5]);
        Assert.assertTrue(gridTable.equalsSearch("./td[3]",Integer.parseInt(obj[3]),obj[5]),"Case ID:"+obj[1]);
    }

    //@Test(dataProvider="kbplist")
    public void searchMulit(String[] obj)
    {
        GridTablePage gridTable = kbplist.getTableByMulit(obj[5],obj[6]);
        Assert.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(obj[3]),obj[5]),"Case ID:"+obj[1]);
        Assert.assertTrue(gridTable.equalsSearch("./td[3]",Integer.parseInt(obj[3]),obj[6]),"Case ID:"+obj[1]);
    }

    @Test(dataProvider = "kbplist")
    public void addKPI(String[] obj)
    {
        TestCase.eventDriver.get(TestCase.eventDriver.getCurrentUrl()+"?kbp_class="+obj[5]);
        Assert.assertEquals(kbplist.addKbp(obj),1,"”√¿˝ ß∞‹£¨CASE ID£∫"+obj[1]);
    }

    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod( String base_url,String action_url)
    {
        TestCase.eventDriver.get(base_url + action_url);
        kbplist.init();
    }
}
