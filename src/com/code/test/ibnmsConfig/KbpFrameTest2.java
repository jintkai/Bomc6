package com.code.test.ibnmsConfig;

import com.code.common.Data;
import com.code.common.ExcelDriver;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.kbplist.KbpListFramePage;
import com.code.page.ibnmsConfig.kbplist.dao.KbpFormDao;
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
    KbpFormDao kbpFormDao;
    @Parameters({"node"})
    public KbpFrameTest2(String node)
    {
        super(node);
        kbpFrame=new KbpListFramePage(eventDriver);
        gridTable=new GridPage(eventDriver);
        Reporter.log("selenium Grid:"+node);
        kbpFormDao=new KbpFormDao();
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
        //map=tools.changeStringToMap(excelHead,str);
        map=new HashMap<String, String>();
        String kbp_class="11";
        KbpFormDomain searchDomain=new KbpFormDomain();
        searchDomain.setKBP_CLASS(kbp_class);
        kbpFormDao=new KbpFormDao();
        List<KbpFormDomain> list=kbpFormDao.selectKbpList(searchDomain);

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
        kbpFormDao=new KbpFormDao();
        map.put("KBP编号_KBP",kbpFormDao.selectOneKbpByClass().getKBP_CLASS());
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
        map.put("KBP编号_KBP",null);
        kbpFormDao=new KbpFormDao();
        map.put("KBP名称_KBP",kbpFormDao.selectOneKbpByCaption().getKBP_CAPTION());
        gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(1),map);
    }
    @Test(priority = 0,description = "KBP编号,KBP名称组合查找")
    public void searchKBP3()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        kbpFormDao=new KbpFormDao();
        KbpFormDomain domain=kbpFormDao.selectKbpByRowNum(30);
        map.put("KBP编号_KBP",domain.getKBP_CLASS());
        map.put("KBP名称_KBP",domain.getKBP_CAPTION());
        map.put("期望值","1");
        gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),tools.getMapValue(map,"期望值"),map);
    }

    //@Test(priority = 1,description = "通过KBP名称查询KBP树")
    @Test(priority = 1)
    public void searchKBPByTree()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        //map=tools.changeStringToMap(excelHead,str);
        kbpFormDao=new KbpFormDao();
        String kbp_caption=kbpFormDao.selectOneKbpByCaption().getKBP_CAPTION();
        KbpFormDomain domain=new KbpFormDomain();
        domain.setKBP_CAPTION(kbp_caption);
        List<KbpFormDomain> list=kbpFormDao.selectKbpList(kbpFormDao.selectKbpList(domain).get(0));

        map.put("KBP",kbp_caption);

        gridTable=kbpFrame.searchByTree(map);
        KbpFormDomain domain2=new KbpFormDomain();
        domain2.setKBP_CLASS(list.get(0).getKBP_CLASS());
        list=kbpFormDao.selectKbpList(domain2);

        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),map);
    }

    @Test(priority = 1,description = "testoe")
    public void searchKBPByTree2()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        //map=tools.changeStringToMap(excelHead,str);
        kbpFormDao=new KbpFormDao();
        String kbp_class=kbpFormDao.selectOneKbpByClass().getKBP_CLASS();
        KbpFormDomain domain=new KbpFormDomain();
        domain.setKBP_CLASS(kbp_class);
        List<KbpFormDomain> list=kbpFormDao.selectKbpList(kbpFormDao.selectKbpList(domain).get(0));

        map.put("KBP",kbp_class);

        gridTable=kbpFrame.searchByTree(map);
        KbpFormDomain domain2=new KbpFormDomain();
        domain2.setKBP_CLASS(list.get(0).getKBP_CLASS());
        list=kbpFormDao.selectKbpList(domain2);

        tools.assertEquals(gridTable.getGrid_xb(),Integer.toString(list.size()),map);
    }
/*
    @Test(dataProvider="kbpList",priority = 2,description="KBP增加、删除、修改;")
    public void operateKBP(String str[])
    {
        map=tools.changeStringToMap(excelHead,str);
        String option=tools.getMapValue(map,"操作类型");
        kbpFrame.operateKbp(map);
        gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getRowNum(),Integer.parseInt(tools.getMapValue(map,"期望值")),map);
    }
    */

    @Test(priority = 3,description = "删除KBP")
    public void deleteKBP()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        map.clear();
        map.put("操作类型","删除");

        kbpFormDao=new KbpFormDao();
        KbpFormDomain domain=kbpFormDao.selectOneKbpByClass();
        map.put("KBP编号_KBP",domain.getKBP_CLASS());
        map.put("KBP名称_KBP",null);
        kbpFrame.operateKbp(map);
        gridTable=kbpFrame.search(map);
        tools.assertEquals(gridTable.getGrid_xb(),"0",map);
    }

    @Test(priority = 2,description = "增加根节点KBP")
    public void addKbp()
    {
        tools.refresh();
        kbpFormDao=new KbpFormDao();
        map=new HashMap<String, String>();
        List<String > listKbp=kbpFormDao.selectKbpID();

        SortedSet<Integer> list2=new TreeSet();
        for(int i=0;i<listKbp.size();i++)
        {
            try{
                list2.add(Integer.parseInt(listKbp.get(i)));
            }catch (Exception e)
            {

            }
        }

        String kbp_class=Integer.toString(list2.last().intValue()+1);
        KbpFormDomain domain=new KbpFormDomain();
        domain.setKBP_CLASS(kbp_class);
        SimpleDateFormat df = new SimpleDateFormat("yyyymmss");
        String nowTime=df.format(new Date());
        String kbp_caption="KBP名称"+nowTime;
        domain.setKBP_CAPTION(kbp_caption);
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

    @Test(priority = 2,description = "增加非根节点KBP")
    public void addKbp2()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        //获取根节点domain对象
        kbpFormDao=new KbpFormDao();
        KbpFormDomain domain_p=kbpFormDao.selectOneKbpByClass();
        String kbp_caption=domain_p.getKBP_CAPTION();
        String kbp_classPre=domain_p.getKBP_CLASS();
        map.put("KBP",kbp_caption);

        String kbp_class="666";
        KbpFormDomain domain=new KbpFormDomain();
        domain.setKBP_CLASS(kbp_class);
        SimpleDateFormat df = new SimpleDateFormat("yyyymmss");
        String nowTime="1";
        domain.setKBP_CAPTION(kbp_caption);
        map.put("操作类型","增加");
        map.put("编号",kbp_class);
        map.put("名称",kbp_caption+nowTime);
        map.put("KBP编号前缀",kbp_caption);
        map.put("KBP描述","KBP描述");
        map.put("展现样式","二维表格");
        map.put("是否有效","是");
        kbpFrame.operateKbp(map);
        tools.refresh();
        map.put("KBP编号_KBP",kbp_classPre+"-"+kbp_class);
        map.put("KBP名称_KBP",kbp_caption+nowTime);
        gridTable=kbpFrame.search(map);

        tools.sleep();
        tools.assertEquals(gridTable.getGrid_xb(),"1",map);
    }

    @Test(priority = 4,description = "修改KBP")
    public void editKbp()
    {
        tools.refresh();
        map=new HashMap<String, String>();
        //得到要修改的数据
        kbpFormDao=new KbpFormDao();
        KbpFormDomain domain_p=kbpFormDao.selectOneKbpByClass();
        String kbp_caption=domain_p.getKBP_CAPTION();
        String kbp_classPre=domain_p.getKBP_CLASS();
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

}
