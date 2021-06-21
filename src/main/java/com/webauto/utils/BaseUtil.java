package com.webauto.utils;

import java.lang.reflect.Method;
import java.util.List;

public class BaseUtil {
    public static Object[][] assembleDatas(String[] cellNames, List<Object> satisfied, Class clazz) {
        Object[][] datas = new Object[satisfied.size()][cellNames.length];
        for (int i = 0; i < satisfied.size(); i++) {
            Object object = satisfied.get(i);
            for (int j = 0; j < cellNames.length; j++) {
                //要反射的方法
                String methodName = "get" + cellNames[j];
                Method method;
                try {
                    method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(object);
                    datas[i][j] = value;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }
}
