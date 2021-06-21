package com.webauto.utils;


import com.webauto.base.Base;
import com.webauto.pojo.Page;
import com.webauto.pojo.UIElement;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UILibraryUtil {
    public static List<Page> pages = new ArrayList<>();

    static {
        loadPages(PropertiesUtil.getPagesElementFilePath("uiLibrary.path"));
    }
    /**
     * 根据页面关键字和元素关键字来完成元素定位
     * @param pageKeyWord
     * @param elemntKeyWord
     * @return WebElement
     */
    public static WebElement getElementByKeyWord(String pageKeyWord,String elemntKeyWord){
        WebElement element=null;
        for (Page page : pages) {
            //根据页面关键字过滤得到要找的页面对象
            if (pageKeyWord.equals(page.getKeyword())){
                List<UIElement> uiElements = page.getUiElements();
                for (UIElement uiElement : uiElements) {
                    //根据元素关键字过滤得到要找的页面元素对象
                    if (elemntKeyWord.equals(uiElement.getKeyword())){
                        //获取元素选择器信息
                        String by = uiElement.getBy();
                        String value = uiElement.getValue();
                        element=getVisibleElement(by,value);
                    }
                }
            }
        }

        return element;
    }

    /**
     *根据定位定位元素方式和值，显示定位元素并返回元素
     * @param by
     * @param value
     */
    private static WebElement getVisibleElement(String by, String value) {
        WebDriverWait wait = new WebDriverWait(Base.driver,10);
        By locator=null;
        WebElement element=null;
        if ("id".equalsIgnoreCase(by)){
            locator = By.id(value);
        }else if ("name".equalsIgnoreCase(by)){
            locator = By.name(value);
        } else if ("className".equalsIgnoreCase(by)){
            locator = By.className(value);
        } else if ("tagName".equalsIgnoreCase(by)){
            locator = By.tagName(value);
        } else if ("linkText".equalsIgnoreCase(by)){
            locator = By.linkText(value);
        } else if ("partialLinkText".equalsIgnoreCase(by)){
            locator = By.partialLinkText(value);
        } else if ("cssSelector".equalsIgnoreCase(by)){
            locator = By.cssSelector(value);
        } else if ("xpath".equalsIgnoreCase(by)){
            locator = By.xpath(value);
        }else {
            System.out.println("暂时不支持：【"+by+"】");
        }
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            if (e instanceof TimeoutException){
                System.out.println("根据by:【"+by+"】，value【"+value+"】定位元素超时");
            }else {
                e.printStackTrace();
            }
        }
        return element;
    }

    /**
     * 解析ui库
     * @param uiLibraryPath
     */
    private static void loadPages(String uiLibraryPath){
        //解析xml
        //获取解析器
        SAXReader reader = new SAXReader();

        InputStream in ;

        try {
            in = new FileInputStream(new File(uiLibraryPath));
            //拿到Document对象
            Document document = reader.read(in);
            //获取根元素
            Element rootElement = document.getRootElement();
            //遍历子元素，完成对象封装
            List<Element> pagesElement = rootElement.elements("Page");
            for (Element pageElement : pagesElement) {
                String pageKeyword = pageElement.attributeValue("keyword");
                //获取<Page>元素的子元素<UIElement>,保存到uiElements集合
                List<Element> uiElements = pageElement.elements("UIElement");
                //将每个<UIElement>元素解析封装成UIElement类型对象，再保存uiElementsList集合中
                List<UIElement> uiElementsList= new ArrayList<>();
                //循环处理
                for (Element uiElement : uiElements) {
                    String uiElementKeyWord= uiElement.attributeValue("keyword");
                    String by= uiElement.attributeValue("by");
                    String value= uiElement.attributeValue("value");
                    UIElement uiEle=new UIElement(uiElementKeyWord,by,value);
                    uiElementsList.add(uiEle);
                }
                Page page = new Page(pageKeyword,uiElementsList);
                pages.add(page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
