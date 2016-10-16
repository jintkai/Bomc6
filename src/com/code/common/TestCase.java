package com.code.common;


import com.code.page.ibnmsConfig.login.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;


/**
 * Created by jinkai on 2014/6/21.
 * Selenium Grid的两个用途:
 * 1、同时测试多平台、多浏览器、多版本;
 * 2、测试用例较多,缩短测试时间;
 * 测试用例分布在不同的远程控制节点node中运行,脚本只需要在中央节点hub
 *
 * 步骤1:
 * 启动hub中央节点:
 *  java -jar selenium-server-standalone-2.53.0.jar -role hub
 * 查看状态:
 *  http://localhost:4444/grid/console
 *
 * 步骤2:
 * 启动node远程控制节点:
 *  java -jar selenium-server-standalone-2.53.0.jar -role node -hub http://localhost:4444/grid/register -browser browserName=firefox maxInstances=3
 *
 *  默认每个节点可以启动 11 个浏览器，包括 5 个 firefox、5 个 chrome 和 1 个 ie 浏览器。最多有 5 个并行的测试案例。
 *   如果参数-browser不设置,则默认可以开启5个firefox,1个chrome,1个InternetExplorer
 *   -browser:
 *   browserName={android, chrome, firefox, htmlunit, internet explorer, iphone, opera} version={browser version}
 *   firefox_binary={path to executable binary} chrome_binary={path to executable binary}
 *   maxInstances={maximum number of browsers of this type}
 *   platform={WINDOWS, LINUX, MAC}
 *  使用json配置:java -jar selenium-server-standalone.jar -role node -nodeConfig nodeconfig.json
 *              java -jar selenium-server-standalone.jar -role hub -hubConfig hubconfig.json
 *
 *-browser browserName=firefox,version=3.6,firefox_binary=/home/myhomedir/firefox36/firefox,maxInstances=3,platform=LINUX -browser browserName=firefox,version=4,firefox_binary=/home/myhomedir/firefox4/firefox,maxInstances=4,platform=LINUX
 */
/*
{
  "capabilities":
      [
        {
          "browserName": "*firefox",
          "maxInstances": 5,
          "seleniumProtocol": "Selenium"
        },
        {
          "browserName": "*googlechrome",
          "maxInstances": 5,
          "seleniumProtocol": "Selenium"
        },
        {
          "browserName": "*iexplore",
          "maxInstances": 1,
          "seleniumProtocol": "Selenium"
        },
        {
          "browserName": "firefox",
          "maxInstances": 5,
          "seleniumProtocol": "WebDriver"
        },
        {
          "browserName": "chrome",
          "maxInstances": 5,
          "seleniumProtocol": "WebDriver"
        },
        {
          "browserName": "internet explorer",
          "maxInstances": 1,
          "seleniumProtocol": "WebDriver"
        }
      ],
  "configuration":
  {
    "proxy": "org.openqa.grid.selenium.proxy.DefaultRemoteProxy",
    "maxSession": 5,
    "port": 5555,
    "host": ip,
    "register": true,
    "registerCycle": 5000,
    "hubPort": 4444,
    "hubHost": ip
  }
}
{
  "host": null,
  "port": 4444,
  "newSessionWaitTimeout": -1,
  "servlets" : [],
  "prioritizer": null,
  "capabilityMatcher": "org.openqa.grid.internal.utils.DefaultCapabilityMatcher",
  "throwOnCapabilityNotPresent": true,
  "nodePolling": 5000,
  "cleanUpCycle": 5000,
  "browserTimeout": 0,
  "jettyMaxThreads":-1
}

 */
public class TestCase {

    public  WebDriver driver;//=createDriver("IE","http://192.168.1.102:5555/wd/hub");
    public DBTools dbTools=new DBTools();
    public  EventFiringWebDriver eventDriver;//=new EventFiringWebDriver(driver).register(new EventListener());
    public Tools tools;//=new Tools(eventDriver);
    public String[] excelHead;
    public Map<String,String> map;
    public WebDriver 	createDriver(String BrowserType,String nodeURL) {
        Properties props=System.getProperties();
        String pcVersion=props.getProperty("os.arch");
        System.out.println("操作系统版本："+pcVersion);
        if(BrowserType.equals("IE"))
        {
            if(pcVersion.contains("64"))
            { System.setProperty("webdriver.ie.driver",
                    Data.baseDir+ "\\Driver\\IEDriverServer64.exe");}
            else
                System.setProperty("webdriver.ie.driver",
                        Data.baseDir+ "\\Driver\\IEDriverServer32.exe");

            /*如果IE浏览器设置安全性较高，在“Internet Options”中都不要选择“Enable Protected Mode”（保护模式），否则可能遇到如下的错误提示。
            解决方法：
            一种是修改掉IE的设置，不要在任何情况下使用保护模式（protected mode），另一种即是前面代码中如下片段在运行时设置IE的Capabilities。
            第二种方法应该是在运行时设置IE的部分参数。鉴于代码健壮性考虑，使用第二种方法，继续修改代码。
            */
            DesiredCapabilities ieCapabilities = DesiredCapabilities
                    .internetExplorer();
            ieCapabilities
                    .setCapability(
                            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                            true);
            //driver = new InternetExplorerDriver(ieCapabilities);
            DesiredCapabilities test=DesiredCapabilities.internetExplorer();
            try {
                driver = new RemoteWebDriver(new URL(nodeURL),ieCapabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else
        if(BrowserType.equals("FIREFOX"))
        {
            DesiredCapabilities test = DesiredCapabilities.firefox();
            try {

                if (nodeURL.equals(""))
                {
                   driver=new FirefoxDriver();
                }
                else
                    driver = new RemoteWebDriver(new URL(nodeURL),test);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else
        if(BrowserType.equals("safari")){
            System.out.println("safari浏览器");
           driver=new SafariDriver();
        }
        else
        if(BrowserType.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",  Data.baseDir+ "/Driver/chromedriver.exe");
            driver=new ChromeDriver();
        }
        else
        if(BrowserType.toLowerCase().equals("htmlUnit".toLowerCase()))
        {
            driver = new FirefoxDriver();

//            DesiredCapabilities test=DesiredCapabilities.htmlUnit();
//            try {
//                driver = new RemoteWebDriver(new URL(" http://172.21.10.31:4456/wd/hub"), test);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
        }
        else
        {
            DesiredCapabilities ieCapabilities = DesiredCapabilities
                    .internetExplorer();
            ieCapabilities
                    .setCapability(
                            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                            true);
            DesiredCapabilities test = DesiredCapabilities.firefox();

            test.setVersion("47.01");
            try {
                driver = new RemoteWebDriver(new URL(" http://192.168.0.6:5555/wd/hub"), ieCapabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }


    public EventFiringWebDriver getEventDriver(){return  eventDriver;}

    @Parameters({"Base_URL"})
    @BeforeClass(alwaysRun=true)
    public void beforeClass(String base_url)
    {
        eventDriver.get(base_url);
        eventDriver.manage().window().maximize();
        LoginPage login=new LoginPage(eventDriver);
        login.login("admin","123456");

    }

    public TestCase(String node)
    {
        driver=createDriver("htmlUnit1",node);
        eventDriver=new EventFiringWebDriver(driver);
        eventDriver.register(new EventListener());
        tools=new Tools(eventDriver);
    }

    @AfterClass(alwaysRun=true)
    public  void afterClass()
    {
        eventDriver.close();
        eventDriver.quit();

    }
    @Parameters({"Base_URL"})
    //@BeforeSuite
    public void beforeSuite()
    {
        /*eventDriver.get(Data.baseUrl);
        eventDriver.manage().window().maximize();
        eventDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        */
       // driver=createDriver("IE","http://192.168.1.104:5555/wd/hub");
        //eventDriver=new EventFiringWebDriver(driver).register(new EventListener());
        //eventDriver.get(base_url);
        eventDriver.manage().window().maximize();
        eventDriver.get("http://172.21.0.31:8084");
        LoginPage login=new LoginPage(eventDriver);
        login.login("admin","123456");
    }
    //@AfterSuite
    public void afterSuite()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eventDriver.close();
        eventDriver.quit();
    }
}
