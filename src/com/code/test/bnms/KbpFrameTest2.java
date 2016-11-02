package com.code.test.bnms;

import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.bnms.kbplist.KbpListFramePage;
import com.code.bnms.kbplist.domain.KbpFormDomain;
import com.code.bnms.kbplist.domain.KbpSearchDomain;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by jinkai on 01/07/2014.
 */
public class KbpFrameTest2 extends TestCase {
    KbpListFramePage kbpFrame;
    GridPage gridTable;
    @Parameters({"node"})
    public KbpFrameTest2(String node)
    {
        super(node);
        kbpFrame=new KbpListFramePage(eventDriver);
        gridTable=new GridPage(eventDriver);
        Reporter.log("selenium Grid:"+node);
        //kbpFormDao=new KbpFormDao();
    }

//    @BeforeMethod
//    @Parameters({"Action_URL"})
//    public void beforeMethod(String actionUrl)
//    {
//        eventDriver.get(Data.baseUrl + actionUrl);
//    }

//    @DataProvider(name="kbpList")
//    public Iterator dataDriver(Method method) throws IOException, BiffException {
//        ExcelDriver excelDriver=new ExcelDriver("KBP管理",method.getName());
//        excelHead=excelDriver.getHead(0);
//        return excelDriver;
//    }
    @Test(priority = 0,description = "模糊查询多条KBP记录")
    public void searchKBP()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        String kbp_class="11";
        String strSql="select * from tb_cde_kbp t where 1=1 and KBP_CLASS like '"+kbp_class+"%'";
        Object params[] ={kbp_class,kbp_class};
        List<Map> list=dbTools.queryMapListHandler(strSql);
        map.clear();
        map.put("KBP编号_KBP",kbp_class);
        map.put("KBP名称_KBP",null);
        KbpSearchDomain kbpSearchDomain=new KbpSearchDomain();
        //kbpSearchDomain.setKbp_Caption(null);
        kbpSearchDomain.setKbp_class(kbp_class);
        gridTable=kbpFrame.search(kbpSearchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),list.size(),kbpSearchDomain.toString());
    }

    @Test(priority = 0,description = "通过KBP编号精确查询")
    public void searchKBP1()
    {
        tools.refresh();
        //map=new HashMap<String, String>();
        //map.clear();
        String sqlStr="select * from (select length(t.KBP_CLASS) as l,t.* from TB_CDE_KBP t  order by l desc ) t2 ";
        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        Map mapSQL=list.get(0);

        //map.put("KBP编号_KBP", (String) mapSQL.get("kbp_class"));
        //map.put("KBP名称_KBP",null);
        KbpSearchDomain kbpSearchDomain=new KbpSearchDomain();
        //kbpSearchDomain.setKbp_Caption(null);
        kbpSearchDomain.setKbp_class((String)mapSQL.get("kbp_class"));
        gridTable=kbpFrame.search(kbpSearchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),1,kbpSearchDomain.toString());
    }

    @Test(priority = 0,description = "通过KBP名称查询")
    public void searchKBP2()
    {
        tools.refresh();

        String sqlStr="select * from (select length(t.KBP_CLASS) as l,t.* from TB_CDE_KBP t  order by l desc ) t2  where l is not null ";
        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        Map<String,String> mapSQL=list.get(0);
        sqlStr="select * from tb_cde_kbp where kbp_caption like '%"+mapSQL.get("kbp_caption")+"%'";
        list=dbTools.queryMapListHandler(sqlStr);

        KbpSearchDomain kbpSearchDomain=new KbpSearchDomain();
        kbpSearchDomain.setKbp_Caption(mapSQL.get("kbp_caption"));
        gridTable=kbpFrame.search(kbpSearchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),list.size(),kbpSearchDomain.toString());
    }

    @Test(priority = 0,description = "KBP编号,KBP名称组合查找")
    public void searchKBP3()
    {
        tools.refresh();

        String strSQL="select * from tb_cde_kbp";
        List<Map> list=dbTools.queryMapListHandler(strSQL);
        Map<String,String> mapSQL=list.get(20);

        KbpSearchDomain kbpSearchDomain=new KbpSearchDomain();
        kbpSearchDomain.setKbp_Caption(mapSQL.get("kbp_caption"));
        kbpSearchDomain.setKbp_class(mapSQL.get("KBP_CLASS"));

        gridTable=kbpFrame.search(kbpSearchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),1,kbpSearchDomain.toString());
    }

    @Test(priority = 1,description = "通过KBP名称查询KBP树")
    public void searchKBPByTree()
    {
        tools.refresh();

        String sql="select * from (select length(t.KBP_CAPTION) as l,t.* from TB_CDE_KBP t  order by l desc ) t2  where l is not null ";
        List<Map<String,String>> list1=dbTools.queryMapListHandler(sql);
        String kbp_caption=list1.get(0).get("KBP_CAPTION");
        String kbp_class=list1.get(0).get("kbp_class");
        String sql2="select * from tb_cde_kbp where KBP_CAPTION like \'%"+kbp_caption+"%'";
        list1=dbTools.queryMapListHandler(sql2);
        sql="select * from tb_cde_kbp where kbp_class ='"+kbp_class+"' or kbp_class like '"+kbp_class+"-%'";
        list1=dbTools.queryMapListHandler(sql);

        gridTable=kbpFrame.searchByTree(kbp_caption);
        tools.assertEquals(gridTable.getGridrowNum(),list1.size(),kbp_caption);
    }

    @Test(priority = 1,description = "通过KBP编号查询KBP树")
    public void searchKBPByTree2()
    {
        tools.refresh();

        String sql="select KBP_CLASS from (select length(t.KBP_CLASS) as l,t.* from TB_CDE_KBP t  order by l desc ) t2 ";
        String kbp_class=( (Map<String,String>) (dbTools.queryMapListHandler(sql).get(0))).get("kbp_class");


        gridTable=kbpFrame.searchByTree(kbp_class);
        tools.sleep(5000);
        tools.assertEquals(gridTable.getGridrowNum(),1,kbp_class);
    }


    @Test(priority = 2,description="KBP增加")
    public void addKBP()
    {

        String nowStr=tools.formatNow();


        String sql="select * from TB_CDE_KBP t order by length(t.KBP_CLASS) desc";
        List<Map<String , String >> list=dbTools.queryMapListHandler(sql);
        KbpFormDomain domain=new KbpFormDomain();

        domain.setKBP_CAPTION("selenium"+nowStr);
        domain.setKBP_DESC("seleniumDesc"+nowStr);
        domain.setMiddle(String.valueOf(tools.random()));
        switch (tools.random()%3) {
            case 1: domain.setVIEW_STYLE("一维表格");

                break;
            case 2: domain.setVIEW_STYLE("二维表格");
                break;

            default: domain.setVIEW_STYLE("自定义页面");
        }
        switch (tools.random()%2) {
            case 1: domain.setENABLE("是");
                break;

            default: domain.setENABLE("否");
        }


        kbpFrame.operateKbp("增加",list.get(0).get("KBP_CLASS"),null,domain);

        KbpSearchDomain searchDomain=new KbpSearchDomain();
        searchDomain.setKbp_Caption("selenium"+nowStr);
        gridTable=kbpFrame.search(searchDomain);
        tools.assertEquals(gridTable.getGridrowNum(),1,domain.toString()+searchDomain.toString());
    }

    @Test(priority = 3,description="KBP删除")
    public void deleteKBP()
    {

        String sql="select * from TB_CDE_KBP t order by length(t.KBP_CLASS) desc";
        List<Map<String , String >> list=dbTools.queryMapListHandler(sql);
        KbpSearchDomain searchDomain=new KbpSearchDomain();
        searchDomain.setKbp_class(list.get(0).get("KBP_CLASS"));

        kbpFrame.operateKbp("删除",null,searchDomain,null);

        gridTable=kbpFrame.search(searchDomain);

        tools.assertEquals(gridTable.getGridrowNum(),0,searchDomain.toString());
    }

    @Test(priority = 2,description = "修改KBP")
    public void editKbp()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        String sql="select * from TB_CDE_KBP t order by length(t.KBP_CLASS) desc";
        List<Map<String , String >> list=dbTools.queryMapListHandler(sql);

        String kbp_caption=list.get(0).get("kbp_caption");
        String kbp_classPre=list.get(0).get("kbp_class");
        KbpSearchDomain searchDomain=new KbpSearchDomain();
        searchDomain.setKbp_Caption(kbp_caption);
        searchDomain.setKbp_class(kbp_classPre);
        String nowTime=String.valueOf(tools.random());

        KbpFormDomain kbpFormDomain=new KbpFormDomain();
        kbpFormDomain.setKBP_CAPTION(nowTime);
        kbpFormDomain.setKBP_DESC("seleniumKBP描述"+nowTime);
        kbpFormDomain.setVIEW_STYLE("一维表格");
        kbpFormDomain.setENABLE("是");

        kbpFrame.operateKbp("修改",null,searchDomain,kbpFormDomain);
        tools.refresh();

        searchDomain.setKbp_Caption(nowTime);
        searchDomain.setKbp_class(kbp_classPre);
        gridTable=kbpFrame.search(searchDomain);

        tools.sleep();
        tools.assertEquals(gridTable.getGridrowNum(),1,searchDomain.toString()+","+kbpFormDomain.toString());
    }

    @Test(priority = 2,description = "增加根节点KBP")
    public void addKbpRoot()
    {
        tools.refresh();

        map=new HashMap<String, String>();

        //获取第一级别的KBP_CLASS列表;

        String sql="select kbp_class from tb_cde_kbp ";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);



        SortedSet<Integer> list2=new TreeSet();
        for(int i=0;i<list.size();i++)
        {
            String kbp_class=list.get(i).get("KBP_CLASS");
            try{
                list2.add(Integer.parseInt(kbp_class.split("-")[0]));
            }catch (Exception e)
            {

            }
        }

        String kbp_class=Integer.toString(list2.last().intValue()+1);


        String nowTime=tools.formatNow();
        String kbp_caption="selninum"+nowTime;

        String kpiPre=String.valueOf(tools.random());

        //map.put("关联KPI前缀", kpiPre);
        map.put("关联KPI前缀", kbp_class);
        map.put("操作类型","增加");
        map.put("编号",kbp_class);
        map.put("名称",kbp_caption);
        map.put("KBP描述","KBP描述");
        map.put("展现样式","二维表格");
        map.put("是否有效","是");
        KbpFormDomain domain=new KbpFormDomain();
        domain.setENABLE("是");
        domain.setVIEW_STYLE("二维表格");
        domain.setKBP_DESC("selenium描述");
        domain.setMiddle(kbp_class);//关联KPI前缀
        domain.setKBP_CLASS(kbp_class);
        domain.setKBP_CAPTION(kbp_caption);

        kbpFrame.operateKbp("增加",null,null,domain);
        tools.refresh();

        KbpSearchDomain searchDomain=new KbpSearchDomain();
        searchDomain.setKbp_Caption(kbp_caption);
        searchDomain.setKbp_class(kbp_class);

        gridTable=kbpFrame.search(searchDomain);

        tools.sleep();
        tools.assertEquals(gridTable.getGridrowNum(),1,domain.toString());
    }

}
