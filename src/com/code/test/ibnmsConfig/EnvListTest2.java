package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.envList.EnvListPage;
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

    @DataProvider(name="envList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("部署环境",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }

    @Test(priority = 0,description = "查询部署环境")
    public void searchEnv()
    {
        String sql="select * from tb_cfg_deploy_env t LEFT JOIN tb_asset_host on t.unit_id=tb_asset_host.unit_id ";
        List<Map<String,String>> list = dbTools.queryMapListHandler(sql);
        map=new HashMap<>();
        map.put("IP地址_ENV",list.get(0).get("ip_addr"));
        map.put("主机名称_ENV",list.get(0).get("device_name"));
        envList.search(map);
        sql="select * from tb_cfg_deploy_env t LEFT JOIN tb_asset_host on t.unit_id=tb_asset_host.unit_id " +
                "where ip_addr ='"+list.get(0).get("ip_addr")+"' and device_name like '%"+list.get(0).get("device_name")+"%'";
        list=dbTools.queryMapListHandler(sql);
        gridTable=new GridPage(eventDriver);
        tools.assertEquals(gridTable.getGrid_xb(),String.valueOf(list.size()),map);
    }
    @Test(priority = 1,description = "增加部署环境")
    public void addEnv()
    {
        map=new HashMap<>();
        String sql="select * from tb_asset_host t where t.unit_id not in(select unit_id from tb_cfg_deploy_env) ";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        map.put("操作类型","增加");
        map.put("资源名称",list.get(2).get("device_name"));
        map.put("资源UNIT_ID_RES",list.get(2).get("unit_id"));
        map.put("资源名称_RES",list.get(2).get("device_name"));
        map.put("用户名","seleniumName");
        map.put("密码","seleniumPword");
        map.put("JDK路径","/selenium/java");
        map.put("密码","seleniumPword");
        int r=(int)(Math.random()*10);
        switch (r%2){
            case 0:
                map.put("连接协议","ssh");
                break;
            case 1:
                map.put("连接协议","telnet");
        }
        map.put("协议端口","22");
        map.put("FTP端口","21");
        envList.operateRes(map);
        map.put("主机名称_ENV",list.get(2).get("device_name"));
        map.put("IP地址_ENV",list.get(2).get("ip_addr"));
        gridTable=envList.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),String.valueOf(1),map);
    }

    @Test(priority = 1,description = "修改部署环境")
    public void editEnv()
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
                "select env_id from tb_cfg_deploy_pmalarm)) a left JOIN tb_asset_host b ON  a.unit_id=b.unit_id";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        map.put("主机名称_ENV",list.get(1).get("device_name"));
        map.put("IP地址_ENV",list.get(1).get("ip_addr"));
        int r=(int)(Math.random()*10);
        map.put("操作类型","修改");
        map.put("用户名","seleniumName"+r);
        map.put("密码","seleniumPword"+r);
        map.put("JDK路径","/selenium/java"+r);
        map.put("密码","seleniumPword"+r);

        switch (r%2){
            case 0:
                map.put("连接协议","ssh");
                break;
            case 1:
                map.put("连接协议","telnet");
                break;
        }
        map.put("协议端口","22"+r);
        map.put("FTP端口","21"+r);
        envList.operateRes(map);
        gridTable=envList.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),String.valueOf(1),map);
    }

    @Test(priority = 1,description = "修改部署环境")
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
                "select env_id from tb_cfg_deploy_pmalarm)) a left JOIN tb_asset_host b ON  a.unit_id=b.unit_id";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        map.put("主机名称_ENV",list.get(1).get("device_name"));
        map.put("IP地址_ENV",list.get(1).get("ip_addr"));
        map.put("操作类型","删除");
        envList.operateRes(map);
        tools.sleep(10000);
        gridTable=envList.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),String.valueOf(0),map);
    }

}
