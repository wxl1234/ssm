package com.wxl.Test;

import com.wxl.dao.UserDao;
import com.wxl.domain.User;
import com.wxl.mybatis.sqlsession.SqlSession;
import com.wxl.mybatis.sqlsession.SqlSessionFactoryBulider;
import com.wxl.mybatis.sqlsession.SqlSessionFactroy;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * UserDao测试类
 */
public class UserTest {

    @Test
    public void testFindAll(){
        InputStream in = UserTest.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactroy sqlSessionFactroy = new SqlSessionFactoryBulider().bulid(in);
        SqlSession session = sqlSessionFactroy.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User one = userDao.findOne(41);
        System.out.println(one);
//        List<User> userList = userDao.findAll();
//        for (User user : userList) {
//            System.out.println(user);
//        }
    }

    @Test
    public void testRegix(){
        String str ="ssssssss id = #{id},id = #{id}";
        String regix = "#\\{.{2}\\}";
        System.out.println(str.replaceAll(regix, "?"));
    }
}
