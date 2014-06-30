package com.code.test;

import com.code.common.ExcelDriver;
import com.code.common.TestCase;
import com.code.common.GridTablePage;
import com.code.page.kbplist.KbpListPage;
import jxl.read.biff.BiffException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/6/22.
 */
public class KbpListTest extends TestCase {
    private KbpListPage kbpList=new KbpListPage();
    private GridTablePage gridTable;

    @DataProvider(name="kbplist")
    public Iterator dataForKbpClass(Method method) throws IOException, BiffException {

        System.out.println(method.getName());
        return new ExcelDriver("CASE",method.getName());
    }

    @Test(dataProvider="kbplist")
    public void searchByClass(String[] obj)
    {
        gridTable=kbpList.searchByClass( obj[5]);
        Assert.assertTrue(gridTable.equalsSearch("./td[2]", Integer.parseInt(obj[3]), obj[5]),"Case ID:"+obj[1]);
    }

    @Test(dataProvider="kbplist")
    public void searchByCaption(String[] obj)
    {
       gridTable = kbpList.searchByCaption(obj[5]);
       Assert.assertTrue(gridTable.equalsSearch("./td[3]",Integer.parseInt(obj[3]),obj[5]),"Case ID:"+obj[1]);
    }

    //@Test(dataProvider="kbplist")
    public void searchGroup(String[] obj)
    {
        gridTable = kbpList.searchMulti(obj[5],obj[6]);
        Assert.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(obj[3]),obj[5]),"Case ID:"+obj[1]);
        Assert.assertTrue(gridTable.equalsSearch("./td[3]",Integer.parseInt(obj[3]),obj[6]),"Case ID:"+obj[1]);
    }

    @Test(dataProvider = "kbplist")
    public void addKPI(String[] obj)
    {
        TestCase.eventDriver.get(driver.getCurrentUrl()+"?kbp_class="+obj[5]);
        Assert.assertTrue(kbpList.addKBP(obj),"Ôö¼ÓÊ§°Ü");

    }

    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod( String base_url,String action_url)
    {
       TestCase.eventDriver.get(base_url+action_url);
        kbpList.init();
        gridTable=null;
    }

}
