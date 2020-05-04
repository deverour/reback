package com.tower.reback.entity;

import java.util.ArrayList;

public class BillExcelInfo {
    public static int INDEX_QUYU=0;
    public static int INDEX_ZHIFUDANHAO=1;
    public static int INDEX_ZHANZHIBIANMA=2;
    public static int INDEX_DIANBIAOBIANMA=3;
    public static int INDEX_DIANBIAOBEILV=4;
    public static int INDEX_SHIFOUZHIGONGDIAN=5;
    public static int INDEX_HUHAO=6;
    public static int INDEX_SHIQI=7;
    public static int INDEX_ZHONGQI=8;
    public static int INDEX_QIDU=9;
    public static int INDEX_ZHIDU=10;
    public static int INDEX_DIANSUN=11;
    public static int INDEX_DIANLIANG=12;
    public static int INDEX_DIANZIZONGE=13;
    public static int INDEX_GONGXIANGYUNYINGSHANG=14;
    public static int INDEX_FENTANBILI=15;
    public static int INDEX_JIESUANJINE=16;
    public static int INDEX_ZHANGQI=17;
    public static int INDEX_JIESUANYUNYINGSHANG=18;
    public static int INDEX_KAIPIAOSHIJIAN=19;
    public static int INDEX_KAIPIAOBIANHAO=20;


    public static ArrayList<String> getBillTitle() {
        ArrayList<String> namelist = new ArrayList<String>() ;
        namelist.add("结算编号(唯一值)");
        namelist.add("区域");
        namelist.add("支付单号");
        namelist.add("站址编码");
        namelist.add("电表编码");
        namelist.add("电表倍率");
        namelist.add("是否直供电");
        namelist.add("户号");
        namelist.add("始期");
        namelist.add("终期");
        namelist.add("起度");
        namelist.add("止度");
        namelist.add("电损");
        namelist.add("电量");
        namelist.add("垫资总额");
        namelist.add("共享运营商");
        namelist.add("分摊比例");
        namelist.add("结算金额");
        namelist.add("账期");
        namelist.add("结算运营商");
        namelist.add("制表时间");
        namelist.add("回款编号");
        namelist.add("上传日期");
        return namelist;
    }
}
