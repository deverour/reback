package com.tower.reback.service;

import com.tower.reback.dao.CpyDao;
import com.tower.reback.dao.RebackDao;
import com.tower.reback.entity.*;
import com.tower.reback.poi.ExcelRead;
import com.tower.reback.poi.LogicCheck;
import com.tower.reback.pojo.Cpy;
import com.tower.reback.pojo.Reback;
import com.tower.reback.pojo.User;
import com.tower.reback.utils.MyUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@Service("CpyService")
public class CpyService {



    @Autowired
    private CpyDao cpyDao;
    @Autowired
    private RebackDao rebackDao;


    @Transactional(rollbackFor = {RuntimeException.class, Error.class})//开启事务
    public Result saveCpy(File file, User user){
        Set<String> huikuanbianhaoSet = new HashSet<>();

        huikuanbianhaoSet = rebackDao.getHuikuanbianhaoSet();

        try {
            ExcelRead excelRead = new ExcelRead(file.getPath(),2);
            Result result = LogicCheck.cpyCheck(excelRead.getMyDataList(),user,huikuanbianhaoSet);
            if (result.isFlag()){
                int index=1;
                double total=0.0;
                int counts=0;
                Cpy cpy ;
                Reback reback = new Reback();
                String shangchuanriqi = MyUtils.getExcelDate(new Date());
                List<List<String>> lists=excelRead.getMyDataList();
                cpy= ExcelColumns.getCpy(lists.get(0));
                HashMap<String,String> branchmap= Group.getAreaMap();
                reback.setFengongsi(branchmap.get(cpy.getQuyu()));
                reback.setQuyu(cpy.getQuyu());
                reback.setZhangqi(cpy.getZhangqi());
                reback.setYunyingshang(cpy.getJiesuanyunyingshang());
                reback.setHuikuanbianhao(cpy.getHuikuanbianhao());
                reback.setIscpy("否");
                for (List<String> cpyList:lists){
                    cpy= ExcelColumns.getCpy(cpyList);
                    cpy.setShangchuanriqi(shangchuanriqi);
                    total=total+ NumberUtils.toDouble(MyUtils.to2Round(cpy.getJiesuanjine()));
                    counts =cpyDao.saveCpy(cpy)+counts;
                    index++;
                }
                reback.setJiesuanjine(MyUtils.to2Round(String.valueOf(total)));
                reback.setIssaomiao("否");
                reback.setIshuikuan("否");
                reback.setHuikuanriqi("");
                reback.setSaomiaoname("");
                reback.setShangchuanriqi(shangchuanriqi);
                rebackDao.saveReback(reback);
                System.out.println("新增签认明细："+counts);
                return new Result(true,"签认明细导入成功,本次导入金额为"+total);
            }

            return result;
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"读取表格失败,请检查导入表模板后重试");
        }


    }

    public List<Cpy> findByCondition(CpyQueryBean cpyQueryBean,User user){
        List<String> areaList = new ArrayList<>();
        if (cpyQueryBean.getCpyBranch()==null || cpyQueryBean.getCpyBranch().isEmpty()){
            HashMap<String, HashSet<String>>  m= new HashMap<>();
            m=Group.getBranchmap();


            for (String str : m.get(user.getGroup())){
                areaList.add(str);
            }

        }else if(cpyQueryBean.getCpyArea()==null || cpyQueryBean.getCpyArea().isEmpty()){
            for (String strBranch : cpyQueryBean.getCpyBranch()){
                for (String strArea : Group.getBranchmap().get(strBranch)){
                    areaList.add(strArea);
                }
            }
        }else {
            areaList = cpyQueryBean.getCpyArea();
        }
        cpyQueryBean.setCpyArea(areaList);

        return cpyDao.findByCondition(cpyQueryBean);




    }


    public PageResult findPage(RebackQueryPageBean rebackQueryPageBean){
            return null;
    }
}
