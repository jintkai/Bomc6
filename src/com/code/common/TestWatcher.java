package com.code.common;

import org.junit.runner.Description;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jon on 2016/10/8.
 */
public class TestWatcher {
    private File filenameFor(Description description){
        String className=description.getClassName();
        String methodName=description.getMethodName();
        return new File("directory",className+"_"+methodName+".png");
    }
    private  void silentlySaveScreenshotTo(File file,String format) throws IOException, AWTException {
        saveScreenshotTo(file, format);

    }
    private static BufferedImage takeScreenshot() throws AWTException {
        Robot robot=new Robot();
        Rectangle captureSize=new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(captureSize);

    }
    private static void saveScreenshotTo(File file,String format) throws AWTException, IOException {
        ImageIO.write(takeScreenshot(),format,file);
    }
    @Test
    public void test()
    {
        TestWatcher testWatcher=new TestWatcher();


    }
}
