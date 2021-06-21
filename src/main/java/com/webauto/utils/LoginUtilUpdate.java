package com.webauto.utils;

import com.webauto.pojo.LoginData;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LoginUtil {

    public static List<LoginData> loginDatas = new ArrayList();

    static {
        //将所有数据解析封装到cases集合对象中
        List<LoginData> list = ExcelUtils.load(PropertiesUtil.getCaseFilePath("login.excel.path"), "用例", LoginData.class);
        loginDatas.addAll(list);
//        for (LoginData loginData : loginDatas) {
//            System.out.println(loginData);
//        }
    }

    /**
     * 获取指定编号的数据
     * @param flag
     * @param cellNames
     * @return
     */
    public static Object[][] getDatas(String flag,String[] cellNames) {
        List<LoginData> satisfied = new ArrayList();
        for (LoginData loginData : loginDatas) {
            if (flag.equals(loginData.getIsNegative())) {
                satisfied.add(loginData);
            }
        }

        Class<LoginData> clazz = LoginData.class;
        Object[][] datas = new Object[satisfied.size()][cellNames.length];
        for (int i = 0; i < satisfied.size(); i++) {
            LoginData ld = satisfied.get(i);
            for (int j = 0; j < cellNames.length; j++) {
                //要反射的方法
                String methodName = "get" + cellNames[j];
                Method method = null;
                try {
                    method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(ld);
                    datas[i][j] = value;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return datas;
    }

    public static void main(String[] args) {
        String[] cellNames = {"Mobilephone", "Password", "ErrorMsg"};
        Object[][] datas = getDatas("1",cellNames);
        for (Object[] data : datas) {
            for (Object datum : data) {
                System.out.print("【" + datum + "】");
            }
            System.out.println();
        }
    }
}
