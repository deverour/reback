package com.tower.reback.controller;


import com.tower.reback.entity.FilePath;
import com.tower.reback.entity.Result;
import com.tower.reback.pojo.Reback;
import com.tower.reback.pojo.User;
import com.tower.reback.service.RebackService;
import com.tower.reback.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/scanned")
public class ScannedController {

    @Autowired
    private RebackService rebackService;



    @RequestMapping("/upload")
    public Result upload(@RequestParam("sannedFile") MultipartFile multipartFile,@RequestParam("id") Integer id){
        try {

            String path = FilePath.UPLOAD_TEMP;
            Reback reback=rebackService.findbById(id);
            String filename = MyUtils.getRealName(multipartFile.getOriginalFilename());
            String rebackNumber = reback.getHuikuanbianhao();
            filename = MyUtils.addUUID(filename,rebackNumber);
            multipartFile.transferTo(new File(FilePath.SCANPATH,filename));
            File file = new File(path,filename);
            if(!file.exists()){
                file.mkdir();
            }
            reback.setIssaomiao("是");
            reback.setSaomiaoname(filename);
            rebackService.update(reback);
            return new Result(true,"上传扫描件成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"上传扫描件失败,请刷新网页后重试");
        }
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam("id") Integer id){
        try {
            Reback reback = rebackService.findbById(id);
            String fileName = reback.getSaomiaoname();
            if (fileName.equals("")){
                fileName=FilePath.DEFAULT_SCANNED;
            }
            String finalFileName = MyUtils.getFinalFileName(reback.getHuikuanbianhao(),reback.getSaomiaoname());
            InputStream is= new FileInputStream(FilePath.SCANPATH+"\\"+reback.getSaomiaoname());
            byte[] body = new byte[is.available()];
            is.read(body);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attchement;filename=" + finalFileName);
            HttpStatus statusCode = HttpStatus.OK;
            ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
            return entity;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        try {
            rebackService.deleteScanned(id);
            return new Result(true,"删除扫描件成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除扫描件失败,请刷新网页后重试");
        }
    }



}
