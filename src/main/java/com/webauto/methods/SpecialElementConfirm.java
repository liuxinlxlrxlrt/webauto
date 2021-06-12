package com.webauto.methods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 模态框：confirm框，不能定位元素
 * 如果div实现的模态框，按照常规元素来处理
 */
public class SpecialElementConfirm {
    private static ChromeDriver chromeDriver;

    public static void main(String[] args) throws InterruptedException {

        String url = "https://www.baidu.com/";
        openChrom(url);

        chromeDriver.findElement(By.id("xxx"));
        Thread.sleep(3000);
        Alert alert = chromeDriver.switchTo().alert();
        //确认
        alert.accept();
        //取消
        alert.dismiss();
        System.out.println(alert.getText());
    }

    public static void openChrom(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        chromeDriver = new ChromeDriver();

        chromeDriver.get(url);
    }
}
