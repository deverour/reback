package com.tower.reback.utils;

import com.tower.reback.pojo.Bill;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class MathUtil {

    /**
     * /// <summary>
     /// 将指定的自然数转换为26进制表示。映射关系：[1-26] ->[A-Z]。
     /// </summary>
     /// <param name="n">自然数（如果无效，则返回空字符串）。</param>
     /// <returns>26进制表示。</returns>

     */
    public static String toNumberSystem26(int n){
        String s = "";
        while (n > 0){
            int m = n % 26;
            if (m == 0) m = 26;
            s = (char)(m + 64) + s;
            n = (n - m) / 26;
        }
        return s;
    }

    /**
     * <summary>
     将指定的26进制表示转换为自然数。映射关系：[A-Z] ->[1-26]。
     </summary>
     <param name="s">26进制表示（如果无效，则返回0）。</param>
     <returns>自然数。</returns>
     */
    public static int fromNumberSystem26(String s){
        if (StringUtils.isBlank(s)) return 0;
        s = s.toUpperCase();
        int n = 0;
        char[] arr = s.toCharArray();
        for (int i = arr.length - 1, j = 1; i >= 0; i--, j *= 26){
            char c = arr[i];
            if (c < 'A' || c > 'Z') return 0;
            //A的ASCII值为65
            n += ((int)c - 64) * j;
        }
        return n;
    }


    public static void main(String[] args) {
        System.out.println(fromNumberSystem26("aa"));
        System.out.println(toNumberSystem26(27));
    }

    public static Bill getBill(List<String> list){
        Bill bill = new Bill();
        bill.setQuyu(list.get(0));
        bill.setZhifudanhao(list.get(1));
        bill.setZhanzhibianma(list.get(2));
        bill.setDianbiaobianma(list.get(3));
        bill.setDianbiaobeilv(list.get(4));
        bill.setShifouzhigongdian(list.get(5));
        bill.setHuhao(list.get(6));
        bill.setShiqi(list.get(7));
        bill.setZhongqi(list.get(8));
        bill.setQidu(Utils.to2Round(list.get(9)));
        bill.setZhidu(Utils.to2Round(list.get(10)));
        bill.setDiansun(Utils.to2Round(list.get(11)));
        bill.setDianliang(Utils.to2Round(list.get(12)));
        bill.setDianzizonge(Utils.to2Round(list.get(13)));
        bill.setGongxiangyunyingshang(list.get(14));
        bill.setFentanbili(Utils.to6Round(list.get(15)));
        bill.setJiesuanjine(Utils.to2Round(list.get(16)));
        bill.setZhangqi(list.get(17));
        bill.setJiesuanyunyingshang(list.get(18));
        bill.setKaipiaoshijian(list.get(19));
        bill.setHuikuanbianhao(list.get(20));

        return bill;
    }


   /* public static Cpy getCpy(List<String> list){
        Cpy cpy = new Cpy();
        cpy.setQuyu(list.get(0));
        cpy.setZhanzhibianma(list.get(1));
        cpy.setZhanzhimingchen(list.get(2));
        cpy.setGongxiangfangshi(list.get(3));
        cpy.setShifouzhigongdian(list.get(4));
        cpy.setDianjia(list.get(5));
        cpy.setJizhunnianjia(Utils.to2Round(list.get(6)));
        cpy.setYearone(Utils.to2Round(list.get(7)));
        cpy.setYeartwo(Utils.to2Round(list.get(8)));
        cpy.setYearthree(Utils.to2Round(list.get(9)));
        cpy.setShiqi(list.get(10));
        cpy.setZhongqi(list.get(11));
        cpy.setChuzhangjine(Utils.to2Round(list.get(12)));
        cpy.setTiaozhangjine(Utils.to2Round(list.get(13)));
        cpy.setJiesuanjine(Utils.to6Round(list.get(14)));
        cpy.setZhangqi(list.get(15));
        cpy.setJiesuanyunyingshang(list.get(16));
        cpy.setZhibiaoshijian(list.get(17));
        cpy.setKaipiaobianhao(list.get(18));


        return cpy;
    }
    public static ArrayList<String> getTitle(String filepath) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        FileInputStream inp = new FileInputStream(filepath);
        XSSFWorkbook wb = new XSSFWorkbook(inp);
        XSSFSheet sheet = wb.getSheetAt(0); //
        int coloumNum=sheet.getRow(0).getPhysicalNumberOfCells();
        XSSFRow row = sheet.getRow(0);
        for (int colindex=0;colindex<=coloumNum;colindex++){
            XSSFCell cell =row.getCell(colindex);
            list.add(getString(cell));
        }
        return list;
    }

    public static String getString(XSSFCell xssfCell) {
        if (xssfCell == null) {
            return "";
        }
        if (xssfCell.getCellTypeEnum() == CellType.NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else if (xssfCell.getCellTypeEnum() == CellType.BOOLEAN) {

            return String.valueOf(xssfCell.getBooleanCellValue());
        } else {
            return xssfCell.getStringCellValue();
        }
    }*/




}
