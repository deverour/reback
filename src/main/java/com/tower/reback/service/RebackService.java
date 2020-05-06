package com.tower.reback.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tower.reback.dao.RebackDao;
import com.tower.reback.entity.Group;
import com.tower.reback.entity.PageResult;
import com.tower.reback.entity.RebackQueryBean;
import com.tower.reback.entity.RebackQueryPageBean;
import com.tower.reback.pojo.Reback;
import com.tower.reback.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class RebackService {
    @Autowired
    private RebackDao rebackDao;
    public PageResult pageQuery(RebackQueryPageBean rebackQueryPageBean) {
        System.out.println("rebackQueryPageBean>>>>"+rebackQueryPageBean);

        PageHelper.startPage(rebackQueryPageBean.getCurrentPage(),rebackQueryPageBean.getPageSize());

       /* List<Reback> list = rebackDao.findByConditionlist(rebackQueryPageBean);
        System.out.println("list:"+list);*/

        Page<Reback> pageData = rebackDao.findByPage(rebackQueryPageBean);
        System.out.println("Result>>>>>>"+pageData.getResult());
        System.out.println("Total>>>>>"+pageData.getTotal());
        return new PageResult(pageData.getTotal(),pageData.getResult());
    }

    public List<Reback> findByCondition(RebackQueryBean rebackQueryBean, User user) {
        List<String> areaList = new ArrayList<>();
        if (rebackQueryBean.getRebackBranch()==null){
            HashMap<String, HashSet<String>> m= new HashMap<>();
            m= Group.getBranchmap();

            //for (String str : m.get(user.getGroup())){
            for (String str : m.get("重庆")){
                areaList.add(str);
            }
        }else if(rebackQueryBean.getRebackArea()==null){
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

}
