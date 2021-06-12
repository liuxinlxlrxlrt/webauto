package com.webauto.methods;

import com.webauto.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * 时间控件
 */
public class DatePickerDemo extends Base {

    @Test
    public void test(){
        /**
         * 1、日期控件支持写入日期
         */
        WebElement iframeElement = driver.findElement(By.cssSelector("iframe[src='xxx']"));
        //切换到iframe
        driver.switchTo().frame(iframeElement);
        driver.findElement(By.id("xxx")).sendKeys("xxx");

        /**
         * 2、日期控件“不”支持写入日期
         */
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("var birthElement =document.getElementById('brith');"
                + "birthElement.value='2021-16-12 21:22:23';");

    }
}
