package com.webauto.utils;

import com.webauto.base.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    /**
     * 实现文件的拷贝
     *
     * @param filePath
     * @return
     */
    public static File saveScreenshot(String filePath) {
        File screenshot = null;
        //截图
        if (Base.driver instanceof FirefoxDriver) {
            FirefoxDriver firefoxDriver = (FirefoxDriver) Base.driver;
            screenshot = firefoxDriver.getScreenshotAs(OutputType.FILE);
        } else if (Base.driver instanceof InternetExplorerDriver) {
            InternetExplorerDriver ieDriver = (InternetExplorerDriver) Base.driver;
            screenshot = ieDriver.getScreenshotAs(OutputType.FILE);
        } else if (Base.driver instanceof ChromeDriver) {
            ChromeDriver chromeDriver = (ChromeDriver) Base.driver;
            screenshot = chromeDriver.getScreenshotAs(OutputType.FILE);
        }
        File targetFile = new File(filePath);
        System.out.println("targetFile："+targetFile.getAbsolutePath());
        try {
            //将截图拷贝到指定文件中
            FileUtils.copyFile(screenshot, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFile;
    }
}
