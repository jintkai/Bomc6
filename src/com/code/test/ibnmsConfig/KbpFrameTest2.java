package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.kbplist.KbpListFramePage;
import com.code.page.ibnmsConfig.kbplist.domain.KbpFormDomain;
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
 * Created by jinkai on 01/07/2014.
 */
public class KbpFrameTest2 extends TestCase {
    KbpListFramePage kbpFrame;
    GridPage gridTable;
    //KbpFormDao kbpFormDao;
    @Parameters({"node"})
    public KbpFrameTest2(String node)
    {
        super(node);
        kbpFrame=new KbpListFramePage(eventDriver);
        gridTable=new GridPage(eventDriver);
        Reporter.log("selenium Grid:"+node);
        //kbpFormDao=new KbpFormDao();
    }
    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @DataProvider(name="kbpList")
    public Iterator dataDriver(Method method) throws IOException, BiffException {
        ExcelDriver excelDriver=new ExcelDriver("KBP管理",method.getName());
        excelHead=excelDriver.getHead(0);
        return excelDriver;
    }
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
        gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),map);
    }

    @Test(priority = 0,description = "通过KBP编号精确查询")
    public void searchKBP1()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        map.clear();
        String sqlStr="select * from (select length(t.KBP_CLASS) as l,t.* from TB_CDE_KBP t  order by l desc ) t2 ";
        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        Map mapSQL=list.get(0);

        map.put("KBP编号_KBP", (String) mapSQL.get("kbp_class"));
        map.put("KBP名称_KBP",null);
        gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(1),map);
    }

    @Test(priority = 0,description = "通过KBP名称查询")
    public void searchKBP2()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        map.clear();
        //map.put("KBP编号_KBP",null);
        String sqlStr="select * from (select length(t.KBP_CLASS) as l,t.* from TB_CDE_KBP t  order by l desc ) t2  where l is not null ";
        List<Map> list=dbTools.queryMapListHandler(sqlStr);
        Map<String,String> mapSQL=list.get(0);
        sqlStr="select * from tb_cde_kbp where kbp_caption like '%"+mapSQL.get("kbp_caption")+"%'";
        list=dbTools.queryMapListHandler(sqlStr);
        map.put("KBP名称_KBP",mapSQL.get("kbp_caption"));;
        gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),map);
    }

    @Test(priority = 0,description = "KBP编号,KBP名称组合查找")
    public void searchKBP3()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        String strSQL="select * from tb_cde_kbp";
        List<Map> list=dbTools.queryMapListHandler(strSQL);
        Map<String,String> mapSQL=list.get(20);
        map.put("KBP编号_KBP",mapSQL.get("KBP_CLASS"));
        map.put("KBP名称_KBP",mapSQL.get("KBP_CAPTION"));
        map.put("期望值","1");
        gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),tools.getMapValue(map,"期望值"),map);
    }

    @Test(priority = 1,description = "通过KBP名称查询KBP树")
    public void searchKBPByTree()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        //map=tools.changeStringToMap(excelHead,str);
        //kbpFormDao=new KbpFormDao();
        String sql="select * from (select length(t.KBP_CAPTION) as l,t.* from TB_CDE_KBP t  order by l desc ) t2  where l is not null ";
        List<Map<String,String>> list1=dbTools.queryMapListHandler(sql);
        String kbp_caption=list1.get(0).get("KBP_CAPTION");
        String kbp_class=list1.get(0).get("kbp_class");
        //String kbp_caption=kbpFormDao.selectOneKbpByCaption().getKBP_CAPTION();
        KbpFormDomain domain=new KbpFormDomain();
        domain.setKBP_CAPTION(kbp_caption);
        String sql2="select * from tb_cde_kbp where KBP_CAPTION like \'%"+kbp_caption+"%'";
        list1=dbTools.queryMapListHandler(sql2);

        //List<KbpFormDomain> list=kbpFormDao.selectKbpList(kbpFormDao.selectKbpList(domain).get(0));

        map.put("KBP",kbp_caption);

        gridTable=kbpFrame.searchByTree(map);
        sql="select * from tb_cde_kbp where kbp_class ='"+kbp_class+"' or kbp_class like '"+kbp_class+"-%'";
        list1=dbTools.queryMapListHandler(sql);
        //为什么还要查一次?
        /*KbpFormDomain domain2=new KbpFormDomain();
        domain2.setKBP_CLASS(list.get(0).getKBP_CLASS());
        list=kbpFormDao.selectKbpList(domain2);*/

        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list1.size()),map);
    }

    @Test(priority = 1,description = "通过KBP编号查询KBP树")
    public void searchKBPByTree2()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        //map=tools.changeStringToMap(excelHead,str);
        //kbpFormDao=new KbpFormDao();
        //String kbp_class=kbpFormDao.selectOneKbpByClass().getKBP_CLASS();
        String sql="select KBP_CLASS from (select length(t.KBP_CLASS) as l,t.* from TB_CDE_KBP t  order by l desc ) t2 ";
        String kbp_class=( (Map<String,String>) (dbTools.queryMapListHandler(sql).get(0))).get("kbp_class");

        //通过最长的KBP_CLASS应该能确定肯定有一条记录,为何还要查询一次sql;
        /*KbpFormDomain domain=new KbpFormDomain();
        domain.setKBP_CLASS(kbp_class);
        List<KbpFormDomain> list=kbpFormDao.selectKbpList(kbpFormDao.selectKbpList(domain).get(0));
        */
        map.put("KBP",kbp_class);

        gridTable=kbpFrame.searchByTree(map);
        /*KbpFormDomain domain2=new KbpFormDomain();
        domain2.setKBP_CLASS(list.get(0).getKBP_CLASS());
        list=kbpFormDao.selectKbpList(domain2);*/
        tools.sleep(5000);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(1),map);
    }


    @Test(priority = 2,description="KBP增加")
    public void addKBP()
    {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String nowStr=dateFormat.format(now);

        map.put("操作类型","增加");
        String sql="select * from TB_CDE_KBP t order by length(t.KBP_CLASS) desc";
        List<Map<String , String >> list=dbTools.queryMapListHandler(sql);
        map.put("KBP编号前缀",list.get(0).get("KBP_CLASS"));
        //map.put("编号","");
        map.put("名称","selenium"+nowStr);
        map.put("KBP描述","selenium描述:"+nowStr);

        int r=(int)(Math.random()*10);
        switch (r%3) {
            case 1: map.put("展现样式", "一维表格");

                break;
            case 2: map.put("展现样式", "二维表格");
                break;

            default: map.put("展现样式", "自定义页面");
        }
        r=(int)(Math.random()*10);
        switch (r%2) {
            case 1: map.put("是否有效","是");
                break;

            default: map.put("是否有效","否");
        }
        int kpiPre=now.getSeconds();
        map.put("关联KPI前缀", String.valueOf(kpiPre));
        kbpFrame.operateKbp(map);
        map.put("KBP名称_KBP","selenium"+nowStr);
        gridTable=kbpFrame.search(map);

        tools.assertEquals(gridTable.getRowNum(),1,map);
    }

    @Test(priority = 3,description="KBP删除")
    public void deleteKBP()
    {

        map.put("操作类型","删除");
        String sql="select * from TB_CDE_KBP t order by length(t.KBP_CLASS) desc";
        List<Map<String , String >> list=dbTools.queryMapListHandler(sql);

        map.put("KBP编号_KBP",list.get(0).get("KBP_CLASS"));
        kbpFrame.operateKbp(map);

        gridTable=kbpFrame.search(map);

        tools.assertEquals(gridTable.getRowNum(),0,map);
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
        map.put("KBP编号_KBP",kbp_classPre);
        map.put("KBP名称_KBP",kbp_caption);
        double r= Math.random();
        Integer rInt= new Integer((int) (r*10000));
        String nowTime=String.valueOf(rInt);
        map.put("操作类型","修改");
        map.put("名称",kbp_caption+nowTime);
        map.put("KBP描述","KBP描述"+nowTime);
        map.put("展现样式","一维表格");
        map.put("是否有效","是");
        kbpFrame.operateKbp(map);
        tools.refresh();
        map.put("KBP编号_KBP",kbp_classPre);
        map.put("KBP名称_KBP",kbp_caption+nowTime);
        gridTable=kbpFrame.search(map);

        tools.sleep();
        tools.assertEquals(gridTable.getGrid_xb(),"1",map);
    }

    @Test(priority = 2,description = "增加根节点KBP")
    public void addKbpRoot()
    {
        tools.refresh();

        map=new HashMap<String, String>();

        //获取第一级别的KBP_CLASS列表;
        /**
         * 此sql存在问题。10-10,能获取。但是11 这种获取不到;
         */
        String sql="select KBP_CLASS from ( SELECT KBP_CLASS FROM ( SELECT t.KBP_CLASS, " +
                "substr(t.KBP_CLASS, 0, INSTR(t.KBP_CLASS, '-', '1')) AS ts FROM TB_CDE_KBP t ) t2 WHERE t2.ts IS NULL ORDER BY t2.ts DESC ) GROUP BY KBP_CLASS";
        sql="select kbp_class from tb_cde_kbp ";
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

        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String nowTime=df.format(new Date());
        String kbp_caption="selninum"+nowTime;
        double r= Math.random();
        Integer rInt= new Integer((int) (r*10000));
        String kpiPre=String.valueOf(rInt);

        map.put("关联KPI前缀", kpiPre);

        map.put("操作类型","增加");
        map.put("编号",kbp_class);
        map.put("名称",kbp_caption);
        map.put("KBP描述","KBP描述");
        map.put("展现样式","二维表格");
        map.put("是否有效","是");
        kbpFrame.operateKbp(map);
        tools.refresh();
        map.put("KBP编号_KBP",kbp_class);
        map.put("KBP名称_KBP",kbp_caption);
        gridTable=kbpFrame.search(map);

        tools.sleep();
        tools.assertEquals(gridTable.getGrid_xb(),"1",map);
    }

}
