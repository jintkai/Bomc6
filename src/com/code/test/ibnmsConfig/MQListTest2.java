package com.code.test.ibnmsConfig;

import com.code.common.*;
import com.code.page.ibnmsConfig.MQList.MQListPage;
import com.code.page.ibnmsConfig.MQList.domain.MqFormDomain;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by jinkai on 2014/7/9.
 */
public class MQListTest2 extends TestCase {
    MQListPage mqList;
    String rowValue;
    String rowName;
    @Parameters({"node"})
    public MQListTest2(String node)
    {
        super(node);
        mqList=new MQListPage(eventDriver);
        if(DBTools.url.contains("172.21.2.96:3306/bnms_cs")){
            rowName="安装路径";
            rowValue="/jlbnms/bomc5/app/broker1";
        }
        if(DBTools.url.contains("172.21.1.5:1523:bnms")){
            rowName="安装路径";
            rowValue="/test-bnms/app";
        }

    }
//    @BeforeMethod
//    @Parameters({"Action_URL"})
//    public void beforeMethod(String actionUrl)
//    {
//        eventDriver.get(Data.baseUrl + actionUrl);
//    }

    @DataProvider(name="mqList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("MQ",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test( priority = 0,description = "增加MQ")
    public void addMQ()
    {

        GridPage gridPage=new GridPage(eventDriver);
        int r=gridPage.getGridrowNum()+1;

        String sql;
        sql="select * from tb_cfg_deploy_env t LEFT JOIN tb_asset_host b on t.unit_id=b.unit_id where ip_addr not like '172.%' ";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        EnvSearchDomain searchDomain=new EnvSearchDomain();
        searchDomain.setDeviceName(list.get(0).get("device_name"));
        searchDomain.setIpAddr(list.get(0).get("ip_Addr"));
        MqFormDomain mqFormDomain=new MqFormDomain();
        mqFormDomain.setEnvSearchDomain(searchDomain);
        mqFormDomain.setInstallPath("/selenium/mq");
        mqFormDomain.setJmxPort(String.valueOf(tools.random()));
        mqFormDomain.setMqPort(String.valueOf(tools.random()));
        mqFormDomain.setWebPort(String.valueOf(tools.random()));
        GridPage gridTable=mqList.operateMQ("增加",mqFormDomain);
        System.out.println(mqFormDomain.toString());
        tools.assertEquals(r,gridTable.getGridrowNum(),mqFormDomain.toString());
    }

    @Test(priority = 1,description = "部署MQ")
    public void deployMQ( )
    {
        Map<String,String> map=new HashMap<>();
        System.out.println(rowName+":"+rowValue);
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=mqList.deploy("部署",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        System.out.println(MqMap.toString());
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"已部署",MqMap);
    }
    @Test(priority = 1,description = "启动MQ",dependsOnMethods = "deployMQ")
    public void startMQ( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=mqList.deploy("启动",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"运行中",map);
    }
    @Test(priority = 1,description = "停止MQ",dependsOnMethods = "startMQ")
    public void stopMQ( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=mqList.deploy("停止",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"已停止",map);
    }
    @Test(priority = 1,description = "停止MQ",dependsOnMethods = "stopMQ")
    public void updeployMQ( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=mqList.deploy("卸载",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"未部署",map);

        mqList.deploy("部署",map);
        mqList.deploy("启动",map);
    }
}
