package com.wxl.mapper;

import com.wxl.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户持久层入口
 */
public interface UserMapper {

    /**
     * 保存用户信息
     * @param user
     */
    @Insert("insert into user(username,birthday,sex,address,money) values(#{username},#{birthday},#{sex},#{address},#{money})")
    void saveUser(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 修改用户信息
     * @param user
     */
    @Update("update user set money = #{money} where id=#{id}")
    void updateUser(User user);

    /**
     * 查询用户根据用户用户姓名
     * @param username
     * @return
     */
    @Select("select * from user where username = #{value}")
    List<User> findUserByUser(String username);

}
