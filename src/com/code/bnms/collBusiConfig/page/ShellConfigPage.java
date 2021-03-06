package com.code.bnms.collBusiConfig.page;

import com.code.common.Page;
import com.code.bnms.collBusiConfig.domain.ShellFormDomain;
import com.code.bnms.cronsConf.CronsShowPage;
import com.code.bnms.kbplist.KbpListFramePage;
import com.code.bnms.kpilist.KpiListFramePage;
import com.code.bnms.kpilist.domain.KpiSearchDomain;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

/**
 * Created by jon on 2016/10/20.
 */
public class ShellConfigPage extends Page {
    @FindBy(id="schedule.serviceName")
    WebElement serviceName;
    @FindBy(id="btn-addkbp")
    WebElement addKbpBtn;
    @FindBy(name="schedule.serviceDesc")
    WebElement serviceDesc;
    @FindBy(id="btn-addCron")
    WebElement addCronBtn;
    @FindBy(id="shell.shellName")
    WebElement shellName;
    @FindBy(id="shell.shellType")
    WebElement shellType;
    @FindBy(id = "shellsubmit")
    WebElement shellSubmit;
    @FindBy(id="overTimeId")
    WebElement overTimeId;
    @FindBy(id="btn-addvars")
    WebElement addVarsBtn;
    public ShellConfigPage(EventFiringWebDriver driver){
        super(driver);
    }

    private void inputForm(String operation, String type,Object domain1){
        if (type.equals("SHELL")) {
            ShellFormDomain domain = (ShellFormDomain) domain1;
            if (operation.equals("增加")) {
                tools.sendKeys(serviceName, domain.getServiceName());
                tools.sendKeys(serviceDesc, domain.getServiceDesc());
                if (domain.getCronsName() != null) {
                    tools.click(addCronBtn);
                    CronsShowPage cronsShowPage = new CronsShowPage(eventDriver);

                    cronsShowPage.select(domain.getCronsName());

                }
                if (domain.getKbpSearchDomain() != null) {
                    tools.click(addKbpBtn);
                    KbpListFramePage kbpListFramePage = new KbpListFramePage(eventDriver);
                    kbpListFramePage.select(domain.getKbpSearchDomain());
                }
                tools.sendKeys(shellName, domain.getShellName());
                tools.sendKeys(shellType, domain.getShellType());
                tools.sendKeys(overTimeId, domain.getOverTimeId());
                if (domain.getKpiSearchDomains() != null) {
                    KpiSearchDomain kpiSearchDomain = new KpiSearchDomain();
                    for (int i = 0; i < domain.getKpiSearchDomains().length; i++) {

                        tools.click(addVarsBtn);
                        List<WebElement> e = tools.findElements(eventDriver, By.className("btn-addkpi"));
                        tools.click(e.get(i));
                        kpiSearchDomain = domain.getKpiSearchDomains()[i];
                        KpiListFramePage kpiListFramePage = new KpiListFramePage(eventDriver);
                        kpiListFramePage.select(kpiSearchDomain);
                    }
                }
                if (domain.getShell() != null) {
                    WebElement div = tools.findBy(eventDriver, By.className("CodeMirror"));
                    JavascriptExecutor js = (JavascriptExecutor) eventDriver;
                    String str = domain.getShell();
                    js.executeScript("arguments[0].CodeMirror.setValue(\"" + str + "\");", div);

                }
            }

        }

    }
    public void opertate(String operation,String type,Object domain){

        inputForm(operation,type,domain);
        tools.click(shellSubmit);
    }
}
