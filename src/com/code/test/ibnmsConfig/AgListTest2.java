package com.code.test.ibnmsConfig;

import com.code.common.*;
import com.code.page.ibnmsConfig.agentList.AgentListPage;
import com.code.page.ibnmsConfig.agentList.domain.AgentFormDomain;
import com.code.page.ibnmsConfig.agentList.domain.AgentSearchDomain;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/14.
 */
public class AgListTest2 extends TestCase {
    public AgentListPage agList;
    String rowValue;
    String rowName;
    @Parameters({"node"})
    public AgListTest2(String node)
    {
        super(node);
        agList=new AgentListPage(eventDriver);
        if(DBTools.url.contains("172.21.2.96:3306/bnms_cs")){
            rowName="Agent名称";
            rowValue="agent96";
        }
        if(DBTools.url.contains("172.21.1.5:1523:bnms")){
            rowName="安装路径";
            rowValue="/test-bnms/app/";
        }
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="agList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("AGENT",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(priority = 0,description = "查询Agent")
    public void searchAG()
    {
        String sql="\n" +
                "select * from tb_cfg_deploy_agent a LEFT JOIN (\n" +
                "select t.unit_id,b.device_name,b.ip_addr from tb_cfg_deploy_env t LEFT JOIN tb_asset_host b\n" +
                "on t.unit_id=b.unit_id\n" +
                ") s on a.env_id=s.unit_id";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        AgentSearchDomain agentSearchDomain=new AgentSearchDomain();
        agentSearchDomain.setDevice_name(list.get(0).get("device_name"));
        agentSearchDomain.setIp(list.get(0).get("ip_addr"));
        System.out.println(agentSearchDomain.toString());
        GridPage gridTable=agList.search(agentSearchDomain);
        sql="select * from tb_cfg_deploy_agent a LEFT JOIN (\n" +
                "select t.unit_id,b.device_name,b.ip_addr from tb_cfg_deploy_env t LEFT JOIN tb_asset_host b\n" +
                "on t.unit_id=b.unit_id\n" +
                ") s on a.env_id=s.unit_id WHERE s.device_name like '%"+agentSearchDomain.getDevice_name()+"%' and s.ip_addr like '%"+agentSearchDomain.getIp()+"%'";
        list=dbTools.queryMapListHandler(sql);
        tools.assertEquals(gridTable.getGridrowNum(),list.size(),agentSearchDomain.toString());
    }

    @Test(priority = 1,description = "增加Agent")
    public void addAgent()
    {
        GridPage gridPage=new GridPage(eventDriver);
        int r=gridPage.getGridrowNum()+1;
        String sql;
        sql="select * from tb_cfg_deploy_env t LEFT JOIN tb_asset_host b on t.unit_id=b.unit_id ";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);

        EnvSearchDomain envSearchDomain=new EnvSearchDomain();
        envSearchDomain.setDeviceName(list.get(0).get("device_name"));
        envSearchDomain.setIpAddr(list.get(0).get("ip_addr"));

        AgentFormDomain agentFormDomain=new AgentFormDomain();
        agentFormDomain.setEnvSearchDomain(envSearchDomain);
        agentFormDomain.setAgentName("seleniumAgent"+tools.random());
        agentFormDomain.setJmxPort(String.valueOf(tools.random()));
        agentFormDomain.setDbPort(String.valueOf(tools.random()));
        agentFormDomain.setInstallPath("/selenium/agent/"+tools.random());
        agentFormDomain.setLang("EN_US"+tools.random());
        GridPage gridTable=agList.operateAG("增加",agentFormDomain);
        tools.sleep(5000);
        tools.assertEquals(gridTable.getGridrowNum(),r,agentFormDomain.toString());
    }


    @Test(priority = 1,description = "部署agent")
    public void deployAgent( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=agList.deployAG("部署",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        System.out.println(MqMap.toString());
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"已部署",MqMap);
    }

    @Test(priority = 1,description = "启动agent",dependsOnMethods = "deployAgent")
    public void startAgent( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=agList.deployAG("启动",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"运行中",map);
    }
    @Test(priority = 1,description = "停止agent",dependsOnMethods = "startAgent")
    public void stopAgent( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=agList.deployAG("停止",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"已停止",map);
    }
    @Test(priority = 1,description = "停止Agent",dependsOnMethods = "stopAgent")
    public void updeployAgent( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=agList.deployAG("卸载",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"未部署",map);
    }
    
}
