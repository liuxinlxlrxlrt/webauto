package com.webauto.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class SpecialElementWindow {
    private static ChromeDriver chromeDriver;

    public static void main(String[] args) throws InterruptedException {

        String url = "https://www.baidu.com/";
        openChrom(url);

        chromeDriver.findElement(By.id("kw")).sendKeys("腾讯课堂");

        chromeDriver.findElement(By.id("su")).click();
        System.out.println(chromeDriver.getTitle());
        String firstTitle = chromeDriver.getTitle();
        String firstWindowHandle = chromeDriver.getWindowHandle();
        //显示等待
//        Thread.sleep(3000);

//        <a data-click="{
//            'F':'778317EA',
//                    'F1':'9D73F1E4',
//                    'F2':'4CA6DD6B',
//                    'F3':'54E5243F',
//                    'T':'1616850062',
//                    'y':'BFBEFDFF'
//                    ,'rsv_gwlink':'1'
//        }" href="https://www.baidu.com/link?url=WZOnmDnP0K0o7jY0gkPv-fFFlveCjwE4fsZMoFvqg5_&amp;wd=&amp;eqid=81d8bf0d00217d2300000004605f2c8e"
//            // target="_blank"><em>腾讯课堂</em>_职业培训、考试提升在线教育平台</a>

        //显示等待
        WebDriverWait wait = new WebDriverWait(chromeDriver,5000);
        WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='_职业培训、考试提升在线教育平台']")));

        chromeDriver.findElement(By.xpath("//a[text()='_职业培训、考试提升在线教育平台']")).click();

        Thread.sleep(5000);
        //切换方式：要传入要操作的窗口的name或者句柄
        System.out.println(chromeDriver.getWindowHandles());
        Set<String> windowHandles = chromeDriver.getWindowHandles();
        for (String handle:windowHandles) {
            if (handle.equals(firstWindowHandle)) {
                //根据句柄切换窗口
                chromeDriver.switchTo().window(handle);
                if (chromeDriver.getTitle().equals(firstTitle)) {
                    //如果标题匹配
                    System.out.println("切换成功");
                    break;
                }
            }
        }
    }

    public static void openChrom(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        chromeDriver = new ChromeDriver();

        chromeDriver.get(url);
    }
}
