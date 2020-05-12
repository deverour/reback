package com.tower.reback.controller;

import com.tower.reback.entity.*;
import com.tower.reback.poi.ExcelWrite;
import com.tower.reback.pojo.Bill;
import com.tower.reback.pojo.Reback;
import com.tower.reback.pojo.User;
import com.tower.reback.service.RebackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/reback")
public class RebackController {

    @Autowired
    private RebackService rebackService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody RebackQueryPageBean rebackQueryPageBean,HttpSession httpSession){

        try {
            User user = (User)httpSession.getAttribute("user");
            return rebackService.pageQuery(rebackQueryPageBean,user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageResult(0l,new ArrayList());
    }


    @RequestMapping("/export")
    public ResponseEntity<byte[]> export(@RequestBody RebackQueryBean rebackQueryBean, HttpSession httpSession/*, HttpServletRequest request*/){
        try {
            User user = (User)httpSession.getAttribute("user");
            List<Reback> rebacks = rebackService.findByCondition(rebackQueryBean,user);
            InputStream is= ExcelWrite.WriteRebacks(rebacks);
            byte[] body = null;
            body = new byte[is.available()];
            is.read(body);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode("回款流程明细","UTF-8")+".xlsx");
            HttpStatus statusCode = HttpStatus.OK;
            ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
            System.out.println("查询成功,开始下载");
            return entity;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        try {
            Reback reback = rebackService.deleteById(id);
            return new Result(true,"删除回款明细成功",reback);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(true,"删除回款明细失败");
    }

    @RequestMapping("/remark")
    public Result remark(@RequestParam("id") Integer id){
        try {
            return rebackService.remack(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false,"回款明细打标操作失败，请稍后重试");
    }
}
