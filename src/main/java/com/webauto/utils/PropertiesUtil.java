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

    public static String getPath(){
        return properties.getProperty("login.excel.path");
    }

}
