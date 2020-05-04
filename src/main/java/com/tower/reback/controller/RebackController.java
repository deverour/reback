package com.tower.reback.controller;

import com.tower.reback.entity.PageResult;
import com.tower.reback.entity.RebackQueryPageBean;
import com.tower.reback.service.RebackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("/reback")
public class RebackController {

    @Autowired
    private RebackService rebackService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody RebackQueryPageBean rebackQueryPageBean){

        try {
            return rebackService.pageQuery(rebackQueryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageResult(0l,new ArrayList());
    }
}
