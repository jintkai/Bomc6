package com.code.test;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.performance.PFListPage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/7/11.
 */
public class PFListTest extends TestCase {
    PFListPage pfList=new PFListPage();
    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="PFlist")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("Performance",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
   // @Test(dataProvider = "PFlist")
    public void addPreformance(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        if(tools.getMapValue(map,"操作类型").equals(Data.ADDBTN))
            pfList.addPf(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.EDITBTN))
            pfList.editPf(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.DELETEBTN))
            pfList.delPf(map);
        GridPage gridTable=new GridPage();
        tools.assertEquals(gridTable.getListOftr("Performance名称",tools.getMapValue(map,"Performance名称")).size(),map,"期望值");
    }
    @Test(dataProvider = "PFlist")
    public void deployPf(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        System.out.println(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.DEPLOYBTN))
            pfList.deploy(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.REMOVEBTN))
            pfList.remove(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.STARTBTN))
            pfList.start(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.SHUTDOWNBTN))
            pfList.shutdown(map);
        GridPage gridTable=new GridPage();
        int i=gridTable.getListOftr("Performance名称",tools.getMapValue(map,"选择名称")).get(0);
        tools.assertEquals(gridTable.getTrOfAllTd(i).get(tools.getMapValue(map,"验证列")),tools.getMapValue(map,"期望值"));
    }
}
