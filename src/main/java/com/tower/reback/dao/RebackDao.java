package com.tower.reback.dao;

import com.github.pagehelper.Page;
import com.tower.reback.entity.RebackQueryBean;
import com.tower.reback.entity.RebackQueryPageBean;
import com.tower.reback.pojo.Reback;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RebackDao {

    @Select("select huikuanbianhao from rebacks")
    Set<String> getHuikuanbianhaoSet();

    @Insert("insert into rebacks (fengongsi,quyu,zhangqi,yunyingshang,huikuanbianhao,jiesuanjine,issaomiao,ishuikuan,huikuanriqi,shangchuanriqi,iscpy) values (#{fengongsi},#{quyu},#{zhangqi},#{yunyingshang},#{huikuanbianhao},#{jiesuanjine},#{issaomiao},#{ishuikuan},#{huikuanriqi},#{shangchuanriqi},#{iscpy}) ")
    int saveReback(Reback reback);




    @Select("<script> select * from rebacks where 1=1" +
            "<if test='rebackNumber != null and rebackNumber !=\"\"'>" +
            "and huikuanbianhao = #{rebackNumber} </if> "+

            "<if test='rebacked != null and rebacked !=\"\"'>" +
            "and ishuikuan = #{rebacked} </if> "+

            "<if test='rebackScanned != null and rebackScanned !=\"\"'>" +
            "and issaomiao = #{rebackScanned} </if> "+


            "<if test ='rebackArea != null and rebackArea.size() > 0 ' >" +
            "and quyu in   " +
            "<foreach item='item' collection='rebackArea' separator=',' open='(' close=')' index=''>"+
            "#{item}" +
            "</foreach>" +
            "</if>" +

            "<if test ='rebackCustomer != null and rebackCustomer.size() > 0 ' >" +
            "and yunyingshang in   " +
            "<foreach item='item' collection='rebackCustomer' separator=',' open='(' close=')' index=''>"+
            "#{item}" +
            "</foreach>" +
            "</if>" +

            "<if test ='rebackStartPaymentDate != null' > " +
            "and zhangqi between #{rebackStartPaymentDate} and #{rebackEndPaymentDate}   " +
            "</if>" +

            "<if test ='rebackStartReceivableDate != null' > " +
            "and huikuanriqi between #{rebackStartReceivableDate} and #{rebackEndReceivableDate}   " +
            "</if>" +
            "</script>")
    Page<Reback> findByPage(RebackQueryPageBean rebackQueryPageBean);

    @Select("<script> select * from rebacks where 1=1" +
            "<if test='rebackNumber != null and rebackNumber !=\"\"'>" +
            "and huikuanbianhao = #{rebackNumber} </if> "+

            "<if test='rebacked != null and rebacked !=\"\"'>" +
            "and ishuikuan = #{rebacked} </if> "+

            "<if test='rebackScanned != null and rebackScanned !=\"\"'>" +
            "and issaomiao = #{rebackScanned} </if> "+


            "<if test ='rebackArea != null and rebackArea.size() > 0 ' >" +
            "and quyu in   " +
            "<foreach item='item' collection='rebackArea' separator=',' open='(' close=')' index=''>"+
            "#{item}" +
            "</foreach>" +
            "</if>" +

            "<if test ='rebackCustomer != null and rebackCustomer.size() > 0 ' >" +
            "and yunyingshang in   " +
            "<foreach item='item' collection='rebackCustomer' separator=',' open='(' close=')' index=''>"+
            "#{item}" +
            "</foreach>" +
            "</if>" +

            "<if test ='rebackStartPaymentDate != null' > " +
            "and zhangqi between #{rebackStartPaymentDate} and #{rebackEndPaymentDate}   " +
            "</if>" +

            "<if test ='rebackStartReceivableDate != null' > " +
            "and huikuanriqi between #{rebackStartReceivableDate} and #{rebackEndReceivableDate}   " +
            "</if>" +
            "</script>")
    List<Reback> findByCondition(RebackQueryBean rebackQueryBean);

    @Select("select * from rebacks where id = #{id}")
    Reback findbById(@Param("id") Integer id);

    @Update("update rebacks set ishuikuan =#{ishuikuan},huikuanriqi=#{huikuanriqi},saomiaoname =#{saomiaoname},issaomiao=#{issaomiao} where id = #{id}")
    int update(Reback reback);

    @Delete("delete from rebacks where id = #{id} ")
    void deleteById(@Param("id") Integer id);


}
