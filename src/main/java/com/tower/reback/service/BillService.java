package com.tower.reback.service;

import com.tower.reback.dao.BillDao;
import com.tower.reback.dao.RebackDao;
import com.tower.reback.entity.*;
import com.tower.reback.poi.ExcelRead;
import com.tower.reback.poi.LogicCheck;
import com.tower.reback.pojo.Bill;
import com.tower.reback.pojo.Reback;
import com.tower.reback.pojo.User;
import com.tower.reback.utils.MathUtil;
import com.tower.reback.utils.MyUtils;
import com.tower.reback.utils.Utils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@Service("BillService")
public class BillService implements InitializingBean {

    public static Set paynumberSet;

    @Autowired
    private BillDao billDao;
    @Autowired
    private RebackDao rebackDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("开始加载支付单号");
        paynumberSet = billDao.getPaynumberSet();
        System.out.println("支付单号缓存完成");


    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})//开启事务
    public Result saveBill(File file, User user){
        Set<String> huikuanbianhaoSet = new HashSet<>();
        Set<String> paynumberSet = new HashSet<>();
        huikuanbianhaoSet = billDao.getHuikuanbianhaoSet();

        try {
            ExcelRead excelRead = new ExcelRead(file.getPath(),2);
            Result result = LogicCheck.billCheck(excelRead.getMyDataList(),user,paynumberSet,huikuanbianhaoSet);
            if (result.isFlag()){
                int index=1;
                double total=0.0;
                int counts=0;
                Bill bill ;
                Reback reback = new Reback();
                String shangchuanriqi = MyUtils.ExcelDate2String(new Date().getTime());
                List<List<String>> lists=excelRead.getMyDataList();
                bill= MathUtil.getBill(lists.get(0));
                HashMap<String,String> branchmap= Group.getAreaMap();
                reback.setFengongsi(branchmap.get(bill.getQuyu()));
                reback.setQuyu(bill.getQuyu());
                reback.setZhangqi(bill.getZhangqi());
                reback.setYunyingshang(bill.getJiesuanyunyingshang());
                reback.setHuikuanbianhao(bill.getHuikuanbianhao());
                reback.setIscpy("否");
                for (List<String> billList:lists){
                    bill= MathUtil.getBill(billList);
                    bill.setShangchuanriqi(shangchuanriqi);
                    total=total+ NumberUtils.toDouble(Utils.to2Round(bill.getJiesuanjine()));
                    counts =billDao.savebill(bill)+counts;
                    index++;
                }
                reback.setJiesuanjine(Utils.to2Round(String.valueOf(total)));
                reback.setIssaomiao("否");
                reback.setIshuikuan("否");
                reback.setHuikuanriqi("");
                reback.setSaomiaoname("");
                reback.setShangchuanriqi(shangchuanriqi);
                rebackDao.saveReback(reback);
                System.out.println("新增签认明细："+counts);
                return new Result(true,"签认明细导入成功,本次导入金额为"+total);
            }

            return LogicCheck.billCheck(excelRead.getMyDataList(),user,paynumberSet,huikuanbianhaoSet);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"读取表格失败,请检查导入表模板后重试");
        }


    }

    public List<Bill> findByCondition(BillQueryBean billQueryBean,User user){
        List<String> areaList = new ArrayList<>();
        if (billQueryBean.getBillBranch()==null){
            HashMap<String, HashSet<String>>  m= new HashMap<>();
            m=Group.getBranchmap();

            //for (String str : m.get(user.getGroup())){
            for (String str : m.get("重庆")){
                areaList.add(str);
            }

        }else if(billQueryBean.getBillArea()==null){
            for (String strBranch : billQueryBean.getBillBranch()){
                for (String strArea : Group.getBranchmap().get(strBranch)){
                    areaList.add(strArea);
                }
            }
        }else {
            areaList = billQueryBean.getBillArea();
        }
        billQueryBean.setBillArea(areaList);

        return billDao.findByCondition(billQueryBean);




    }


    public PageResult findPage(RebackQueryPageBean rebackQueryPageBean){
            return null;
    }
}
