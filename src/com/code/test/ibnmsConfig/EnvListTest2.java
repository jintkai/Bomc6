package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.envList.EnvListPage;
import com.code.page.ibnmsConfig.envList.domain.EnvFormDomain;
import com.code.page.ibnmsConfig.envList.domain.EnvSearchDomain;
import com.code.page.ibnmsConfig.reslist.domain.ResSearchDomain;
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
 * Created by jinkai on 2014/7/9.
 */
public class EnvListTest2 extends TestCase{
    EnvListPage envList=new EnvListPage(eventDriver);
    GridPage gridTable=new GridPage(eventDriver);
    @Parameters({"node"})
    public EnvListTest2(String node)
    {
        super(node);
        envList=new EnvListPage(eventDriver);
        gridTable=new GridPage(eventDriver);
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

//    @DataProvider(name="envList")
//    public Iterator dataDriver(Method method) throws IOException, BiffException {
//        ExcelDriver excelDriver=new ExcelDriver("部署环境",method.getName());
//        excelHead=excelDriver.getHead(0);
//        return excelDriver;
//    }

    @Test(priority = 0,description = "查询部署环境")
    public void searchEnv()
    {
        String sql="select * from tb_cfg_deploy_env t LEFT JOIN tb_asset_host on t.unit_id=tb_asset_host.unit_id ";
        List<Map<String,String>> list = dbTools.queryMapListHandler(sql);

        EnvSearchDomain searchDomain=new EnvSearchDomain();
        searchDomain.setIpAddr(list.get(0).get("ip_addr"));
        searchDomain.setDeviceName(list.get(0).get("device_name"));
        envList.search(searchDomain);

        sql="select * from tb_cfg_deploy_env t LEFT JOIN tb_asset_host on t.unit_id=tb_asset_host.unit_id " +
                "where ip_addr ='"+list.get(0).get("ip_addr")+"' and device_name like '%"+list.get(0).get("device_name")+"%'";
        list=dbTools.queryMapListHandler(sql);
        gridTable=new GridPage(eventDriver);
        tools.assertEquals(gridTable.getGridrowNum(),list.size(),searchDomain.toString());
    }

    @Test(priority = 1,description = "增加部署环境")
    public void addEnv()
    {
        String sql="select * from tb_asset_host t where t.unit_id not in(select unit_id from tb_cfg_deploy_env) and ip_addr not like '172.%' order by length(unit_id) desc ";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);

        ResSearchDomain resSearchDomain=new ResSearchDomain();
        resSearchDomain.setDeviceName(list.get(2).get("device_name"));
        resSearchDomain.setUnitID(list.get(2).get("unit_id"));
        EnvFormDomain domain=new EnvFormDomain();
        domain.setResSearchDomain(resSearchDomain);
        domain.setUsername("seleniumName");
        domain.setPassword("123456");
        domain.setJdkHome("/home/selenium/autoTesting/env");
        int r=(int)(Math.random()*10);
        switch (r%2){
            case 0:
                domain.setProtocol("ssh");
                break;
            case 1:
                domain.setProtocol("telnet");
        }
        domain.setProtocolPort("22");
        domain.setFtpPort("21");

        envList.operateRes("增加",null,domain);
        EnvSearchDomain envSearchDomain=new EnvSearchDomain();
        envSearchDomain.setDeviceName(list.get(2).get("device_name"));
        envSearchDomain.setIpAddr(list.get(2).get("ip_addr"));

        gridTable=envList.search(envSearchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),1,domain.toString());
    }

    @Test(priority = 1,description = "修改部署环境")
    public void editEnv()
    {

        String sql="select * from (select * from tb_cfg_deploy_env where unit_id not in(\n" +
                "select env_id from tb_cfg_deploy_mq\n" +
                "UNION\n" +
                "select env_id from tb_cfg_deploy_agent\n" +
                "UNION\n" +
                "select env_id from tb_cfg_deploy_workstation\n" +
                "UNION\n" +
                "select env_id from tb_cfg_deploy_performance\n" +
                "UNION\n" +
                "select env_id from tb_cfg_deploy_pmalarm)) a left JOIN tb_asset_host b ON  a.unit_id=b.unit_id";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        EnvSearchDomain searchDomain=new EnvSearchDomain();
        searchDomain.setDeviceName(list.get(1).get("device_name"));
        searchDomain.setIpAddr(list.get(1).get("ip_addr"));

        EnvFormDomain domain=new EnvFormDomain();
        domain.setUsername("seleniumName"+tools.random());
        domain.setPassword("123456");
        domain.setJdkHome("/home/selenium/autoTesting/edit");
        int r=(int)(Math.random()*10);
        switch (r%2){
            case 0:
                domain.setProtocol("ssh");
                break;
            case 1:
                domain.setProtocol("telnet");
        }
        domain.setProtocolPort(String.valueOf(tools.random()));
        domain.setFtpPort(String.valueOf(tools.random()));


        envList.operateRes("修改",searchDomain,domain);
        gridTable=envList.search(searchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),1,searchDomain.toString()+domain.toString());
    }

    @Test(priority = 1,description = "删除部署环境")
    public void deleteEnv()
    {
        map=new HashMap<>();
        String sql="select * from (select * from tb_cfg_deploy_env where unit_id not in(\n" +
                "select env_id from tb_cfg_deploy_mq\n" +
                "UNION\n" +
                "select env_id from tb_cfg_deploy_agent\n" +
                "UNION\n" +
                "select env_id from tb_cfg_deploy_workstation\n" +
                "UNION\n" +
                "select env_id from tb_cfg_deploy_performance\n" +
                "UNION\n" +
                "select env_id from tb_cfg_deploy_pmalarm)) a left JOIN tb_asset_host b ON  a.unit_id=b.unit_id order by length(b.unit_id) desc";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        EnvSearchDomain searchDomain=new EnvSearchDomain();
        searchDomain.setDeviceName(list.get(1).get("device_name"));
        searchDomain.setIpAddr(list.get(1).get("ip_addr"));

        envList.operateRes("删除",searchDomain,null);
        tools.sleep(10000);
        gridTable=envList.search(searchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),0,searchDomain.toString());
    }

}
