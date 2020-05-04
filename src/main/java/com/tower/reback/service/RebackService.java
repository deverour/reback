package com.tower.reback.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tower.reback.dao.RebackDao;
import com.tower.reback.entity.PageResult;
import com.tower.reback.entity.RebackQueryPageBean;
import com.tower.reback.pojo.Reback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Page<Reback> pageData = rebackDao.findByCondition(rebackQueryPageBean);
        System.out.println("Result>>>>>>"+pageData.getResult());
        System.out.println("Total>>>>>"+pageData.getTotal());
        return new PageResult(pageData.getTotal(),pageData.getResult());
    }

}
