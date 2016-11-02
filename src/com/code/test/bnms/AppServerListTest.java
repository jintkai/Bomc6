package com.code.test.bnms;

import com.code.common.*;
import com.code.bnms.appServer.AppServerListPage;
import com.code.bnms.appServer.domain.AppFormDomain;
import com.code.bnms.envList.domain.EnvSearchDomain;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jin on 2014/8/10.
 */
public class AppServerListTest extends TestCase {
    AppServerListPage appServerList;
    String rowValue;
    String rowName;
    @Parameters({"node"})
    public AppServerListTest(String node)
    {
        super(node);
        appServerList=new AppServerListPage(eventDriver);
        rowName=Config.getProperty("bmcServerKey");
        rowValue=Config.getProperty("bmcServerValue");
    }


    @Test(priority = 0,description = "增加Appserver-BMCServer")
    public void addAppServer()
    {
        String sql="select * from tb_cfg_deploy_env t LEFT JOIN tb_asset_host b on t.unit_id=b.unit_id where ip_addr not like '172.%'";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        EnvSearchDomain searchDomain=new EnvSearchDomain();
        searchDomain.setDeviceName(list.get(0).get("device_name"));
        searchDomain.setIpAddr(list.get(0).get("ip_Addr"));

        AppFormDomain formDomain=new AppFormDomain();
        formDomain.setEnvSearchDomain(searchDomain);
        String appName="seleniumBMCSERVER"+tools.formatNow();
        formDomain.setAppName(appName);
        formDomain.setInstatllPath("/selenium/bmcserver/"+tools.random());
        formDomain.setProcessKey("processKeyBmcServer"+tools.random());
        GridPage gridTable=appServerList.operateApp("增加","BmcServer",formDomain,null);
        tools.assertEquals(gridTable.getListOftr("应用名称",appName).size(),
                1,formDomain.toString());
    }

    @Test(priority = 1,description = "修改Appserver-BMCServer")
    public void editAppServer()
    {
        String sql="select * from tb_cfg_appserver_deploy where applyvalue=\"BmcServer\" and applyhostip not like '172.%' order by length(applyname) desc";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        String appName=list.get(0).get("applyname");

        Map<String,String> map=new HashMap<>();
        map.put("列值",appName);
        map.put("列名","应用名称");
        AppFormDomain formDomain=new AppFormDomain();
        String installPath="/selenium/"+tools.formatNow();
        String processKey="processKey"+tools.formatNow();
        formDomain.setInstatllPath(installPath);
        formDomain.setProcessKey(processKey);
        GridPage gridTable=appServerList.operateApp("修改",null,formDomain,map);
        tools.assertEquals(gridTable.getListOftr("安装路径",installPath).size(),
                1,"修改记录:"+map+";修改后记录:"+formDomain.toString());
        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr("应用名称",appName).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"进程关键字"),processKey,"修改BmcServer:"+map+";"+MqMap);
        tools.assertEquals(tools.getMapValue(MqMap,"安装路径"),installPath,"修改BmcServer:"+map+";"+MqMap);
    }

    @Test(priority = 2,description = "删除Appserver-BMCServer")
    public void deleteAppServer()
    {
        String sql="select * from tb_cfg_appserver_deploy where applyvalue=\"BmcServer\" and applyhostip not like '172.%' order by length(applyname) desc";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        String appName=list.get(0).get("applyname");
        sql="select * from tb_cfg_appserver_deploy where applyvalue=\"BmcServer\"";
        int rowNum=dbTools.queryMapListHandler(sql).size();

        Map<String,String> map=new HashMap<>();
        map.put("列值",appName);
        map.put("列名","应用名称");

        GridPage gridTable=appServerList.operateApp("删除",null,null,map);
        tools.sleep(10000);
        int rowNumAfter=dbTools.queryMapListHandler(sql).size();
        System.out.println("rowNum:"+rowNum+";rowNumAfter:"+rowNumAfter);
        tools.assertEquals(rowNumAfter,
                rowNum-1,map.toString());
    }


    @Test(priority = 2,description = "部署appserver-BmcServer")
    public void deployAppServer()
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=appServerList.deployApp("部署",map);

        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr(rowName,rowValue).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"已部署","部署BmcServer:"+map+";"+MqMap);
    }

    @Test(priority = 2,description = "启动appserver-BmcServer",dependsOnMethods = "deployAppServer")
    public void startAppServer()
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=appServerList.deployApp("启动",map);

        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr(rowName,rowValue).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"运行中","启动BmcServer:"+map+";"+MqMap);
    }
    @Test(priority = 2,description = "停止appserver-BmcServer",dependsOnMethods = "startAppServer")
    public void stopAppServer()
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=appServerList.deployApp("停止",map);

        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr(rowName,rowValue).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"已停止","停止BmcServer:"+map+";"+MqMap);
    }
    @Test(priority = 2,description = "卸载appserver-BmcServer",dependsOnMethods = "stopAppServer")
    public void undeployAppServer()
    {
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=appServerList.deployApp("卸载",map);

        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr(rowName,rowValue).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"未部署","卸载BmcServer:"+map+";"+MqMap);
        appServerList.deployApp("部署",map);
        appServerList.deployApp("启动",map);
    }
}
