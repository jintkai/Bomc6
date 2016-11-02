package com.code.test.bnms;

import com.code.common.*;
import com.code.bnms.MQList.MQListPage;
import com.code.bnms.MQList.domain.MqFormDomain;
import com.code.bnms.envList.domain.EnvSearchDomain;
import jxl.read.biff.BiffException;
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
        rowName=Config.getProperty("mqKey");
        rowValue=Config.getProperty("mqValue");
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
        mqFormDomain.setInstallPath("/selenium/mq"+tools.formatNow());
        mqFormDomain.setJmxPort(String.valueOf(tools.random()));
        mqFormDomain.setMqPort(String.valueOf(tools.random()));
        mqFormDomain.setWebPort(String.valueOf(tools.random()));
        GridPage gridTable=mqList.operateMQ("增加",mqFormDomain);
        System.out.println(mqFormDomain.toString());
        tools.assertEquals(r,gridTable.getGridrowNum(),mqFormDomain.toString());
    }
    @Test( priority = 1,description = "删除MQ")
    public void deleteMQ()
    {

        GridPage gridPage=new GridPage(eventDriver);
        int r=gridPage.getGridrowNum()-1;

        String sql;
        sql="select * from tb_cfg_deploy_mq where INSTANCE_NAME like '%selenium%' order by INSTANCE_NAME asc";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);

        Map<String,String> map=new HashMap<>();
        map.put("列名","安装路径");
        map.put("列值",list.get(0).get("install_patch"));
        map.put("操作类型","删除");
        GridPage gridTable=mqList.operateMQ(map);
        tools.assertEquals(r,gridTable.getGridrowNum(),map.toString());
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
        tools.sleep(10000);
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"已部署",MqMap.toString());
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
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"运行中",map.toString());
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
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"已停止",map.toString());
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
        tools.assertEquals((Object)tools.getMapValue(MqMap,"部署状态"),(Object) "未部署",map.toString());

        mqList.deploy("部署",map);
        mqList.deploy("启动",map);
    }
}
