package com.code.test.bnms;

import com.code.common.*;
import com.code.bnms.PfList.PFListPage;
import com.code.bnms.PfList.domain.PfFormDomain;
import com.code.bnms.envList.domain.EnvSearchDomain;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jinkai on 2014/7/11.
 */
public class PFListTest extends TestCase {
    PFListPage pfList;
    String rowValue;
    String rowName;
    @Parameters({"node"})
    public PFListTest(String node)
    {
        super(node);
        pfList=new PFListPage(eventDriver);
        rowName=Config.getProperty("pfKey");
        rowValue=Config.getProperty("pfValue");
    }
//    @BeforeMethod
//    @Parameters({"Action_URL"})
//    public void beforeMethod(String actionUrl)
//    {
//        eventDriver.get(Data.baseUrl + actionUrl);
//    }

//    @DataProvider(name="PFlist")
//    public Iterator dataDriver(Method method) throws IOException, BiffException {
//        ExcelDriver excelDriver=new ExcelDriver("PERFORMANCE",method.getName());
//        excelHead=excelDriver.getHead(0);
//        return excelDriver;
//    }
    @Test(priority = 0,description = "增加Performance")
    public void addPerformance()
    {
        GridPage gridPage=new GridPage(eventDriver);
        int r=gridPage.getGridrowNum()+1;
        String sql;
        sql="select * from tb_cfg_deploy_env t LEFT JOIN tb_asset_host b on t.unit_id=b.unit_id ";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        EnvSearchDomain envSearchDomain=new EnvSearchDomain();
        envSearchDomain.setDeviceName(list.get(0).get("device_name"));
        envSearchDomain.setIpAddr(list.get(0).get("ip_addr"));
        PfFormDomain domain=new PfFormDomain();
        domain.setInstallPath("/selenium/pf/"+tools.random());
        domain.setLang("en_US");
        domain.setEnSearchDomain(envSearchDomain);

        GridPage gridTable=pfList.operatePF("增加",domain,null);
        tools.assertEquals(r,gridTable.getGridrowNum(),domain.toString());
    }


    @Test(priority = 1,description = "部署Performance")
    public void deployPerformance( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列名",rowName);
        map.put("列值",rowValue);
        GridPage gridTable=pfList.deployPF("部署",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        System.out.println(MqMap.toString());
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"已部署",MqMap.toString());
    }

    @Test(priority = 1,description = "启动Performance",dependsOnMethods = "deployPerformance")
    public void startPerformance( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列名",rowName);
        map.put("列值",rowValue);
        GridPage gridTable=pfList.deployPF("启动",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"运行中",map.toString());
    }
    @Test(priority = 1,description = "停止Performance",dependsOnMethods = "startPerformance")
    public void stopPerformance( )
    {
        Map<String,String> map=new HashMap<>();

        map.put("列名",rowName);
        map.put("列值",rowValue);
        GridPage gridTable=pfList.deployPF("停止",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"运行状态"),"已停止",map.toString());
    }
    @Test(priority = 1,description = "卸载Performance",dependsOnMethods = "stopPerformance")
    public void updeployPerformance( )
    {
        Map<String,String> map=new HashMap<>();
        map.put("列名",rowName);
        map.put("列值",rowValue);

        GridPage gridTable=pfList.deployPF("卸载",map);
        Map<String, String> MqMap = gridTable.getTrOfAllTd(
                gridTable.getListOftr(tools.getMapValue(map,"列名"),tools.getMapValue(map,"列值")).get(0));
        tools.assertEquals(tools.getMapValue(MqMap,"部署状态"),"未部署",map.toString());
        pfList.deployPF("部署",map);
        pfList.deployPF("启动",map);
    }
}
