package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.agentList.AgentListPage;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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
        ExcelDriver excelDriver=new ExcelDriver("AG_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider = "agList")
    public void search(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=agList.search(map);
        tools.assertEquals(gridTable.getRowNum(),map,"期望值");
    }
    @Test(dataProvider="agList")
    public void addAG(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        if(option.equals("增加")) {
            agList.add(map);
        }
        if (option.equals("修改"))
        {
            agList.edit(map);
        }
        if (option.equals("删除"))
        {
            agList.delete(map);
        }
        GridPage gridTable=new GridPage();
        ArrayList list=gridTable.getListOftr("Agent名称",tools.getMapValue(map,"Agent"));
        tools.assertEquals(list.size(),map,"期望值");
    }
    @Test(dataProvider="agList")
    public void deployAG(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        agList.deploy(map);
        GridPage gridTable=new GridPage();
        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr("Agent名称", tools.getMapValue(map, "选择名称")).get(0));
        if (option.equals("部署") || option.equals("卸载")) {
            tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),map,"期望值");
        }
        if (option.equals("启动") || option.equals("停止")) {
            tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),map,"期望值");
        }
    }
}
