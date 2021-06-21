package com.webauto.methods;

import com.webauto.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 显示等待
 *1、页面元素在页面存在并且课件
 *  showwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
 *
 *2、页面元素师傅在页面上可用和可被单击
 *  showwait.until(ExpectedConditions.elementToBeClickable(By.xpath("")));
 *
 * 3、页面元素处于被选中状态
 *  showwait.until(ExpectedConditions.elementToBeSelected(By.xpath("")));
 *
 *4、页面元素中是否包含特定的文本
 *  showwait.until(ExpectedConditions.textToBePresentInElement(By.xpath("")));
 *
 *5、页面元素在页面中是否存在
 * showwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));
 */
public class ShowWait extends Base {


    /**
     * 判断元素是否出现，只要有一个元素出现，就通过。（代表在不代表可见）
     * 判断是否至少有 1 个元素存在于 dom 树中。举个例子，
     * 如果页面上有 n 个元素的 class 都是’column-md-3’，
     * 那么只要有 1 个元素存在，这个方法就返回 True。
     * 不保证是否可见
     * @param by
     * @return
     */
    public WebElement getPresenceElement(By by){
        WebDriverWait wait = new WebDriverWait(driver,30);
        try {
            WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return webElement;
        } catch (Exception e) {
            System.out.println("元素定位超时");
            return null;
        }
    }

    /**
     * 判断某个元素中是否可见并且是enable的，这样的话才叫clickable；
     * @param by
     * @return
     */
    public WebElement getClickableElement(By by){
        WebDriverWait wait = new WebDriverWait(driver,30);
        try {
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(by));
            return webElement;
        } catch (Exception e) {
            System.out.println("元素定位超时");
            return null;
        }
    }

    /**
     * 获取一个可见的元素
     * @param by
     * @return
     */
    public WebElement getVisableElement(By by){
        WebDriverWait wait = new WebDriverWait(driver,30);
        try {
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return webElement;
        } catch (Exception e) {
            System.out.println("元素定位超时");
            return null;
        }
    }

    /**
     * 当元素加载完毕时才去获取元素
     * @param by
     * @return
     */
    public WebElement getElementWhilePageIsReady(By by){
        WebDriverWait wait = new WebDriverWait(driver,30);
        try {
            String jsToBeExecute ="return document.readySate == 'complete'";
            boolean isReady= (boolean) wait.until(ExpectedConditions.jsReturnsValue(jsToBeExecute));
            if (isReady){
                return getVisableElement(by);
            }
        } catch (Exception e) {
            System.out.println("元素定位超时");
            e.printStackTrace();

        }
        return null;
    }
}
