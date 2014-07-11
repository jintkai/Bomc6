package com.code.test;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.workstation.WKlistPage;
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
public class WKlistTest extends TestCase {
    WKlistPage wkList=new WKlistPage();
    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="WKlist")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("WORKSTATION",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    //@Test(dataProvider = "WKlist")
    public void addWorkstation(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        if(tools.getMapValue(map,"操作类型").equals(Data.ADDBTN))
            wkList.addWK(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.EDITBTN))
            wkList.editWK(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.DELETEBTN))
            wkList.delWK(map);
        GridPage gridTable=new GridPage();
        tools.assertEquals(gridTable.getListOftr("名称",tools.getMapValue(map,"Workstation名称")).size(),map,"期望值");
    }
    @Test(dataProvider = "WKlist")
    public void deployWK(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        System.out.println(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.DEPLOYBTN))
            wkList.deployWK(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.REMOVEBTN))
            wkList.removeWK(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.STARTBTN))
            wkList.startWK(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.SHUTDOWNBTN))
            wkList.shutdownWK(map);
        GridPage gridTable=new GridPage();
        int i=gridTable.getListOftr("名称",tools.getMapValue(map,"选择名称")).get(0);
        tools.assertEquals(gridTable.getTrOfAllTd(i).get(tools.getMapValue(map,"验证列")),tools.getMapValue(map,"期望值"));
    }
}
