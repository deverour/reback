package com.tower.reback.controller;

import com.tower.reback.entity.CpyQueryBean;
import com.tower.reback.entity.Result;

import com.tower.reback.poi.ExcelWrite;
import com.tower.reback.pojo.Cpy;
import com.tower.reback.pojo.User;
import com.tower.reback.service.CpyService;
import com.tower.reback.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cpy")
public class CpyController {
    public static final String SCANPATH = "D:\\SystemInfo\\logs\\ScanFile_Upload";
    public static final String UPLOAD_TEMP="D:\\SystemInfo\\Cache\\TEMP";

    @Autowired
    private CpyService cpyService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile multipartFile, HttpSession httpSession){
        try {
            User user = (User)httpSession.getAttribute("user");
            String path =UPLOAD_TEMP;
            String uuid = UUID.randomUUID().toString().replace("-","");
            String filename = MyUtils.getRealName(multipartFile.getOriginalFilename())+uuid;
            File file = new File(path,filename);
            if(!file.exists()){
                file.mkdir();
            }
            multipartFile.transferTo(file);
            return cpyService.saveCpy(file,user);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"读取表格失败,请检查导入表模板后重试");
        }
    }

    @RequestMapping("/query")
    public Result query(@RequestBody CpyQueryBean cpyQueryBean, HttpSession httpSession){
        try {
            User user = (User)httpSession.getAttribute("user");
            List<Cpy> cpys = cpyService.findByCondition(cpyQueryBean,user);
            double sum=0.0;
            int count=0;
            for (Cpy b:cpys){
                sum=sum+Double.parseDouble(b.getJiesuanjine());
                count++;
            }
            return new Result(true,count+"条明细，    合计金额："+ MyUtils.to2Round(String.valueOf(sum)));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"查询失败，请稍后重试");
        }
    }

    @RequestMapping("/export")
    public ResponseEntity<byte[]> export(@RequestBody CpyQueryBean cpyQueryBean, HttpSession httpSession){
        try {
            User user = (User)httpSession.getAttribute("user");
            List<Cpy> cpys = cpyService.findByCondition(cpyQueryBean,user);
            InputStream is= ExcelWrite.WriteCpys(cpys);
            byte[] body = null;
            body = new byte[is.available()];
            is.read(body);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode("包干签认明细","UTF-8")+".xlsx");
            HttpStatus statusCode = HttpStatus.OK;
            ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
            System.out.println("查询成功,开始下载");
            return entity;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
