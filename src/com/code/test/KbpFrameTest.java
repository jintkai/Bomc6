package com.code.test;

import com.code.common.ExcelDriver;
import com.code.common.GridTablePage;
import com.code.common.TestCase;
import com.code.page.kbplist.KbpListFramePage;
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
public class KbpFrameTest extends TestCase {
    KbpListFramePage kbpFrame=new KbpListFramePage();

    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl+actionUrl);
        kbpFrame.init();
    }

    @DataProvider(name="kbpList")
    public Iterator dataForKbp(Method method) throws IOException, BiffException {
        return new ExcelDriver("KBP",method.getName());
    }

    //@Test(dataProvider = "kbpList")
    public void searchKbpByTreeCaption(String[] obj)
    {
        GridTablePage gridTable=kbpFrame.searchKbpByTree(obj[5]);
        tools.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(obj[3]), obj[6]));
    }

    //@Test(dataProvider = "kbpList")
    public void searchKbpByTreeClass(String[] obj)
    {
        GridTablePage gridTable=kbpFrame.searchKbpByTree(obj[5]);
        tools.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(obj[3]), obj[5]));
    }

    @Test(dataProvider = "kbpList")
    public void searchByClass(String[] obj)
    {
        GridTablePage gridTable=kbpFrame.searchByClass(obj[5]);
        tools.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(obj[3]), obj[5]));
    }

  //  @Test(dataProvider = "kbpList")
    public void searchByCaption(String[] obj)
    {
        GridTablePage gridTable=kbpFrame.searchByCaption(obj[5]);
        Assert.assertEquals(gridTable.getRowNum(), Integer.parseInt(obj[3]), "KBPTree��ѯ�������");
        Assert.assertTrue(gridTable.equalsSearch("./td[3]", gridTable.getRowNum(), obj[5]), "KBPTree��ѯ������");
    }

    //@Test(dataProvider = "kbpList")
    public void searchByMulti(String[] obj)
    {
        GridTablePage gridTable=kbpFrame.searchByMulti(obj[5],obj[6]);
        Assert.assertTrue(gridTable.equalsSearch("./td[2]",Integer.parseInt(obj[3]),obj[5]),"searchMulit,����ʧ�ܣ�Case ID:"+obj[1]);
        Assert.assertTrue(gridTable.equalsSearch("./td[3]",Integer.parseInt(obj[3]),obj[6]),"searchMulit,����ʧ�ܣ�Case ID:"+obj[1]);
    }

}
