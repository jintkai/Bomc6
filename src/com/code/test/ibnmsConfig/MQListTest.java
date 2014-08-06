package com.code.test.ibnmsConfig;

import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.MQList.MQListPage;
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
 * Created by jinkai on 2014/7/9.
 */
public class MQListTest extends TestCase {
    MQListPage mqList=new MQListPage();
    @BeforeMethod
    @Parameters({"Base_URL","Action_URL"})
    public void beforeMethod(String baseUrl,String actionUrl)
    {
        TestCase.eventDriver.get(baseUrl + actionUrl);
    }

    @DataProvider(name="mqList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("MQ_NEW",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(dataProvider="mqList")
    public void addMQ(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        if(option.equals("增加")) {
            mqList.add(map);
        }
        if (option.equals("修改"))
        {
            mqList.edit(map);
        }
        if (option.equals("删除"))
        {
            mqList.delete(map);
        }
        GridPage gridTable=new GridPage();
        ArrayList list=gridTable.getListOftr("MQ名称",tools.getMapValue(map,"MQ名称"));
        tools.assertEquals(list.size(),tools.getMapValue(map,"期望值"),map);
    }

    @Test(dataProvider="mqList")
    public void deployMQ(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        mqList.deploy(map);
        GridPage gridTable=new GridPage();
        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr("MQ名称", tools.getMapValue(map, "选择名称")).get(0));
        if (option.equals("部署") || option.equals("卸载")) {
            tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),tools.getMapValue(map,"期望值"),map);
        }
        if (option.equals("启动") || option.equals("停止")) {
            tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),tools.getMapValue(map,"期望值"),map);
        }
    }
}
