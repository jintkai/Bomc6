package com.code.test.ibnmsConfig;

import com.code.common.DBTools;
import com.code.common.Data;
import com.code.common.GridPage;
import com.code.common.TestCase;
import com.code.page.ibnmsConfig.agentList.AgentListPage;
import com.code.page.ibnmsConfig.collBusiConfig.CollBusiConfigPage;
import com.code.page.ibnmsConfig.collBusiConfig.domain.ShellFormDomain;
import com.code.page.ibnmsConfig.kbplist.domain.KbpSearchDomain;
import com.code.page.ibnmsConfig.kpilist.domain.KpiSearchDomain;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jon on 2016/10/20.
 */
public class CollBusiConfTest extends TestCase {
    CollBusiConfigPage collBusiConfigPage=new CollBusiConfigPage(eventDriver);
    GridPage gridPage=new GridPage(eventDriver);
    String rowValue;
    String rowName;
    @Parameters({"node"})
    public CollBusiConfTest(String node) {
        super(node);
        if(DBTools.url.contains("172.21.2.96:3306/bnms_cs")){
            rowName="Agent名称";
            rowValue="agent96";
        }
        if(DBTools.url.contains("172.21.1.5:1523:bnms")){
            rowName="安装路径";
            rowValue="/test-bnms/app/";
        }
    }

    @BeforeMethod
    @Parameters({"Action_URL"})
    public void beforeMethod(String actionUrl)
    {
        eventDriver.get(Data.baseUrl + actionUrl);
    }

    @Test
    public void addShellBusiConf(){

        String sql="select * from tb_cde_kbp where kbp_class like '11-__'";
        List<Map<String,String>> list=dbTools.queryMapListHandler(sql);
        String kbpClass=list.get(0).get("kbp_class");
        String kbpName=list.get(0).get("kbp_caption");
        sql="select * from tb_cde_kpi where kpi_id like 'PM-%' and kbp_class like '11-__'";
        list=dbTools.queryMapListHandler(sql);
        String kpiClass1=list.get(0).get("kpi_id");
        String kpiName1=list.get(0).get("kpi_name");
        String kpiClass2=list.get(1).get("kpi_id");
        String kpiName2=list.get(1).get("kpi_name");

        ShellFormDomain formDomain=new ShellFormDomain();
        String serviceName="selenium"+tools.formatNow();
        formDomain.setCronsName("间隔1分钟，0秒启动");
        formDomain.setServiceDesc("selenium描述"+tools.formatNow());
        formDomain.setServiceName(serviceName);
        KbpSearchDomain kbpSearchDomain=new KbpSearchDomain();
        kbpSearchDomain.setKbp_class(kbpClass);
        kbpSearchDomain.setKbp_Caption(kbpName);
        formDomain.setKbpSearchDomain(kbpSearchDomain);
        formDomain.setOverTimeId("1200");
        formDomain.setShellType("sh");
        formDomain.setShellName("selenium"+tools.formatNow()+".sh");
        KpiSearchDomain[] kpiSearchDomains=new KpiSearchDomain[2];
        kpiSearchDomains[0]=new KpiSearchDomain(kpiClass1,kpiName1);
        kpiSearchDomains[1]=new KpiSearchDomain(kpiClass2,kpiName2);
        String shell=new String();
        String t=tools.formatNow();
        String unit_id=kbpClass+":"+t;
        shell="#output# ${UNIT_ID}:"+t+" $KPI1 "+tools.random()+";"+"#output# ${UNIT_ID}:"+t+" $KPI2 "+tools.random();
        formDomain.setShell(shell);
        formDomain.setKpiSearchDomains(kpiSearchDomains);
        AgentListPage agentListPage=new AgentListPage(eventDriver);
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        agentListPage.addBusi("业务采集配置",map,formDomain);
        tools.sleep(10000);
        GridPage gridTable=new GridPage(eventDriver);

        gridTable.getGridrowNum();

        collBusiConfigPage.search("SHELL采集",serviceName);
        tools.assertEquals(gridTable.getGridrowNum(),1,formDomain.toString());
        collBusiConfigPage.addShellColl(formDomain);

        sql="select * from tb_ato_kpidetail where unit_id='"+unit_id+"' and (kpi_id ='"+kpiClass1+"' or kpi_id ='"+kpiClass2+"')";
        int n=0;
        while ((n++)<24) {

            tools.sleep(10*1000);
            list = dbTools.queryMapListHandler(sql);
            if(list.size()==2) {
                break;
            }
            System.out.println("延迟时间s:"+n*10);
        }

        tools.assertEquals(list.size(),2,"kpidetail未收集到采集数据,检查agent后台程序。延迟时间s:"+n*10+","+unit_id+";"+kpiClass1+":"+kpiClass2);
    }
}
