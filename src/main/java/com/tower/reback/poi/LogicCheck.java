package com.tower.reback.poi;

import com.tower.reback.entity.ExcelColumns;
import com.tower.reback.entity.Group;
import com.tower.reback.entity.Result;
import com.tower.reback.pojo.User;
import com.tower.reback.utils.Utils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogicCheck {



    public static Result billCheck(List<List<String>> bills, User user, Set<String> paynumberSet, Set<String> huikuanbianhaoSet){

        HashMap<String,String> map = new HashMap<String,String>();
        HashSet<String> quyuSet = Group.getBranchmap().get(user.getGroup());
        String message="";
        double total=0.0;
        //System.out.println("bills.size():"+bills.size());


        String oldkaipiaobianhao;
        String oldkey="";

        if (bills.get(0).size()< ExcelColumns.INDEX_BILL_KAIPIAOBIANHAO+1){
            message=message+"第【"+(bills.get(0).size()+1)+"】列不能为空\n";
            //map.put("msg",message);
            return new Result(false,message);
        }else {
            oldkaipiaobianhao=bills.get(0).get(ExcelColumns.INDEX_BILL_KAIPIAOBIANHAO);
        }
        int col=2;
        for(List<String> bill:bills){


            if (bill.size()< ExcelColumns.INDEX_BILL_KAIPIAOBIANHAO+1){
                message=message+"第【"+(bill.size()+1)+"】列不能为空\n";
            }else{

                //KEY
                String newkey=bill.get(ExcelColumns.INDEX_BILL_ZHANZHIBIANMA)+bill.get(ExcelColumns.INDEX_BILL_DIANBIAOBIANMA)+bill.get(ExcelColumns.INDEX_BILL_ZHONGQI);
                if (newkey.equals(oldkey)){
                    message=message+"请以电费签认表表二为数据来源制表，以免后期取数导致电量翻倍（电费签认表表一为系统分摊出账，直供电按票据数量会出两条电量一样的明细），目前三家运营商电费签认表均有新模板（类似电信）\n";
                }else {
                    oldkey=newkey;
                }

                //区域
                String quyu=bill.get(ExcelColumns.INDEX_BILL_QUYU);
                if (!Group.AreaSet.contains(quyu)){
                    message=message+"【区域错误】,请参导入模板表二限定字段\n";
                }else if (!quyuSet.contains(quyu)){

                    message=message+"【区域错误】,本账号没有录入【"+quyu+"】区域的权限\n";
                }

                //支付单号
                String zhifudanhao=bill.get(ExcelColumns.INDEX_BILL_ZHIFUDANHAO);
                if (!paynumberSet.contains(zhifudanhao)){

                    message=message+ "【支付单号】不存在\n";
                }

                //站址编码
                if (!NumberUtils.isNumber(bill.get(ExcelColumns.INDEX_BILL_ZHANZHIBIANMA))){

                    message=message+"【站址编码】错误,请检查是否有空格或非数字\n";
                }else if (bill.get(ExcelColumns.INDEX_BILL_ZHANZHIBIANMA).contains(".")){
                    message=message+"【站址编码】错误,请检查是否有空格或非数字\n";
                }
                boolean iszhigong=false;

                //电表编码
                String dianbiaobianma=bill.get(ExcelColumns.INDEX_BILL_DIANBIAOBIANMA);
                if (dianbiaobianma==null){
                    message=message+ "【电表编码】不能为空\n";
                }

                //电表倍率
                String dianbiaobeilv=bill.get(ExcelColumns.INDEX_BILL_DIANBIAOBEILV);
                if (dianbiaobeilv==null){
                    message=message+ "【电表倍率】不能为空\n";
                }

                //是否为直供电
                String shifouzhigongdian=bill.get(ExcelColumns.INDEX_BILL_SHIFOUZHIGONGDIAN);
                if (shifouzhigongdian==null){

                    message=message+ "【是否直供电】不能为空\n";

                }else if (shifouzhigongdian.equals("是")){
                    iszhigong=true;
                }else if (!shifouzhigongdian.equals("否") ){
                    message=message+"【是否为直供电】错误,只能为'是'或者'否'\n";
                }

                //户号
                String huhao = bill.get(ExcelColumns.INDEX_BILL_HUHAO);
                if (iszhigong ){
                    //System.out.println(x);
                    //System.out.println("直供电");
                    //System.out.println(bill.get(BillExcelInfo.INDEX_HUHAO));
                    if (huhao==null){
                        message=message+"【户号错误】,直供电户号不能为空\n";
                        //System.out.println("户号错误,直供电户号不能为空  ");
                    }else if (!NumberUtils.isNumber(huhao)){
                        message=message+"【户号错误】,请检查是否有空格或非数字\n";
                    }
                    else if (huhao.contains(".")){
                        message=message+"【户号错误】,请检查是否有空格或非数字\n";
                    }else if(huhao.length()>10){
                        message=message+"【户号错误】,直供电户号不能大于10位，转供电户号为空\n";
                    }
                }

                //始期、终期
                String shiqi=bill.get(ExcelColumns.INDEX_BILL_SHIQI);
                String zhongqi=bill.get(ExcelColumns.INDEX_BILL_ZHONGQI);
                if (!NumberUtils.isNumber(shiqi) ||!NumberUtils.isNumber(zhongqi)){

                    message=message+"【起止时间】错误,请检查是否为时间格式(筛选时，为可缩进状态)\n";
                }else if(shiqi.contains(".")|| zhongqi.contains(".")){
                    message=message+"【起止时间】错误,请检查是否为时间格式(筛选时，为可缩进状态)\n";
                }else if (Integer.parseInt(shiqi)<40179 ||  Integer.parseInt(shiqi)>47483){
                    message=message+"【始期】错误】,时间范围异常，请检查时间值是否在正确的范围\n";
                }else if (Integer.parseInt(zhongqi)<40179 ||  Integer.parseInt(zhongqi)>47483){
                    message=message+"【终期】错误】,时间范围异常，请检查时间值是否在正确的范围\n";
                }else if (Integer.parseInt(shiqi)>Integer.parseInt(zhongqi)){
                    message=message+"【始期】不应大于【终期】\n";
                }


                //起度、止度、电损、电量、垫资总额、
                if (!NumberUtils.isNumber(bill.get(ExcelColumns.INDEX_BILL_QIDU))){
                    message=message+"【起度】错误,请检查是否有空格或非数字\n";
                }
                if (!NumberUtils.isNumber(bill.get(ExcelColumns.INDEX_BILL_ZHIDU))){
                    message=message+"【止度】错误,请检查是否有空格或非数字\n";
                }
                if (!NumberUtils.isNumber(bill.get(ExcelColumns.INDEX_BILL_DIANSUN))){
                    message=message+"【电损】错误,请检查是否有空格或非数字\n";
                }
                if (!NumberUtils.isNumber(bill.get(ExcelColumns.INDEX_BILL_DIANLIANG))){
                    message=message+"【电量】错误,请检查是否有空格或非数字\n";
                }
                if (!NumberUtils.isNumber(bill.get(ExcelColumns.INDEX_BILL_DIANZIZONGE))){
                    message=message+"【垫资总额】错误,请检查是否有空格或非数字\n";
                }
                //共享运营商
                //System.out.println("共享运营商:"+bill.get(BillExcelInfo.INDEX_GONGXIANGYUNYINGSHANG));
                if (!Group.ShareCustomerSet.contains(bill.get(ExcelColumns.INDEX_BILL_GONGXIANGYUNYINGSHANG))){
                    message=message+"【共享运营商】错误,请参导入模板表二限定字段\n";
                }

                //分摊比例
                String fentanbili=bill.get(ExcelColumns.INDEX_BILL_FENTANBILI);
                if (!NumberUtils.isNumber(fentanbili)){
                    message=message+"【分摊比例】错误,请检查是否大于0且小于等于100%\n";
                }else if (Double.parseDouble(fentanbili)<=0 && Double.parseDouble(fentanbili)>1){
                    message=message+"【分摊比例】错误,请检查是否大于0且小于等于100%\n";
                }
                //结算金额
                String jiesuanjine=bill.get(ExcelColumns.INDEX_BILL_JIESUANJINE);
                if (!NumberUtils.isNumber(jiesuanjine)){
                    message=message+"【结算金额】错误,请检查是否有空格或非数字\n";
                }else {

                    total=total+NumberUtils.toDouble(Utils.to2Round(jiesuanjine));

                }

                //账期
                String zhangqi=bill.get(ExcelColumns.INDEX_BILL_ZHANGQI);
                if (!NumberUtils.isNumber(zhangqi)){
                    message=message+"【账期】错误,请检查是否有空格或非数字\n";
                }else if (zhangqi.contains(".")){
                    message=message+"【账期】错误,请检查是否有空格或非数字\n";
                }else if(zhangqi.length()!=6){
                    message=message+"【账期】错误,请检查长度是否为6位\n";
                    //System.out.println(zhangqi.length());
                }else if(Integer.parseInt(zhangqi)>202212 || Integer.parseInt(zhangqi)<201401){
                    message=message+"【账期】错误,请检查是否过大或者过小\n";

                }else if(Integer.parseInt(zhangqi.substring(4))>12 ){
                    message=message+"【账期月份】错误,不应大于12\n";
                }else if(zhangqi.substring(4).equals("00") ){
                    message=message+"【账期月份】错误,不应等于00\n";
                }
                //结算运营商
                String jiesuanyunyingshang=bill.get(ExcelColumns.INDEX_BILL_JIESUANYUNYINGSHANG);
                if (!Group.CustomerSet.contains(jiesuanyunyingshang)){
                    message=message+"【结算运营商】错误,请参导入模板表二限定字段\n";
                }else if (!bill.get(ExcelColumns.INDEX_BILL_GONGXIANGYUNYINGSHANG).contains(jiesuanyunyingshang)){
                    message=message+"【共享运营商】应包含【结算运营商】\n";
                }
                //开票时间
                String kaipiaoshijian=bill.get(ExcelColumns.INDEX_BILL_KAIPIAOSHIJIAN);
                if (!NumberUtils.isNumber(kaipiaoshijian)){
                    message=message+"【制表时间】错误,请检查是否有空格或非数字\n";
                }else if (kaipiaoshijian.contains(".")){
                    message=message+"【制表时间】错误,请检查是否有空格或非数字\n";
                }else if(kaipiaoshijian.length()!=8){
                    message=message+"【制表时间】错误,请检查长度是否为8位\n";
                    //System.out.println(kaipiaoshijian.length());
                }else if(Integer.parseInt(kaipiaoshijian)>20221231 || Integer.parseInt(kaipiaoshijian)<20140101){
                    message=message+"【制表时间】错误,请检查是否过大或者过小\n";

                }else{
                    int y=Integer.parseInt(kaipiaoshijian.substring(0,4));
                    int m=Integer.parseInt(kaipiaoshijian.substring(4,6));
                    int d=Integer.parseInt(kaipiaoshijian.substring(6));
                    if (y > 2022 || y < 2014){
                        message=message+"【制表时间】错误,请检查是否过大或者过小\n";
                    }else if (m>12){
                        message=message+"【制表时间】月份错误,不应大于12\n";
                    }else if (d>31){
                        message=message+"【制表时间】号数错误,不应大于31\n";
                    }
                }

                //开票编号
                String kaipiaobianhao=bill.get(ExcelColumns.INDEX_BILL_KAIPIAOBIANHAO);
                if (huikuanbianhaoSet.contains(kaipiaobianhao)){
                    message=message+"【回款编号】系统已存在,请检查本次明细是否已导入\n";
                }
                if(!oldkaipiaobianhao.equals(kaipiaobianhao)){
                    message=message+"【回款编号】错误,同一导入表回款编号应当一致\n";
                }else{
                    String[] kaipiaobianhaoStr=kaipiaobianhao.split("-");
    //                for (String s:kaipiaobianhaoStr){
    //                    System.out.println(s);
    //
    //                }
                    if(kaipiaobianhaoStr.length!=4){
                        message=message+"【回款编号】格式错误\n";
                    }else if (!kaipiaobianhaoStr[0].equals(quyu)){
                        message=message+"【回款编号】错误,第一部分应等于区域\n";
                    }else if(!kaipiaobianhaoStr[1].equals(jiesuanyunyingshang)){
                        message=message+"【回款编号】错误,第二部分应等于结算运营商\n";
                    }else if (!kaipiaobianhaoStr[2].equals(kaipiaoshijian)){
                        message=message+"【回款编号】错误,第三部分应等于制表时间\n";
                    }else if(kaipiaobianhaoStr[3].length()!=3){
                        message=message+"【回款编号】错误,第四部分应为三位数字\n";
                    }
                }
                //System.out.println("message长度："+message.length());
                if (message.length()>0){
                    message="第【"+col+"】行：\n"+message;
                    map.put("msg",message);
                    return new Result(false,message);
                }else {
                    col++;
                }
            }


        }
        //System.out.println(message);
        String totalStr =String.valueOf(total);

      /*  map.put("msg",message);
        map.put("total",Utils.to2Round(totalStr));*/
        return new Result(true,Utils.to2Round(totalStr));

    }

/*    public static HashMap<String,String> cpysCheck(List<List<String>> cyps, User user,Set<String> huikuanbianhaoSet){
        HashMap<String,String> map = new HashMap<String,String>();
        HashSet<String> quyuSet = Group.getGroupMap().get(user.getGroup());
        String message="";
        double total=0.0;



        String oldkaipiaobianhao;
        //System.out.println("cyps.get(0).size():"+cyps.get(0).size());
        if (cyps.get(0).size()< CpyTitle.INDEX_KAIPIAOBIANHAO+1){
            message=message+"第【"+(cyps.get(0).size()+1)+"】列不能为空\n";
            map.put("msg",message);
            return map;
        }else {
            oldkaipiaobianhao=cyps.get(0).get(CpyTitle.INDEX_KAIPIAOBIANHAO);
        }
        int col=2;
        for(List<String> cyp:cyps){


            if (cyp.size()<CpyTitle.INDEX_KAIPIAOBIANHAO+1){
                message=message+"第【"+(cyp.size()+1)+"】列不能为空\n";
            }else{



                //区域
                String quyu=cyp.get(CpyTitle.INDEX_QUYU);
                if (!BillMessage.quyuSet.contains(quyu)){
                    message=message+"【区域错误】,请参导入模板表二限定字段\n";
                }else if (!quyuSet.contains(quyu)){

                    message=message+"【区域错误】,本账号没有录入【"+quyu+"】区域的权限\n";
                }


                //站址编码
                if (!NumberUtils.isNumber(cyp.get(CpyTitle.INDEX_ZHANZHIBIANMA))){

                    message=message+"【站址编码】错误,请检查是否有空格或非数字\n";
                }else if (cyp.get(CpyTitle.INDEX_ZHANZHIBIANMA).contains(".")){
                    message=message+"【站址编码】错误,请检查是否有空格或非数字\n";
                }
                boolean iszhigong=false;



                //站址名称
                String zhanzhimingchen=cyp.get(CpyTitle.INDEX_ZHANZHIMINGCHEN);
                if (zhanzhimingchen==null){
                    message=message+ "【站点名称】不能为空\n";
                }

                //共享方式
                String gongxiangfangshi=cyp.get(CpyTitle.INDEX_GONGXIANGFANGSHI);
                if (gongxiangfangshi==null){

                    message=message+ "【共享方式】不能为空\n";

                }else if (gongxiangfangshi.equals("独享")){

                }else if (!gongxiangfangshi.equals("共享") ){
                    message=message+"【共享方式】错误,只能为'独享'或者'共享'\n";
                }

                //是否为直供电
                String shifouzhigongdian=cyp.get(CpyTitle.INDEX_SHIFOUZHIGONGDIAN);
                if (shifouzhigongdian==null){

                    message=message+ "【是否直供电】不能为空\n";

                }else if (shifouzhigongdian.equals("是")){
                    iszhigong=true;
                }else if (!shifouzhigongdian.equals("否") ){
                    message=message+"【是否为直供电】错误,只能为'是'或者'否'\n";
                }



                //始期、终期
                String shiqi=cyp.get(CpyTitle.INDEX_SHIQI);
                String zhongqi=cyp.get(CpyTitle.INDEX_ZHONGQI);
                if (!NumberUtils.isNumber(shiqi) ||!NumberUtils.isNumber(zhongqi)){

                    message=message+"【起止时间】错误,请检查是否为时间格式(筛选时，为可缩进状态)\n";
                }else if(shiqi.contains(".")|| zhongqi.contains(".")){
                    message=message+"【起止时间】错误,请检查是否为时间格式(筛选时，为可缩进状态)\n";
                }else if (Integer.parseInt(shiqi)<40179 ||  Integer.parseInt(shiqi)>47483){
                    message=message+"【始期】错误】,时间范围异常，请检查时间值是否在正确的范围\n";
                }else if (Integer.parseInt(zhongqi)<40179 ||  Integer.parseInt(zhongqi)>47483){
                    message=message+"【终期】错误】,时间范围异常，请检查时间值是否在正确的范围\n";
                }else if (Integer.parseInt(shiqi)>Integer.parseInt(zhongqi)){
                    message=message+"【始期】不应大于【终期】\n";
                }


                //0、1、2、3、4、
                if (!NumberUtils.isNumber(cyp.get(CpyTitle.INDEX_DIANJIA))){
                    message=message+"【电价】错误,请检查是否有空格或非数字\n";
                }
                if (!NumberUtils.isNumber(cyp.get(CpyTitle.INDEX_JIZHUNNIANJIA))){
                    message=message+"【基准包干年价】错误,请检查是否有空格或非数字\n";
                }
                if (!NumberUtils.isNumber(cyp.get(CpyTitle.INDEX_ONE))){
                    message=message+"【第一年度包干年价】错误,请检查是否有空格或非数字\n";
                }
                if (!NumberUtils.isNumber(cyp.get(CpyTitle.INDEX_TWO))){
                    message=message+"【第二年度包干年价】错误,请检查是否有空格或非数字\n";
                }
                if (!NumberUtils.isNumber(cyp.get(CpyTitle.INDEX_THREE))){
                    message=message+"【第三年度包干年价】错误,请检查是否有空格或非数字\n";
                }
                if (!NumberUtils.isNumber(cyp.get(CpyTitle.INDEX_CHUZHANGJINE))){
                    message=message+"【出账金额】错误,请检查是否有空格或非数字\n";
                }
                if (!NumberUtils.isNumber(cyp.get(CpyTitle.INDEX_TIAOZHANGJINE))){
                    message=message+"【调账金额】错误,请检查是否有空格或非数字\n";
                }



                //结算金额
                String jiesuanjine=cyp.get(CpyTitle.INDEX_JIESUANJINE);
                if (!NumberUtils.isNumber(jiesuanjine)){
                    message=message+"【结算金额】错误,请检查是否有空格或非数字\n";
                }else {

                    total=total+NumberUtils.toDouble(Utils.to2Round(jiesuanjine));
                }

                //账期
                String zhangqi=cyp.get(CpyTitle.INDEX_ZHANGQI);
                if (!NumberUtils.isNumber(zhangqi)){
                    message=message+"【账期】错误,请检查是否有空格或非数字\n";
                }else if (zhangqi.contains(".")){
                    message=message+"【账期】错误,请检查是否有空格或非数字\n";
                }else if(zhangqi.length()!=6){
                    message=message+"【账期】错误,请检查长度是否为6位\n";
                    //System.out.println(zhangqi.length());
                }else if(Integer.parseInt(zhangqi)>202212 || Integer.parseInt(zhangqi)<201401){
                    message=message+"【账期】错误,请检查是否过大或者过小\n";

                }else if(Integer.parseInt(zhangqi.substring(4))>12 ){
                    message=message+"【账期月份】错误,不应大于12\n";
                }else if(zhangqi.substring(4).equals("00") ){
                    message=message+"【账期月份】错误,不应等于00\n";
                }
                //结算运营商
                String jiesuanyunyingshang=cyp.get(CpyTitle.INDEX_JIESUANYUNYINGSHANG);
                if (!BillMessage.yunyingshangSet.contains(jiesuanyunyingshang)){
                    message=message+"【结算运营商】错误,请参导入模板表二限定字段\n";
                }


                //制表时间
                String kaipiaoshijian=cyp.get(CpyTitle.INDEX_KAIPIAOSHIJIAN);
                if (!NumberUtils.isNumber(kaipiaoshijian)){
                    message=message+"【制表时间】错误,请检查是否有空格或非数字\n";
                }else if (kaipiaoshijian.contains(".")){
                    message=message+"【制表时间】错误,请检查是否有空格或非数字\n";
                }else if(kaipiaoshijian.length()!=8){
                    message=message+"【制表时间】错误,请检查长度是否为8位\n";
                    //System.out.println(kaipiaoshijian.length());
                }else if(Integer.parseInt(kaipiaoshijian)>20221231 || Integer.parseInt(kaipiaoshijian)<20140101){
                    message=message+"【制表时间】错误,请检查是否过大或者过小\n";

                }else{
                    int y=Integer.parseInt(kaipiaoshijian.substring(0,4));
                    int m=Integer.parseInt(kaipiaoshijian.substring(4,6));
                    int d=Integer.parseInt(kaipiaoshijian.substring(6));
                    if (y > 2022 || y < 2014){
                        message=message+"【制表时间】错误,请检查是否过大或者过小\n";
                    }else if (m>12){
                        message=message+"【制表时间】月份错误,不应大于12\n";
                    }else if (d>31){
                        message=message+"【制表时间】号数错误,不应大于31\n";
                    }
                }

                //开票编号
                String kaipiaobianhao=cyp.get(CpyTitle.INDEX_KAIPIAOBIANHAO);
                if (huikuanbianhaoSet.contains(kaipiaobianhao)){
                    message=message+"【回款编号】系统已存在,请检查本次明细是否已导入\n";
                }
                if(!oldkaipiaobianhao.equals(kaipiaobianhao)){
                    message=message+"【回款编号】错误,同一导入表开票编号应当一致\n";
                }else{
                    String[] kaipiaobianhaoStr=kaipiaobianhao.split("-");
                    //                for (String s:kaipiaobianhaoStr){
                    //                    System.out.println(s);
                    //
                    //                }
                    if(kaipiaobianhaoStr.length!=4){
                        message=message+"【回款编号】格式错误\n";
                    }else if (!kaipiaobianhaoStr[0].equals(quyu)){
                        message=message+"【回款编号】错误,第一部分应等于区域\n";
                    }else if(!kaipiaobianhaoStr[1].equals(jiesuanyunyingshang)){
                        message=message+"【回款编号】错误,第二部分应等于结算运营商\n";
                    }else if (!kaipiaobianhaoStr[2].equals(kaipiaoshijian)){
                        message=message+"【回款编号】错误,第三部分应等于制表时间\n";
                    }else if(kaipiaobianhaoStr[3].length()!=3){
                        message=message+"【回款编号】错误,第四部分应为三位数字\n";
                    }
                }
                //System.out.println("message长度："+message.length());
                if (message.length()>0){
                    message="第【"+col+"】行：\n"+message;
                    map.put("msg",message);
                    return map;
                }else {
                    col++;
                }
            }


        }
        //System.out.println(message);
        String totalStr =String.valueOf(total);

        map.put("msg",message);
        map.put("total",Utils.to2Round(totalStr));
        return map;

    }*/
}