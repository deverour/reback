package com.tower.reback.entity;

import java.util.ArrayList;

public class TitleList {
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
    public static ArrayList<String> getRebackTitle() {
        ArrayList<String> namelist = new ArrayList<String>();
        namelist.add("分公司");
        namelist.add("区域");
        namelist.add("账期");
        namelist.add("运营商");
        namelist.add("回款编号");
        namelist.add("结算金额");
        namelist.add("是否上传扫描件");
        namelist.add("是否回款");
        namelist.add("回款日期");
        namelist.add("签认日期");
        namelist.add("是否包干");

        return namelist;
    }
}
