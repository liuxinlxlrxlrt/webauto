package com.webauto.report;

import com.webauto.utils.ScreenshotUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.util.Date;

/**
 * Custom[ˈkʌstəm]:自定义
 * screenshot[ˈskriːnʃɑːt]:截图
 */
public class CustomListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        String baseDir = "test-output";
//        String baseDir = "target"+File.separator+"surefire-reports";
        String screenshotDir ="screenshot";
        //获取测试上下文-->获取当前正在执行的test测试集-->获取测试集的name
        String testNameDir = tr.getTestContext().getCurrentXmlTest().getName();
        Date date = new Date();
        //yyyy-MM-dd HH:mm:ss
        String dateDir = DateFormatUtils.format(date,"yyyy-MM-dd");
        String fileName = date.getTime()+".jpg";
        //拼接截图文件路径
        String filePath=baseDir+ File.separator+screenshotDir+File.separator
                +testNameDir+File.separator+dateDir+File.separator+fileName;

        System.out.println("路径："+filePath);
        //获取截图
        File file = ScreenshotUtil.saveScreenshot(filePath);
        System.out.println("file:"+file.getAbsolutePath());

        //从testng.xml文件中获取指定name为apacheDocumentRoot的值
        String toBeReplaced = tr.getTestContext().getCurrentXmlTest().getParameter("apacheDocumentRoot");
        System.out.println("toBeReplaced:"+toBeReplaced);
        //从testng.xml文件中获取指定name为host的值
        String replcaement = tr.getTestContext().getCurrentXmlTest().getParameter("host");
        System.out.println("replcaement:"+replcaement);
        //accessPath = http://xxxx
        String imgString= getImgString(toBeReplaced,replcaement,file);
        System.out.println("imgString:"+imgString);

        //写reportng的日志中去
        Reporter.log(imgString);

    }

    /**
     * 获取页面展示图片的html代码
     * @param file
     * @return
     */
    private String getImgString(String toBeReplaced,String replcaement,File file) {
        //获取图片的绝对路径
        String absolutePath=file.getAbsolutePath();
        System.out.println("absolutePath:"+absolutePath);
        //获取替换后的路径
        String accessPath =absolutePath.replace(toBeReplaced,replcaement);
        System.out.println("accessPath:"+accessPath);
        String img="<img src='"+accessPath
                +"' height='100' width='100'><a href='"
                +accessPath+"' target='_blank'>点击查看大图</a></img>";
        return img;
    }

    public static void main(String[] args) {
        Date date = new Date();
        //yyyy-MM-dd HH:mm:ss
        String dateDir = DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
        System.out.println(dateDir);
    }
}
