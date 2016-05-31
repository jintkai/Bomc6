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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
        KpiFormDao dao=new KpiFormDao();
        KpiFormDomain domain=dao.selectKpiByRowNum(10);
        domain.setKPI_NAME("");
        List<KpiFormDomain> list=dao.selectKpiList(domain);

        map=new HashMap<String,String>();
        map.put("指标编号_KPI",domain.getKPI_ID());
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),
                map);

    }
    @Test(priority = 0,description = "通过KPI名称查询")
    public void searchKpi2(){
        tools.refresh();
        KpiFormDao dao=new KpiFormDao();
        KpiFormDomain domain=dao.selectKpiByRowNum(10);
        domain.setKPI_ID("");
        String kpiName=domain.getKPI_NAME();
        domain=new KpiFormDomain();
        domain.setKPI_NAME(kpiName);
        List<KpiFormDomain> list=dao.selectKpiList(domain);

        map=new HashMap<String,String>();
        map.put("指标名称_KPI",domain.getKPI_NAME());
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),
                map);

    }
    @Test(priority = 0,description = "通过KPI编号,名称查询")
    public void searchKpi3(){
        tools.refresh();
        KpiFormDao dao=new KpiFormDao();
        KpiFormDomain domain=dao.selectKpiByRowNum(10);
        List<KpiFormDomain> list=dao.selectKpiList(domain);

        map=new HashMap<String,String>();
        map.put("指标名称_KPI",domain.getKPI_NAME());
        map.put("指标编号_KPI",domain.getKPI_ID());
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),
                map);

    }

    @Test(priority = 0,description = "通过KBP树查询KPI")
    public void searchKpi4(){
        //asyncTree_21_span表示平台,10-10
        tools.refresh();
        gridTable=kpiFrame.selectTree("主机");
        KpiFormDao dao=new KpiFormDao();
        KpiFormDomain domain=new KpiFormDomain();
        domain.setKBP_CLASS("10-10");
        List<KpiFormDomain> list=dao.selectKpiList(domain);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),"");
    }

    @Test(priority = 0,description = "通过KBP树,KPI编号组合查询")
    public void searchKpi5(){
        tools.refresh();
        gridTable=kpiFrame.selectTree("主机");
        KpiFormDomain domain=new KpiFormDomain();
        domain.setKBP_CLASS("10-11");
        KpiFormDao dao=new KpiFormDao();
        List<KpiFormDomain> list = dao.selectKpiList(domain);
        domain.setKBP_CLASS("");
        domain.setKPI_ID(list.get(0).getKPI_ID());
        map=new HashMap<String,String>();
        map.put("指标编号_KPI",domain.getKPI_ID());
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),"0",
                map);
    }
    @Test(priority = 0,description = "通过KBP树,KPI编号组合查询")
    public void searchKpi6(){
        tools.refresh();
        gridTable=kpiFrame.selectTree("主机");
        KpiFormDomain domain=new KpiFormDomain();
        domain.setKBP_CLASS("10-10");
        KpiFormDao dao=new KpiFormDao();
        List<KpiFormDomain> list = dao.selectKpiList(domain);
        domain.setKBP_CLASS("");
        domain.setKPI_ID(list.get(0).getKPI_ID());
        map=new HashMap<String,String>();
        map.put("指标编号_KPI",domain.getKPI_ID());
        gridTable=kpiFrame.search(map);
        domain.setKPI_ID(domain.getKPI_ID());
        domain.setKBP_CLASS("10-10");
        list = dao.selectKpiList(domain);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),
                map);

    }

    @Test(priority = 3,description = "删除KPI")
    public void deleteKpi(){
        tools.refresh();
        map=new HashMap<String,String>();
        map.put("操作类型","删除");
        KpiFormDomain domain=new KpiFormDomain();
        KpiFormDao dao=new KpiFormDao();
        domain=dao.selectOneKpiByID();
        map.put("指标编号_KPI",domain.getKPI_ID());
        kpiFrame.operateKpi(map);
        gridTable=kpiFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),"0",
                map);
    }

    @Test(priority = 1,description = "增加与KBP关联的KPI")
    public void addKpi(){
        tools.refresh();
        map=new HashMap<String,String>();

        KpiFormDomain domain=new KpiFormDomain();
        KpiFormDao dao=new KpiFormDao();
        domain=dao.selectOneKpiByID();

        map.put("操作类型","增加");
        map.put("管理类型","告警管理");
        map.put("指标类型","数量");
        map.put("编号",domain.getKPI_ID()+"6");
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
        map.put("指标编号_KPI",domain.getKPI_ID()+"6");
        gridTable=kpiFrame.search(map);

        tools.assertEquals(gridTable.getGrid_xb(),"1",
                map);
    }

    @Test(priority = 2,description = "修改KPI信息")
    public void editKpi(){
        tools.refresh();
        map=new HashMap<String,String>();

        KpiFormDomain domain=new KpiFormDomain();
        KpiFormDao dao=new KpiFormDao();
        domain=dao.selectOneKpiByID();

        map.put("操作类型","修改");

        map.put("指标类型","使用率");

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime=df.format(new Date());
        String kpi_name=domain.getKPI_NAME()+"selenium";
        map.put("名称",kpi_name);
        map.put("指标描述",kpi_name+nowTime);
        map.put("单位","率");
        map.put("数据类型","字符串");
        map.put("指标编号_KPI",domain.getKPI_ID());

        gridTable=kpiFrame.search(map);

        kpiFrame.operateKpi(map);
        map.clear();
        map.put("指标编号_KPI",domain.getKPI_ID());
        map.put("指标名称_KPI",kpi_name);

        gridTable=kpiFrame.search(map);

        tools.assertEquals(gridTable.getGrid_xb(),"1",
                map);
    }

}

