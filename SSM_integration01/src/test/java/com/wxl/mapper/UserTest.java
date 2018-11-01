package com.wxl.mapper;

import com.wxl.domain.User;
import com.wxl.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void test1(){

        List<User> users = userService.findAll();
        System.out.println(users);
    }

    @Test
    public void test2(){
        userService.tranferTo("小马宝莉","老王2",20f);
    }
}
