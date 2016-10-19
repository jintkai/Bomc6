package com.code.test.ibnmsConfig;

import com.code.common.*;
import com.code.page.ibnmsConfig.reslist.ResListFramePage;
import com.code.page.ibnmsConfig.reslist.dao.ResFormDao;
import com.code.page.ibnmsConfig.reslist.domain.ResFormDomain;
import com.code.page.ibnmsConfig.reslist.domain.ResHostFormDomain;
import com.code.page.ibnmsConfig.reslist.domain.ResSearchDomain;
import jxl.read.biff.BiffException;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jinkai on 07/07/2014.
 */
public class ResFrameTest2 extends TestCase {
    ResListFramePage resFrame;//=new ResListFramePage(eventDriver);
    GridPage gridTable;//=new GridPage(eventDriver);
    //ResFormDao dao;
    private static Logger logger = Logger.getLogger(ResFrameTest2.class);
    @Parameters({"node"})
    public ResFrameTest2(String node)
    {
        super(node);
        resFrame=new ResListFramePage(eventDriver);
        gridTable=new GridPage(eventDriver);
    }

    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        this.eventDriver.get(Data.baseUrl+actionUrl);
    }


    @Test(priority = 0,description = "通过设备名称查询资源树")
    public void searchResByTree()
    {
        String sqlStr="select * from tb_asset_host order by length(device_id) desc";

        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        map=list.get(0);

        gridTable=resFrame.searchByTree(map.get("device_id"));
        tools.sleep(5000);
        tools.assertEquals(gridTable.getGridrowNum(),0,map);
    }

    @Test(priority = 0,description = "通过设备id查询资源树")
    public void searchResByTree2()
    {
        String sqlStr="select * from tb_asset_host order by length(unit_id) desc";

        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        map=list.get(0);

        gridTable=resFrame.searchByTree(map.get("unit_id"));
        tools.assertEquals(gridTable.getGridrowNum(),0,map);
    }

    @Test(priority = 0,description = "树查询,通过设备id查询数据库资源")
    public void searchDBResByTree()
    {
        String sqlStr="select * from tb_asset_database order by length(unit_id) desc";

        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        map=list.get(0);
        gridTable=resFrame.searchByTree(map.get("unit_id"));
        tools.assertEquals(gridTable.getGridrowNum(),0,map);
    }


    @Test(priority = 0,description = "查询资源基础配置-主机")
    public void searchHostRes1()
    {
        String sqlStr="select * from tb_asset_host  ";
        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        map=list.get(0);

        ResSearchDomain domain=new ResSearchDomain();
        domain.setUnitID(map.get("unit_id"));
        domain.setDeviceName(map.get("device_name"));
        domain.setIp(map.get("ip_addr"));

        gridTable=resFrame.search(domain);
        tools.assertEquals(gridTable.getGridrowNum(),1,domain.toString());
    }

    @Test(priority = 0,description = "查询资源基础配置-数据库")
    public void searchDatabaseRes1()
    {

        String sqlStr="select * from tb_asset_database  ";
        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        map=list.get(0);

        ResSearchDomain domain=new ResSearchDomain();
        domain.setUnitID(map.get("unit_id"));
        domain.setDeviceName(map.get("device_name"));
        domain.setIp(map.get("ip_addr"));

        gridTable=resFrame.search(domain);
        tools.assertEquals(gridTable.getGridrowNum(),1,domain.toString());
    }


    @Test(priority = 2,description = "增加主机")
    public void addHostRes()
    {
        map=new HashMap<>();

        String nowTime=tools.formatNow();

        String resName="host"+nowTime;
        int r1= (int) (Math.random()*1000)%253+1;
        int r2= (int) (Math.random()*1000)%253+1;
        String ip=r1+"."+r1+"."+r2+"."+r2;

        //获取厂家类型
        String sql1="select * from tb_cde_kbp where kbp_class like '10-10-__' and enable=1";
        List<Map<String , String >> list=dbTools.queryMapListHandler(sql1);
        String resClass=null;
        resClass=list.get(r1%list.size()).get("kbp_caption");
        map.put("厂家类型",resClass);
        String typeClass=null;
        sql1="select * from tb_cde_kbp where kbp_class like '11-__' and enable=1 ";
        list=dbTools.queryMapListHandler(sql1);
        typeClass=list.get(r1%list.size()).get("kbp_caption");

        ResFormDomain domain=new ResFormDomain();
        domain.setDevType("主机");
        domain.setDevice_name(resName);
        domain.setDevice_id("ID"+nowTime);
        domain.setBz_type(typeClass);
        domain.setIp_addr(ip);
        domain.setLinkman("selenium");
        domain.setEnable("是");
        domain.setManufacturer(resClass);
        domain.setUsage("简明用途");

        resFrame.operateRes("增加",null,domain);

        ResSearchDomain searchDomain=new ResSearchDomain();
        searchDomain.setUnitID("ID"+nowTime);

        gridTable=resFrame.search(searchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),1,domain.toString());
    }

    @Test(priority = 2,description = "增加数据库")
    public void addDatabaseRes()
    {
        
        String nowTime=tools.formatNow();
        String resName="db"+nowTime;
        int r1= (int) (Math.random()*1000)%253+1;
        int r2= (int) (Math.random()*1000)%253+1;
        String ip=r1+"."+r1+"."+r2+"."+r2;
        //获取厂家类型
        String sql1="select * from tb_cde_kbp where kbp_class like '10-11-__' and enable=1";
        List<Map<String , String >> list=dbTools.queryMapListHandler(sql1);
        String resClass=null;
        resClass=list.get(r1%list.size()).get("kbp_caption");
        String typeClass=null;
        sql1="select * from tb_cde_kbp where kbp_class like '11-__' and enable=1";
        list=dbTools.queryMapListHandler(sql1);
        typeClass=list.get(r1%list.size()).get("kbp_caption");

        ResFormDomain domain=new ResFormDomain();
        domain.setDevType("数据库");
        domain.setDevice_name(resName);
        domain.setDevice_id("ID"+nowTime);
        domain.setBz_type(typeClass);
        domain.setManufacturer(resClass);
        //domain.setDevType(resClass);
        domain.setIp_addr(ip);
        domain.setLinkman("selenium");
        domain.setEnable("是");
        domain.setSsid("DBSelenium");
        domain.setDbPort("1521");
        domain.setDbUser("DBUser");
        domain.setDbPasswd("1234567890");
        domain.setDbUrl("jdbc:oracle:thin:@//127.0.0.1:1521/oracle");
        resFrame.operateRes("增加",null,domain);
        ResSearchDomain searchDomain=new ResSearchDomain();
        searchDomain.setUnitID("ID"+nowTime);
        gridTable=resFrame.search(searchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),1,domain.toString());
    }

    @Test(priority = 2,description = "修改数据库")
    public void editRes()
    {
        String sqlStr="select * from tb_asset_database order by length(unit_id) desc";
        String unit_id=((Map<String,String>)(dbTools.queryMapListHandler(sqlStr)).get(0)).get("unit_id");

        String nowTime=tools.formatNow();
        String resName="db"+nowTime;
        ResSearchDomain searchDomain=new ResSearchDomain();
        searchDomain.setUnitID(unit_id);
        ResFormDomain domain=new ResFormDomain();
        domain.setDevice_name(resName);
        domain.setDevType("数据库");
        domain.setDbUrl("jdbc:oracle:thin:@//127.0.0.1:1521/oracle_Edit");
        resFrame.operateRes("修改",searchDomain,domain);
        searchDomain.setDeviceName(resName);
        gridTable=resFrame.search(searchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),1,searchDomain.toString()+";"+domain.toString());
    }

    @Test(priority = 3,description = "删除资源")
    public void deleteRes()
    {
        map=new HashMap<>();
        map.put("操作类型","删除");
        String sqlStr="select * from tb_asset_database where ip_addr not like '172.%' order by length(unit_id) desc";
        String unit_id=((Map<String,String>)(dbTools.queryMapListHandler(sqlStr)).get(0)).get("unit_id");
        ResSearchDomain searchDomain=new ResSearchDomain();
        searchDomain.setUnitID(unit_id);

        resFrame.operateRes("删除",searchDomain,null);

        gridTable=resFrame.search(searchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),0,searchDomain.toString());
    }


}
