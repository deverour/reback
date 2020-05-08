package com.tower.reback.dao;

import com.tower.reback.entity.BillQueryBean;
import com.tower.reback.pojo.Bill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface BillDao {





    @Select("select zhifudanhao from payment")
    public Set<String> getPaynumberSet();

    @Insert("insert into bills (quyu,zhifudanhao,zhanzhibianma,dianbiaobianma,dianbiaobeilv,shifouzhigongdian,huhao,shiqi,zhongqi,qidu,zhidu,diansun,dianliang,dianzizonge,gongxiangyunyingshang,fentanbili,jiesuanjine,zhangqi,jiesuanyunyingshang,kaipiaoshijian,kaipiaobianhao,shangchuanriqi) values (#{quyu},#{zhifudanhao},#{zhanzhibianma},#{dianbiaobianma},#{dianbiaobeilv},#{shifouzhigongdian},#{huhao},#{shiqi},#{zhongqi},#{qidu},#{zhidu},#{diansun},#{dianliang},#{dianzizonge},#{gongxiangyunyingshang},#{fentanbili},#{jiesuanjine},#{zhangqi},#{jiesuanyunyingshang},#{kaipiaoshijian},#{kaipiaobianhao},#{shangchuanriqi}) ")
    public int saveBill(Bill bill);


    @Select("<script> select * from bills where 1=1" +
            "<if test='billPayNumber != null and billPayNumber !=\"\"'>" +
                "and zhifudanhao = #{billPayNumber}  "+
            "</if>" +
            "<if test ='billArea != null and billArea.size() > 0 ' >" +
                "and quyu in   " +
                "<foreach item='item' collection='billArea' separator=',' open='(' close=')' index=''>"+
                    "#{item}" +
                "</foreach>" +
            "</if>" +

            "<if test ='billCustomer != null and billCustomer.size() > 0 ' >" +
                "and jiesuanyunyingshang in   " +
                "<foreach item='item' collection='billCustomer' separator=',' open='(' close=')' index=''>"+
                    "#{item}" +
                "</foreach>" +
            "</if>" +

            "<if test ='billStartPaymentDate != null' > " +
                "and zhangqi between #{billStartPaymentDate} and #{billEndPaymentDate}   " +
            "</if>" +
            "</script>")
    public List<Bill> findByCondition(BillQueryBean billQueryBean);

}
