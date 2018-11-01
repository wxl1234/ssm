package com.wxl.service.impl;

import com.wxl.domain.User;
import com.wxl.mapper.UserMapper;
import com.wxl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务层
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 保存用户
     *
     * @param user
     */
    public void saveAccount(User user) {
        userMapper.saveUser(user);
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<User> findAll() {
        System.out.println("service is invoked");
        return userMapper.findAll();
    }

    /**
     * 实现转账
     *
     * @param sourceName
     * @param descName
     * @param money
     */
    @Override
    public void tranferTo(String sourceName, String descName, float money) {

        // 获取转账信息
        List<User> sourceUsers = userMapper.findUserByUser(sourceName);
        List<User> descUsers = userMapper.findUserByUser(descName);

        if (sourceUsers == null || descUsers == null) {
            throw new RuntimeException("用户不存在");
        }
        if (sourceUsers.size() > 1 || descUsers.size() > 1) {
            throw new RuntimeException("数据源不正确");
        }
        // 获取到用户信息
        User sourceUser = sourceUsers.get(0);
        User desUser = descUsers.get(0);

        // 完成转账
        sourceUser.setMoney(sourceUser.getMoney() - money);
        desUser.setMoney(desUser.getMoney() + money);

        //更新数据
        userMapper.updateUser(sourceUser);
        userMapper.updateUser(desUser);
    }


}
