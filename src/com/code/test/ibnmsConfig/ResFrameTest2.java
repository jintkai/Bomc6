package com.code.test.ibnmsConfig;

import com.code.common.*;
import com.code.page.ibnmsConfig.reslist.ResListFramePage;
import com.code.page.ibnmsConfig.reslist.dao.ResFormDao;
import com.code.page.ibnmsConfig.reslist.domain.ResHostFormDomain;
import jxl.read.biff.BiffException;
import org.apache.commons.collections.map.CaseInsensitiveMap;
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


        map.put("TREE",map.get("device_id"));


        gridTable=resFrame.searchByTree(map);
        tools.sleep(5000);
        tools.assertEquals(gridTable.getGrid_xb(),"0",map);
    }

    @Test(priority = 0,description = "通过设备id查询资源树")
    public void searchResByTree2()
    {
        String sqlStr="select * from tb_asset_host order by length(unit_id) desc";

        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        map=list.get(0);


        map.put("TREE",map.get("unit_id"));


        gridTable=resFrame.searchByTree(map);
        tools.assertEquals(gridTable.getGrid_xb(),"0",map);
    }

    @Test(priority = 0,description = "通过设备id查询数据库资源")
    public void searchDBResByTree()
    {
        String sqlStr="select * from tb_asset_database order by length(unit_id) desc";

        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        map=list.get(0);


        map.put("TREE",map.get("unit_id"));


        gridTable=resFrame.searchByTree(map);
        tools.assertEquals(gridTable.getGrid_xb(),"0",map);
    }


    @Test(priority = 0,description = "查询资源基础配置-主机")
    public void searchHostRes1()
    {
        String sqlStr="select * from tb_asset_host  ";
        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        map=list.get(0);
        System.out.println(list);

        map.put("资源UNIT_ID_RES",map.get("unit_id"));
        map.put("资源名称_RES",map.get("device_name"));
        map.put("IP地址_RES",map.get("ip_addr"));

        gridTable=resFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),"1",map);
    }

    @Test(priority = 0,description = "查询资源基础配置-数据库")
    public void searchDatabaseRes1()
    {

        String sqlStr="select * from tb_asset_database  ";
        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        map=list.get(0);
        System.out.println(list);

        map.put("资源UNIT_ID_RES",map.get("unit_id"));
        map.put("资源名称_RES",map.get("device_name"));
        map.put("IP地址_RES",map.get("ip_addr"));

        gridTable=resFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),"1",map);
    }


    @Test(priority = 2,description = "增加主机")
    public void addHostRes()
    {
        map=new HashMap<>();
        map.put("操作类型","增加");
        map.put("资源类型","主机");
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String nowTime=df.format(new Date());
        String resName="host"+nowTime;
        map.put("资源名称",resName);
        map.put("资源标识","ID"+nowTime);
        int r1= (int) (Math.random()*1000)%253+1;
        int r2= (int) (Math.random()*1000)%253+1;
        String ip=r1+"."+r1+"."+r2+"."+r2;
        map.put("IP地址",ip);
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
        map.put("业务类型",typeClass);
        map.put("简明用途","简明用途");
        map.put("责任人","selenium");
        map.put("是否有效","是");
        resFrame.operateRes(map);
        map.put("资源UNIT_ID_RES","ID"+nowTime);
        gridTable=resFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),String.valueOf(1),map);
    }

    @Test(priority = 2,description = "增加数据库")
    public void addDatabaseRes()
    {
        map=new HashMap<>();
        map.put("资源类型","数据库");
        map.put("操作类型","增加");
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String nowTime=df.format(new Date());
        String resName="db"+nowTime;
        map.put("资源名称",resName);
        map.put("资源标识","ID"+nowTime);
        map.put("SSID","selenium");
        map.put("端口号","100");
        int r1= (int) (Math.random()*1000)%253+1;
        int r2= (int) (Math.random()*1000)%253+1;
        String ip=r1+"."+r1+"."+r2+"."+r2;
        map.put("IP地址",ip);
        //获取厂家类型
        String sql1="select * from tb_cde_kbp where kbp_class like '10-11-__' and enable=1";
        List<Map<String , String >> list=dbTools.queryMapListHandler(sql1);
        String resClass=null;
        resClass=list.get(r1%list.size()).get("kbp_caption");
        map.put("厂家类型",resClass);
        String typeClass=null;
        sql1="select * from tb_cde_kbp where kbp_class like '11-__' and enable=1";
        list=dbTools.queryMapListHandler(sql1);
        typeClass=list.get(r1%list.size()).get("kbp_caption");
        map.put("业务类型",typeClass);
        map.put("简明用途","简明用途");
        map.put("责任人","selenium");
        map.put("是否有效","是");
        map.put("用户名","username");
        map.put("密码","password");
        map.put("URL","jdbc:oracle:thin:@//127.0.0.1:1521/oracle");
        resFrame.operateRes(map);
        map.put("资源UNIT_ID_RES","ID"+nowTime);
        gridTable=resFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),String.valueOf(1),map);
    }

    @Test(priority = 2,description = "修改数据库")
    public void editRes()
    {
        map=new HashMap<>();
        map.put("资源类型","数据库");
        map.put("操作类型","修改");
        String sqlStr="select * from tb_asset_database order by length(unit_id) desc";
        String unit_id=((Map<String,String>)(dbTools.queryMapListHandler(sqlStr)).get(0)).get("unit_id");

        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String nowTime=df.format(new Date());
        String resName="db"+nowTime;
        map.put("资源名称",resName);
        map.put("资源UNIT_ID_RES",unit_id);
        map.put("URL","jdbc:oracle:thin:@//127.0.0.1:1521/oracle_Edit");
        resFrame.operateRes(map);
        map.put("资源UNIT_ID_RES",unit_id);
        map.put("资源名称_RES",resName);
        gridTable=resFrame.search(map);
        Reporter.log(map.toString());
        tools.assertEquals(gridTable.getGrid_xb(),String.valueOf(1),map);
    }

    @Test(priority = 3,description = "删除资源")
    public void deleteRes()
    {
        map=new HashMap<>();
        map.put("操作类型","删除");
        String sqlStr="select * from tb_asset_database where ip_addr not like '1.%' order by length(unit_id) desc";
        String unit_id=((Map<String,String>)(dbTools.queryMapListHandler(sqlStr)).get(0)).get("unit_id");
        map.put("资源UNIT_ID_RES",unit_id);

        resFrame.operateRes(map);

        gridTable=resFrame.search(map);
        Reporter.log(map.toString());
        System.out.println(map.toString());
        tools.assertEquals(gridTable.getGrid_xb(),String.valueOf(0),map);
    }


}
