package com.tower.reback.dao;


import com.tower.reback.entity.CpyQueryBean;

import com.tower.reback.pojo.Cpy;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CpyDao {



    @Select("select huikuanbianhao from cpys")
    public Set<String> getHuikuanbianhaoSet();

    @Select("select zhifudanhao from payment")
    public Set<String> getPaynumberSet();

    @Insert("insert into cpys (quyu,zhanzhibianma,zhanzhimingchen,gongxiangfangshi,shifouzhigongdian,dianjia,jizhunnianjia,yearone,yeartwo,yearthree,shiqi,zhongqi,chuzhangjine,tiaozhangjine,jiesuanjine,zhangqi,jiesuanyunyingshang,zhibiaoshijian,huikuanbianhao,shangchuanriqi) values (#{quyu},#{zhanzhibianma},#{zhanzhimingchen},#{gongxiangfangshi},#{shifouzhigongdian},#{dianjia},#{jizhunnianjia},#{yearone},#{yeartwo},#{yearthree},#{shiqi},#{zhongqi},#{chuzhangjine},#{tiaozhangjine},#{jiesuanjine},#{zhangqi},#{jiesuanyunyingshang},#{zhibiaoshijian},#{huikuanbianhao},#{shangchuanriqi}) ")
    public int saveCpy(Cpy cpy);


    @Select("<script> select * from cpys where 1=1" +
            "<if test ='cpyArea != null and cpyArea.size() > 0 ' >" +
                "and quyu in   " +
                "<foreach item='item' collection='cpyArea' separator=',' open='(' close=')' index=''>"+
                    "#{item}" +
                "</foreach>" +
            "</if>" +

            "<if test ='cpyCustomer != null and cpyCustomer.size() > 0 ' >" +
                "and jiesuanyunyingshang in   " +
                "<foreach item='item' collection='cpyCustomer' separator=',' open='(' close=')' index=''>"+
                    "#{item}" +
                "</foreach>" +
            "</if>" +

            "<if test ='cpyStartPaymentDate != null' > " +
                "and zhangqi between #{cpyStartPaymentDate} and #{cpyEndPaymentDate}   " +
            "</if>" +
            "</script>")
    public List<Cpy> findByCondition(CpyQueryBean cpyQueryBean);

}
