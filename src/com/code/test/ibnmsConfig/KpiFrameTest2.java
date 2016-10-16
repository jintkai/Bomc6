package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.kpilist.KpiListFramePage;
import com.code.page.ibnmsConfig.kpilist.dao.KpiFormDao;
import com.code.page.ibnmsConfig.kpilist.domain.KpiFormDomain;
import jxl.read.biff.BiffException;
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
 * Created by jinkai on 06/07/2014.
 */
public class KpiFrameTest2 extends TestCase {
    KpiListFramePage kpiFrame;
    GridPage gridTable;
    @Parameters({"node"})
    public KpiFrameTest2(String node)
    {
        super(node);
        kpiFrame=new KpiListFramePage(eventDriver);
        gridTable=new GridPage(eventDriver);
        Reporter.log("selenium Grid:" + node);
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="kpiList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("KPI管理",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
    @Test(priority = 0,description = "通过KPI编号查询")
    public void searchKpi1(){
        tools.refresh();
        String sql="select * from tb_cde_kpi";
        String kpi_id=((Map<String,String>)(dbTools.queryMapListHandler(sql).get(10))).get("kpi_id");
        sql="select * from tb_cde_kpi where kpi_id like \'%"+kpi_id+"%\'";
        List list=dbTools.queryMapListHandler(sql);
        map=new HashMap<>();
        map.put("指标编号_KPI",kpi_id);
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),
                map);

    }
    @Test(priority = 0,description = "通过KPI名称查询")
    public void searchKpi2(){
        tools.refresh();
        String sql="select * from tb_cde_kpi";
        String kpiName=((Map<String,String>)(dbTools.queryMapListHandler(sql).get(10))).get("kpi_Name");
        sql="select * from tb_cde_kpi where kpi_Name like \'%"+kpiName+"%\'";
        List list=dbTools.queryMapListHandler(sql);


        map=new HashMap<>();
        map.put("指标名称_KPI",kpiName);
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),
                map);

    }
    @Test(priority = 0,description = "通过KPI编号,名称查询")
    public void searchKpi3(){
        tools.refresh();
        String sql="select * from tb_cde_kpi";
        String kpi_Name=((Map<String,String>)(dbTools.queryMapListHandler(sql).get(10))).get("kpi_Name");
        String kpi_id=((Map<String,String>)(dbTools.queryMapListHandler(sql).get(10))).get("kpi_id");
        sql="select * from tb_cde_kpi where kpi_Name like \'%"+kpi_Name+"%\' and kpi_id like '%"+kpi_id+"%\'";
        List list=dbTools.queryMapListHandler(sql);
        map=new HashMap<>();
        map.put("指标名称_KPI",kpi_Name);
        map.put("指标编号_KPI",kpi_id);
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),
                map);

    }

    @Test(priority = 0,description = "通过KBP树查询KPI")
    public void searchKpi4(){
        //asyncTree_21_span表示平台,10-10
        tools.refresh();
        gridTable=kpiFrame.selectTree("主机");
       // String sql="select * from tb_cde_kpi where kbp_class like \'10-10%\'";
        String sql="SELECT *\n" +
                "FROM (SELECT *\n" +
                "      FROM (SELECT\n" +
                "              t1.*,\n" +
                "              t2.TREND_FLAG,\n" +
                "              t2.BASELINE_FLAG\n" +
                "            FROM TB_CDE_KPI t1 LEFT JOIN TB_CFG_PREALARM_KPI t2 ON t1.KPI_ID = t2.KPI_ID\n" +
                "            WHERE 1 = 1 AND (t1.kbp_class LIKE concat('10-10', '-%') OR t1.kbp_class = '10-10')) order_\n" +
                "      ORDER BY KPI_ID ASC) page_ ";
        List list=dbTools.queryMapListHandler(sql);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),"通过KBP树查询KPI:主机类");
    }

    @Test(priority = 0,description = "通过KBP树,KPI编号组合查询")
    public void searchKpi5(){
        tools.refresh();


        String sql="select * from tb_cde_kpi where kbp_class like \'10-10%\'";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);

        String kpi_id=((Map<String,String>) list.get(0)).get("kpi_id");
        sql="select * from tb_cde_kpi where kbp_class like \'10-10%\' and kpi_id like '"+kpi_id+"%'";
        list=dbTools.queryMapListHandler(sql);
        map=new HashMap<>();
        map.put("指标编号_KPI",kpi_id);
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),String.valueOf(list.size()),
                map);
    }

    @Test(priority = 3,description = "删除KPI")
    public void deleteKpi(){
        tools.refresh();
        map=new HashMap<>();
        map.put("操作类型","删除");
        String sql="select * from (select length(t.KPI_ID) as l,t.* from TB_CDE_KPI t  order by l desc ) t2  ";
        String kpi_id=((Map<String,String>)(dbTools.queryMapListHandler(sql).get(0))).get("kpi_id");

        map.put("指标编号_KPI",kpi_id);
        kpiFrame.operateKpi(map);
        tools.sleep(10000);
        gridTable=kpiFrame.search(map);
        System.out.println(map.toString());
        tools.assertEquals(gridTable.getGrid_xb(),"0",
                map);
    }

    @Test(priority = 1,description = "增加与KBP关联的KPI")
    public void addKpi(){
        tools.refresh();
        map=new HashMap<>();
        String sql="select * from (select length(t.KPI_ID) as l,t.* from TB_CDE_KPI t  order by l desc ) t2  ";
        String kpi_id=((Map<String,String>)(dbTools.queryMapListHandler(sql).get(0))).get("kpi_id");

        map.put("操作类型","增加");
        map.put("管理类型","告警管理");
        map.put("指标类型","数量");
        map.put("编号",kpi_id+"6");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime=df.format(new Date());
        String kpi_name="KPI名称"+nowTime;
        map.put("名称",kpi_name);
        map.put("指标描述","KPI描述"+nowTime);
        map.put("单位","个");
        map.put("数据类型","数值");

        gridTable=kpiFrame.selectTree("主机"); //asyncTree_21_span 10-10

        kpiFrame.operateKpi(map);
        map.clear();
        map.put("指标编号_KPI",kpi_id+"6");
        gridTable=kpiFrame.search(map);

        tools.assertEquals(gridTable.getGrid_xb(),"1",
                map);
    }

    @Test(priority = 2,description = "修改KPI信息")
    public void editKpi(){
        tools.refresh();
        map=new HashMap<>();

        String sql="select * from (select length(t.KPI_ID) as l,t.* from TB_CDE_KPI t  order by l desc ) t2 ";
        String kpi_Name=((Map<String,String>)(dbTools.queryMapListHandler(sql).get(0))).get("kpi_Name");
        String kpi_id=((Map<String,String>)(dbTools.queryMapListHandler(sql).get(0))).get("kpi_id");

        map.put("操作类型","修改");

        map.put("指标类型","使用率");

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime=df.format(new Date());
        String kpi_name=kpi_Name+"selenium";
        map.put("名称",kpi_name);
        map.put("指标描述",kpi_name+nowTime);
        map.put("单位","率");
        map.put("数据类型","字符串");
        map.put("指标编号_KPI",kpi_id);

        gridTable=kpiFrame.search(map);

        kpiFrame.operateKpi(map);
        map.clear();
        map.put("指标编号_KPI",kpi_id);
        map.put("指标名称_KPI",kpi_name);

        gridTable=kpiFrame.search(map);

        tools.assertEquals(gridTable.getGrid_xb(),"1",
                map);
    }

}

