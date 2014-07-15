package com.code.test.ibnmsConfig;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.kbplist.KbpListFramePage;
import jxl.read.biff.BiffException;
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
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="kbpList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("KBP_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "kbpList")
    public void search(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=kbpFrame.search(map);
        tools.assertEquals(gritTable.getRowNum(),map,"期望值");
    }
    @Test(dataProvider = "kbpList")
    public void searchByTree(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gritTable=kbpFrame.searchByTree(map);
        tools.assertEquals(gritTable.getRowNum(),map,"期望值");
    }
    @Test(dataProvider="kbpList")
    public void addKBP(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        if(option.equals("增加")) {
            kbpFrame.add(map);
        }
        if (option.equals("修改"))
        {
            kbpFrame.edit(map);
        }
        if (option.equals("删除"))
        {
            kbpFrame.delete(map);
        }
        GridPage gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }
}
