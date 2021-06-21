package com.webauto.utils;

import com.webauto.pojo.LoginData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LoginUtilUpdate {

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
        List<Object> satisfied = new ArrayList();
        for (LoginData loginData : loginDatas) {
            if (flag.equals(loginData.getIsNegative())) {
                satisfied.add(loginData);
            }
        }

        Class clazz = LoginData.class;
        return BaseUtil.assembleDatas(cellNames,satisfied,clazz);
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
