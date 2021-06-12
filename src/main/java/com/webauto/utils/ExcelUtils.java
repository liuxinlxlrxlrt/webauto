package com.webauto.utils;


import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * poi批量读取数据
 */
public class ExcelUtils {

//    public static String casePath="src/main/resources/case_v9.xls";


    /**
     * 指定excel表单的数据，封装对象
     *
     * @param excelPath excel的相对路径
     * @param sheetName excel表单名
     */
    public static <T> List<T> load(String excelPath, String sheetName, Class<T> clazz) {

        List<T>  list = new ArrayList<>();
        //创建WorkBook对象
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            Sheet sheet = workbook.getSheet(sheetName);
            //获取第一行
            Row titleRow = sheet.getRow(0);
            //获取最后一个列
            int lastCellNum = titleRow.getLastCellNum();
            //数据数组
            String[] fields = new String[lastCellNum];
            //循环处理第一行的title
            for (int i = 0; i < lastCellNum; i++) {
                //获取列索来引获取对应的列
                Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                //设置列的类型为字符串
                cell.setCellType(CellType.STRING);
                //获取列值
                String title = cell.getStringCellValue();
                //截取英文名称（除汉字描述之外）
                if (title.indexOf("(") != -1) {
                    title = title.substring(0, title.indexOf("("));
                }

                fields[i] = title;
            }
            System.out.println("");
            int lastRowIndex = sheet.getLastRowNum();

            //循环遍历标题以外的所有内容，将所有内容保存到用例对象中
            //循环处理每一个数据行
            for (int i = 1; i <= lastRowIndex; i++) {
                //反射获取Case对象
                T obj = clazz.newInstance();
                //获取me一行
                Row dataRow = sheet.getRow(i);
                //如果行为null或者为空就跳过
                if (dataRow == null || isEmptyRow(dataRow)) {
                    continue;
                }
                //遍历每一列，将每一列的内容添加到Case的对中
                for (int j = 0; j < lastCellNum; j++) {
                    //Row.MissingCellPolicy.CREATE_NULL_AS_BLANK枚举类策略
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    //反射获取方法名
                    String methodName = "set" + fields[j];
                    //获取（set标题）方法
                    Method method = clazz.getMethod(methodName, String.class);
                    //通过反射调用并执行（set标题）方法将每行的title对应的数据添加到Case对象中
                    //调用执行方法时，无参只穿Class
                    method.invoke(obj, value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 判断行是否空
     *
     * @param dataRow
     * @return
     */
    private static boolean isEmptyRow(Row dataRow) {
        int lastCellNum = dataRow.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            String value = cell.getStringCellValue();
            if (value != null || value.trim().length() > 0) {
                //不为空返回fasle
                return false;
            }
        }
        //为空返回true
        return true;
    }

//    public static void main(String[] args) {
//        String caasePath = "src/main/resources/case_v8.xls";
//        String sheetName = "接口信息";
//        int[] rows = {2, 3};
//        int[] cells = {1, 4};
//        String url = "";
//        Object[][] datas = ExcelUtils.getDataByArray(caasePath, sheetName, rows, cells);
//        for (Object[] objects : datas) {
//            String apiidFromRest = objects[0].toString();
//            if (apiidFromRest.equals("1")) {
//                url = objects[1].toString();
//
//                break;
//            }
//        }
//        System.out.println(url);
//    }
}
