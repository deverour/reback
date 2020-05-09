package com.tower.reback.dao;

import com.tower.reback.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from users where username=#{username} and password=#{password}")
    User findByUserAndPassword(@Param("username") String username,@Param("password") String password);
}
