package com.tower.reback.entity;

import com.tower.reback.pojo.Bill;
import com.tower.reback.pojo.Cpy;
import com.tower.reback.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class ExcelColumns {
    public static int INDEX_BILL_QUYU=0;
    public static int INDEX_BILL_ZHIFUDANHAO=1;
    public static int INDEX_BILL_ZHANZHIBIANMA=2;
    public static int INDEX_BILL_DIANBIAOBIANMA=3;
    public static int INDEX_BILL_DIANBIAOBEILV=4;
    public static int INDEX_BILL_SHIFOUZHIGONGDIAN=5;
    public static int INDEX_BILL_HUHAO=6;
    public static int INDEX_BILL_SHIQI=7;
    public static int INDEX_BILL_ZHONGQI=8;
    public static int INDEX_BILL_QIDU=9;
    public static int INDEX_BILL_ZHIDU=10;
    public static int INDEX_BILL_DIANSUN=11;
    public static int INDEX_BILL_DIANLIANG=12;
    public static int INDEX_BILL_DIANZIZONGE=13;
    public static int INDEX_BILL_GONGXIANGYUNYINGSHANG=14;
    public static int INDEX_BILL_FENTANBILI=15;
    public static int INDEX_BILL_JIESUANJINE=16;
    public static int INDEX_BILL_ZHANGQI=17;
    public static int INDEX_BILL_JIESUANYUNYINGSHANG=18;
    public static int INDEX_BILL_KAIPIAOSHIJIAN=19;
    public static int INDEX_BILL_KAIPIAOBIANHAO=20;

    public static int INDEX_CPY_QUYU=0;
    public static int INDEX_CPY_ZHANZHIBIANMA=1;
    public static int INDEX_CPY_ZHANZHIMINGCHEN=2;
    public static int INDEX_CPY_GONGXIANGFANGSHI=3;
    public static int INDEX_CPY_SHIFOUZHIGONGDIAN=4;
    public static int INDEX_CPY_DIANJIA=5;
    public static int INDEX_CPY_JIZHUNNIANJIA=6;
    public static int INDEX_CPY_ONE=7;
    public static int INDEX_CPY_TWO=8;
    public static int INDEX_CPY_THREE=9;
    public static int INDEX_CPY_SHIQI=10;
    public static int INDEX_CPY_ZHONGQI=11;
    public static int INDEX_CPY_CHUZHANGJINE=12;
    public static int INDEX_CPY_TIAOZHANGJINE=13;
    public static int INDEX_CPY_JIESUANJINE=14;
    public static int INDEX_CPY_ZHANGQI=15;
    public static int INDEX_CPY_JIESUANYUNYINGSHANG=16;
    public static int INDEX_CPY_KAIPIAOSHIJIAN=17;
    public static int INDEX_CPY_KAIPIAOBIANHAO=18;

    public static int INDEX_REBACK_QUYU=0;
    public static int INDEX_REBACK_ZHIFUDANHAO=1;
    public static int INDEX_REBACK_ZHANZHIBIANMA=2;
    public static int INDEX_REBACK_DIANBIAOBIANMA=3;
    public static int INDEX_REBACK_DIANBIAOBEILV=4;
    public static int INDEX_REBACK_SHIFOUZHIGONGDIAN=5;
    public static int INDEX_REBACK_HUHAO=6;
    public static int INDEX_REBACK_SHIQI=7;
    public static int INDEX_REBACK_ZHONGQI=8;
    public static int INDEX_REBACK_QIDU=9;
    public static int INDEX_REBACK_ZHIDU=10;
    public static int INDEX_REBACK_DIANSUN=11;
    public static int INDEX_REBACK_DIANLIANG=12;
    public static int INDEX_REBACK_DIANZIZONGE=13;
    public static int INDEX_REBACK_GONGXIANGYUNYINGSHANG=14;
    public static int INDEX_REBACK_FENTANBILI=15;
    public static int INDEX_REBACK_JIESUANJINE=16;
    public static int INDEX_REBACK_ZHANGQI=17;
    public static int INDEX_REBACK_JIESUANYUNYINGSHANG=18;
    public static int INDEX_REBACK_KAIPIAOSHIJIAN=19;
    public static int INDEX_REBACK_KAIPIAOBIANHAO=20;


    public static Bill getBill(List<String> list){
        Bill bill = new Bill();
        bill.setQuyu(list.get(0));
        bill.setZhifudanhao(list.get(1));
        bill.setZhanzhibianma(list.get(2));
        bill.setDianbiaobianma(list.get(3));
        bill.setDianbiaobeilv(list.get(4));
        bill.setShifouzhigongdian(list.get(5));
        bill.setHuhao(list.get(6));
        bill.setShiqi(list.get(7));
        bill.setZhongqi(list.get(8));
        bill.setQidu(MyUtils.to2Round(list.get(9)));
        bill.setZhidu(MyUtils.to2Round(list.get(10)));
        bill.setDiansun(MyUtils.to2Round(list.get(11)));
        bill.setDianliang(MyUtils.to2Round(list.get(12)));
        bill.setDianzizonge(MyUtils.to2Round(list.get(13)));
        bill.setGongxiangyunyingshang(list.get(14));
        bill.setFentanbili(MyUtils.to6Round(list.get(15)));
        bill.setJiesuanjine(MyUtils.to2Round(list.get(16)));
        bill.setZhangqi(list.get(17));
        bill.setJiesuanyunyingshang(list.get(18));
        bill.setKaipiaoshijian(list.get(19));
        bill.setHuikuanbianhao(list.get(20));

        return bill;
    }


    public static Cpy getCpy(List<String> list){
        Cpy cpy = new Cpy();
        cpy.setQuyu(list.get(0));
        cpy.setZhanzhibianma(list.get(1));
        cpy.setZhanzhimingchen(list.get(2));
        cpy.setGongxiangfangshi(list.get(3));
        cpy.setShifouzhigongdian(list.get(4));
        cpy.setDianjia(list.get(5));
        cpy.setJizhunnianjia(MyUtils.to2Round(list.get(6)));
        cpy.setYearone(MyUtils.to2Round(list.get(7)));
        cpy.setYeartwo(MyUtils.to2Round(list.get(8)));
        cpy.setYearthree(MyUtils.to2Round(list.get(9)));
        cpy.setShiqi(list.get(10));
        cpy.setZhongqi(list.get(11));
        cpy.setChuzhangjine(MyUtils.to2Round(list.get(12)));
        cpy.setTiaozhangjine(MyUtils.to2Round(list.get(13)));
        cpy.setJiesuanjine(MyUtils.to6Round(list.get(14)));
        cpy.setZhangqi(list.get(15));
        cpy.setJiesuanyunyingshang(list.get(16));
        cpy.setZhibiaoshijian(list.get(17));
        cpy.setHuikuanbianhao(list.get(18));
        return cpy;
    }

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

    public static ArrayList<String> getCpyTitle() {
        ArrayList<String> namelist = new ArrayList<String>() ;
        namelist.add("结算编号(唯一值)");
        namelist.add("区域");
        namelist.add("站址编码");
        namelist.add("站址名称");
        namelist.add("共享方式");
        namelist.add("是否直供电");
        namelist.add("电价");
        namelist.add("基准包干年价(不含税)");
        namelist.add("第一年度包干年价(不含税)");
        namelist.add("第二年度包干年价(不含税)");
        namelist.add("第三年度包干年价(不含税)");
        namelist.add("包干起始日期");
        namelist.add("包干终止日期");
        namelist.add("当月系统出账金额(不含税)");
        namelist.add("调账金额(不含税)");
        namelist.add("实际结算金额");
        namelist.add("账期");
        namelist.add("结算运营商");
        namelist.add("制表时间");
        namelist.add("回款编号");
        namelist.add("上传日期");
        return namelist;
    }




}
