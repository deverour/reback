package com.tower.reback.poi;


import com.tower.reback.entity.ExcelColumns;
import com.tower.reback.pojo.Bill;
import com.tower.reback.pojo.Cpy;
import com.tower.reback.pojo.Reback;
import com.tower.reback.utils.MyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelWrite {
    final static int PAGE_COUNTS=1000000;

    public static InputStream  WriteBills(List<Bill> list) throws Exception {
        int pages= list.size()/PAGE_COUNTS+1;
        System.out.println("共： "+pages+"页");
        ArrayList<String> sheetName = new ArrayList<String>();
        for (int page=1;page<=pages;page++){
            sheetName.add("sheet"+page);
        }

        SXSSFWorkbook wb=new SXSSFWorkbook();
        ArrayList<String> namelist = ExcelColumns.getBillTitle();

        long ta =0;
        long tb =0;
        ta = System.currentTimeMillis();
        for (int page=1;page<=pages;page++){
            wb.createSheet(sheetName.get(page-1));
            SXSSFSheet sheet = wb.getSheetAt(page-1);
            int rowindex = 0;
            SXSSFRow row = sheet.createRow(rowindex);
            int colindex = 0;
            for (String str:namelist){
                SXSSFCell cell = row.createCell(colindex);
                cell.setCellValue(str);
                colindex++;
            }
            rowindex++;
            int countanull=0;
            Cell cell;
            int start=(page-1)*(PAGE_COUNTS-1);
            int end=page*(PAGE_COUNTS-1)-1;
            if(page==pages){
                end=list.size()-1;
            }
            for (int listindex=start;listindex<=end;listindex++){
                row = sheet.createRow(rowindex);
                List<String> billlist = MyUtils.getList(list.get(listindex));
                for (int i=0;i<billlist.size();i++){
                    String str =billlist.get(i);
                    cell = row.createCell(i);
                    cell.setCellValue(str);
                }
                rowindex++;
            }
        }

        tb = System.currentTimeMillis();
        System.out.println("总耗时："+(tb-ta));
        InputStream in = null;
        try{
            //临时缓冲区
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            //创建临时文件
            wb.write(baout);
            byte [] bookByteAry = baout.toByteArray();
            in = new ByteArrayInputStream(bookByteAry);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return in;
    }

    public static InputStream  WriteRebacks(List<Reback> list) throws Exception {
        ArrayList<String> sheetName = new ArrayList<String>();
        SXSSFWorkbook wb=new SXSSFWorkbook();
        ArrayList<String> namelist = ExcelColumns.getRebackTitle();
        wb.createSheet("steet1");
        SXSSFSheet sheet = wb.getSheetAt(0);
        int rowindex = 0;
        SXSSFRow row = sheet.createRow(rowindex);
        int colindex = 0;
        for (String str:namelist){
            SXSSFCell cell = row.createCell(colindex);
            cell.setCellValue(str);
            colindex++;
        }
        rowindex++;
        Cell cell;

        for (Reback reback:list){
            row = sheet.createRow(rowindex);
            List<String> rebacklist = MyUtils.getRebackList(reback);
            colindex = 0;
            for (String str : rebacklist ){
                cell = row.createCell(colindex);
                cell.setCellValue(str);
                colindex++;
            }
            rowindex++;
        }


        InputStream in = null;
        try{
            //临时缓冲区
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            //创建临时文件
            wb.write(baout);
            byte [] bookByteAry = baout.toByteArray();
            in = new ByteArrayInputStream(bookByteAry);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return in;
    }

    public static InputStream  WriteCpys(List<Cpy> list) throws Exception {
        int pages= list.size()/PAGE_COUNTS+1;
        System.out.println("共： "+pages+"页");
        ArrayList<String> sheetName = new ArrayList<String>();
        for (int page=1;page<=pages;page++){
            sheetName.add("sheet"+page);
        }
        SXSSFWorkbook wb=new SXSSFWorkbook();
        ArrayList<String> namelist = ExcelColumns.getCpyTitle();
        long ta =0;
        long tb =0;
        ta = System.currentTimeMillis();
        for (int page=1;page<=pages;page++){
            wb.createSheet(sheetName.get(page-1));
            SXSSFSheet sheet = wb.getSheetAt(page-1);
            int rowindex = 0;
            SXSSFRow row = sheet.createRow(rowindex);
            int colindex = 0;
            for (String str:namelist){
                SXSSFCell cell = row.createCell(colindex);
                cell.setCellValue(str);
                colindex++;
            }
            rowindex++;
            Cell cell;
            int start=(page-1)*(PAGE_COUNTS-1);
            int end=page*(PAGE_COUNTS-1)-1;
            if(page==pages){
                end=list.size()-1;
            }
            for (int listindex=start;listindex<=end;listindex++){
                row = sheet.createRow(rowindex);
                List<String> cpylist = MyUtils.getCpyList(list.get(listindex));
                for (int i=0;i<cpylist.size();i++){
                    String str =cpylist.get(i);
                    cell = row.createCell(i);
                    cell.setCellValue(str);
                }
                rowindex++;
            }
        }
        tb = System.currentTimeMillis();
        System.out.println("总耗时："+(tb-ta));
        InputStream in = null;
        try{
            //临时缓冲区
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            //创建临时文件
            wb.write(baout);
            byte [] bookByteAry = baout.toByteArray();
            in = new ByteArrayInputStream(bookByteAry);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return in;
    }


}
