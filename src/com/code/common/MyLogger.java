package com.code.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

/**
 * Created by Jin on 2014/8/20.
 */
public class MyLogger {
    public Logger logger;
    public MyLogger()
    {
        PropertyConfigurator.configure(Data.baseDir+"\\config\\log4j.properties");
        logger=Logger.getLogger(MyLogger.class.getName());
    }
    @Test
    public void test()
    {
        logger.info("INFO hello");
        logger.debug(" debug HELLO");
        logger.error(" error HELLO");
        logger.warn(" warm HELLO");
    }

}
