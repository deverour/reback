package com.tower.reback.utils;

public class MyUtils {
    public static String ExcelDate2String(Long times){
        return String.valueOf(((times+28800000)/86400000)+25569);
    }
}
