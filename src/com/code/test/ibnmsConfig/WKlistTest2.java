package com.code.test.ibnmsConfig;

import com.code.common.*;
import com.code.page.ibnmsConfig.agentList.domain.AgentFormDomain;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;
import com.code.page.ibnmsConfig.workstation.WKlistPage;
import com.code.page.ibnmsConfig.workstation.domain.WKFormDomain;
import com.code.page.ibnmsConfig.workstation.page.WKFormPage;
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
 * Created by jinkai on 2014/7/11.
 */
public class WKlistTest2 extends TestCase {
    WKlistPage wkList;
    String rowValue;
    String rowName;
    @Parameters({"node"})
    public WKlistTest2(String node)
    {
        super(node);
        wkList=new WKlistPage(eventDriver);
        if(DBTools.url.contains("172.21.2.96:3306/bnms_cs")){
            rowName="ID";
            rowValue="B8D27C1...";
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

    @DataProvider(name="WKlist")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("WORKSTATION",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }

    @Test(priority = 0,description = "增加WORKSTATION")
    public void addWorkstation()
    {
        GridPage gridPage=new GridPage(eventDriver);
        int r=gridPage.getGridrowNum()+1;
        String sql;
        sql="select * from tb_cfg_deploy_env t LEFT JOIN tb_asset_host b on t.unit_id=b.unit_id ";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        EnvSearchDomain envSearchDomain=new EnvSearchDomain();
        envSearchDomain.setDeviceName(list.get(0).get("device_name"));
        envSearchDomain.setIpAddr(list.get(0).get("ip_addr"));
        WKFormDomain wkFormDomain=new WKFormDomain();
        wkFormDomain.setInstallPath("/selenium/worksation/"+tools.random());
        wkFormDomain.setEnvSearchDomain(envSearchDomain);
        GridPage gridTable=wkList.operateWK("增加",wkFormDomain);
        tools.sleep(5000);
        tools.assertEquals(gridTable.getGridrowNum(),r,wkFormDomain.toString());
    }

    //@Test(dataProvider = "WKlist",priority = 0,description = "增加、修改、删除WORKSTATION")
    public void operateWK(String[] str)
    {
        map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=wkList.operateWK(map);
        tools.assertEquals(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")).size(),
                Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    //@Test(dataProvider="WKlist",priority = 1,description = "部署、启动、停止、卸载WORKSTATION")
    public void deployWK(String[] str)
    {
        Map<String,String> map=tools.changeStringToMap(excelHead,str);
        GridPage gridTable=wkList.deployWK(map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(gridTable.getListOftr(tools.getMapValue(map,"列表选择器"),tools.getMapValue(map,"列表匹配数据")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,tools.getMapValue(map,"状态字段")),tools.getMapValue(map,"期望值"),map);
    }

    @Test(priority = 1,description = "部署Workstation")
    public void deployWorkstation( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列名",rowName);
        map.put("列值",rowValue);
        GridPage gridTable=wkList.deployWK("部署",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        System.out.println(MqMap.toString());
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"已部署",MqMap);
    }

    @Test(priority = 1,description = "启动Workstation",dependsOnMethods = "deployWorkstation")
    public void startWorkstation( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列名",rowName);
        map.put("列值",rowValue);
        GridPage gridTable=wkList.deployWK("启动",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"运行中",map);
    }
    @Test(priority = 1,description = "停止Workstation",dependsOnMethods = "startWorkstation")
    public void stopWorkstation( )
    {
        Map<String,String> map=new HashMap<>();

        map.put("列名",rowName);
        map.put("列值",rowValue);
        GridPage gridTable=wkList.deployWK("停止",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"已停止",map);
    }
    @Test(priority = 1,description = "停止Workstation",dependsOnMethods = "stopWorkstation")
    public void updeployWorkstation( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列名",rowName);
        map.put("列值",rowValue);

        GridPage gridTable=wkList.deployWK("卸载",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"未部署",map);
    }
}
