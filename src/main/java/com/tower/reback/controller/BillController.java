package com.tower.reback.controller;


import com.tower.reback.entity.BillQueryBean;
import com.tower.reback.entity.Result;
import com.tower.reback.poi.ExcelWrite;
import com.tower.reback.pojo.Bill;
import com.tower.reback.pojo.User;
import com.tower.reback.service.BillService;
import com.tower.reback.utils.FileUtils;
import com.tower.reback.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bill")
public class BillController {

    public static final String SCANPATH = "D:\\SystemInfo\\logs\\ScanFile_Upload";
    public static final String UPLOAD_TEMP="D:\\SystemInfo\\Cache\\TEMP";

    @Autowired
    private BillService billService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile multipartFile, HttpSession httpSession){
        /*try {
            User user = (User)httpSession.getAttribute("user");
            String path =UPLOAD_TEMP;
            String uuid = UUID.randomUUID().toString().replace("-","");
            String filename = FileUtils.getRealName(multipartFile.getOriginalFilename())+uuid;
            File file = new File(path,filename);
            if(!file.exists()){
                file.mkdir();
            }
            multipartFile.transferTo(file);
            return billService.saveBill(file,user);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"读取表格失败,请检查导入表模板后重试");
        }*/
        return new Result(false,"读取表格失败,请检查导入表模板后重试");
    }

    @RequestMapping("/query")
    public Result query(@RequestBody BillQueryBean billQueryBean,HttpSession httpSession){
        try {
            User user = (User)httpSession.getAttribute("user");
            List<Bill> bills = billService.findByCondition(billQueryBean,user);
            double sum=0.0;
            int count=0;
            for (Bill b:bills){
                sum=sum+Double.parseDouble(b.getJiesuanjine());
                count++;
            }
            return new Result(true,count+"条明细，    合计金额："+ Utils.to2Round(String.valueOf(sum)));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"查询失败，请稍后重试");
        }
    }

    @RequestMapping("/export")
    public ResponseEntity<byte[]> export(@RequestBody BillQueryBean billQueryBean, HttpSession httpSession)  {
        try {
            User user = (User)httpSession.getAttribute("user");
            List<Bill> bills = billService.findByCondition(billQueryBean,user);
            InputStream is= ExcelWrite.WriteAll(bills);
            byte[] body = null;
            body = new byte[is.available()];
            is.read(body);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode("代垫签认明细","UTF-8")+".xlsx");
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
