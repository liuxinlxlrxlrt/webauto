package com.webauto.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    /**
     * 读取jdbc.properties文件
     */
    public static Properties properties =new Properties();


    static {
        try {
            InputStream inputStream =PropertiesUtil.class.getClassLoader().getResourceAsStream("Properties/excelconfig.properties");
                properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用例excel文件路径
     * @param key
     * @return
     */
    public static String getCaseFilePath(String key){
        return properties.getProperty(key);
    }

    /**
     * 获取指定key的信息
     * @param key
     * @return
     */
    public static String getPageUrlPath(String key){
        return properties.getProperty(key);
    }

    /**
     * 获取页面元素的xml文件路径
     * @param key
     * @return
     */
    public static String getPagesElementFilePath(String key){
        return properties.getProperty(key);
    }

}
