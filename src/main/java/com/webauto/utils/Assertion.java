package com.webauto.utils;

import com.webauto.base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * 断言类，支持多个场景的断言
 */
public class Assertion {
    /**
     * 判断当前页面url是否指定内容
     * @param urlContains 要匹配的内容
     */
    public static void assertUrlContains(String urlContains){
        WebDriverWait wait = new WebDriverWait(Base.driver,30);
        boolean isDirectedToLogin=true;
        try {
            wait.until(ExpectedConditions.urlContains(urlContains));
        } catch (Exception e) {
            isDirectedToLogin=false;
        }
        Assert.assertTrue(isDirectedToLogin);
    }

    /**
     *断言文本值出现在页面的指定元素
     * @param element
     * @param text
     */
    public static void assertTextPresentInElement(WebElement element,String text){
        WebDriverWait wait = new WebDriverWait(Base.driver,30);
        boolean textToBePresentInElement=true;
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element,text));
        } catch (Exception e) {
            textToBePresentInElement=false;
        }
        Assert.assertTrue(textToBePresentInElement);
    }

    /**
     *断言文在页面的指定元素是可点击的
     * @param element
     * @param text
     */
    public static void assertElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Base.driver,30);
        boolean elementToBeClickable=true;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            elementToBeClickable=false;
        }
        Assert.assertTrue(elementToBeClickable);
    }
}
