package com.code.test;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.agentList.AgentListPage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by jinkai on 2014/7/14.
 */
public class AgListTest extends TestCase {
    public AgentListPage agList=new AgentListPage();
    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="agList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("Agent",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "agList")
    public void addAgent(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        if(tools.getMapValue(map,"操作类型").equals(Data.ADDBTN))
            agList.addAg(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.EDITBTN))
            agList.editAg(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.DELETEBTN))
            agList.delAg(map);
        GridPage gridTable=new GridPage();
        tools.assertEquals(gridTable.getListOftr("Agent名称",tools.getMapValue(map,"Agent名称")).size(),map,"期望值");
    }
    @Test(dataProvider = "agList")
    public void deployAgent(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        System.out.println(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.DEPLOYBTN))
            agList.deploy(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.REMOVEBTN))
            agList.remove(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.STARTBTN))
            agList.start(map);
        if(tools.getMapValue(map,"操作类型").equals(Data.SHUTDOWNBTN))
            agList.shutdown(map);
        GridPage gridTable=new GridPage();
        int i=gridTable.getListOftr("Agent名称",tools.getMapValue(map,"选择名称")).get(0);
        tools.assertEquals(gridTable.getTrOfAllTd(i).get(tools.getMapValue(map,"验证列")),tools.getMapValue(map,"期望值"));
    }
}
