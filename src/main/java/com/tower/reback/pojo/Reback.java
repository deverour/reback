package com.tower.reback.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@ToString
@Getter
@Setter
public class Reback {
    private String id;
    private String fengongsi;
    private String quyu;
    private String zhangqi;
    private String yunyingshang;
    private String huikuanbianhao;
    private String jiesuanjine;
    private String issaomiao;
    private String ishuikuan;
    private String huikuanriqi;
    private String shangchuanriqi;
    private String saomiaoname;
    private String iscpy;

 /*   private String quyus;
    private String kehus;
    private String zhangtimes;
    private String huikuantimes;
    private String issaomiaos;
    private String ishuikuans;
    private String msg;
    private String marked;
    private String startDate;
    private String endDate;*/

    public static ArrayList<String> getNamelist() {
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