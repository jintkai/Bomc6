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

        ShellFormDomain formDomain=new ShellFormDomain();
        String serviceName="selenium"+tools.formatNow();
        formDomain.setCronsName("间隔1分钟，0秒启动");
        formDomain.setServiceDesc("selenium描述"+tools.formatNow());
        formDomain.setServiceName(serviceName);
        KbpSearchDomain kbpSearchDomain=new KbpSearchDomain();
        kbpSearchDomain.setKbp_class("11-");
        formDomain.setKbpSearchDomain(kbpSearchDomain);
        formDomain.setOverTimeId("1200");
        formDomain.setShellType("sh");
        formDomain.setShellName(tools.formatNow());
        KpiSearchDomain[] kpiSearchDomains=new KpiSearchDomain[2];
        kpiSearchDomains[0]=new KpiSearchDomain("CM",null);
        kpiSearchDomains[1]=new KpiSearchDomain("PM",null);
        String[] shells=new String[1];
        shells[0]="hello";
        formDomain.setShell(shells);
        formDomain.setKpiSearchDomains(kpiSearchDomains);
        AgentListPage agentListPage=new AgentListPage(eventDriver);
        Map<String,String> map=new HashMap<>();
        map.put("列值",rowValue);
        map.put("列名",rowName);
        GridPage gridTable=agentListPage.addBusi("业务采集配置",map,formDomain);

        //collBusiConfigPage.addShellColl(formDomain);
    }
}
