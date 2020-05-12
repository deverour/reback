package com.tower.reback.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tower.reback.dao.BillDao;
import com.tower.reback.dao.RebackDao;
import com.tower.reback.entity.*;
import com.tower.reback.pojo.Reback;
import com.tower.reback.pojo.User;
import com.tower.reback.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@Service
public class RebackService {
    @Autowired
    private RebackDao rebackDao;

    @Autowired
    private BillDao billDao;

    public PageResult pageQuery(RebackQueryPageBean rebackQueryPageBean,User user) {
        System.out.println("user>>>>>:"+user.getUsername());
        List<String> areaList = new ArrayList<>();
        if (rebackQueryPageBean.getRebackBranch()==null || rebackQueryPageBean.getRebackBranch().isEmpty()){
            HashMap<String, HashSet<String>> m= new HashMap<>();
            m= Group.getBranchmap();

            //for (String str : m.get(user.getGroup())){
            for (String str : m.get(user.getGroup())){
                System.out.println(">>>>>:"+str);
                areaList.add(str);
            }
        }else if(rebackQueryPageBean.getRebackArea()==null || rebackQueryPageBean.getRebackArea().isEmpty()){
            for (String strBranch : rebackQueryPageBean.getRebackBranch()){
                for (String strArea : Group.getBranchmap().get(strBranch)){
                    areaList.add(strArea);
                }
            }
        }else {
            areaList = rebackQueryPageBean.getRebackArea();
        }
        rebackQueryPageBean.setRebackArea(areaList);



        PageHelper.startPage(rebackQueryPageBean.getCurrentPage(),rebackQueryPageBean.getPageSize());

           /* List<Reback> list = rebackDao.findByConditionlist(rebackQueryPageBean);
            System.out.println("list:"+list);*/

        Page<Reback> pageData = rebackDao.findByPage(rebackQueryPageBean);
        System.out.println("Result>>>>>>"+pageData.getResult());
        System.out.println("Total>>>>>"+pageData.getTotal());
        return new PageResult(pageData.getTotal(),pageData.getResult());
    }

    public List<Reback> findByCondition(RebackQueryBean rebackQueryBean, User user) {
        System.out.println("rebackQueryBean.getRebackBranch()>>>>>:"+rebackQueryBean.getRebackBranch());
        List<String> areaList = new ArrayList<>();
        if (rebackQueryBean.getRebackBranch()==null || rebackQueryBean.getRebackBranch().isEmpty()){
            HashMap<String, HashSet<String>> m= new HashMap<>();
            m= Group.getBranchmap();

            //for (String str : m.get(user.getGroup())){
            for (String str : m.get(user.getGroup())){
                System.out.println(">>>>>:"+str);
                areaList.add(str);
            }
        }else if(rebackQueryBean.getRebackArea()==null || rebackQueryBean.getRebackArea().isEmpty()){
            for (String strBranch : rebackQueryBean.getRebackBranch()){
                for (String strArea : Group.getBranchmap().get(strBranch)){
                    areaList.add(strArea);
                }
            }
        }else {
            areaList = rebackQueryBean.getRebackArea();
        }
        rebackQueryBean.setRebackArea(areaList);

        return rebackDao.findByCondition(rebackQueryBean);
    }


    public Reback findbById(Integer id){

        return rebackDao.findbById(id);
    }

    public int update(Reback reback) {

        return rebackDao.update(reback);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Reback deleteScanned(Integer id){
        Reback reback = rebackDao.findbById(id);
        String fileName= reback.getSaomiaoname();
        reback.setSaomiaoname("");
        reback.setIssaomiao("否");
        rebackDao.update(reback);
        File filepath = new File(FilePath.SCANPATH+"\\"+fileName);
        if (filepath.isFile() && filepath.exists()) {
            filepath.delete();
        }
        return reback;

    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Reback deleteById(Integer id){
        Reback reback = rebackDao.findbById(id);

        billDao.deleteByHuikuanbianhao(reback.getHuikuanbianhao());
        rebackDao.deleteById(id);
        return reback;
    }

    public Result remack(Integer id){
        Reback reback = rebackDao.findbById(id);
        if (reback.getIshuikuan().equals("是")){
            reback.setIshuikuan("否");
            reback.setHuikuanriqi("");
            rebackDao.update(reback);
            return new Result(true,"回款明细打标撤销");
        }else {
            String huikuanriqi = MyUtils.getExcelDate(new Date());
            reback.setHuikuanriqi(huikuanriqi);
            reback.setIshuikuan("是");
            rebackDao.update(reback);
            return new Result(true,"回款明细打标成功");
        }


    }
}
