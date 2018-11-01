package com.wxl.mybatis.util;

import com.wxl.mybatis.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库的工具类
 */
public class DataBaseUtil {

    /**
     * 获取数据库连接对象
     * @param config
     * @return
     */
    public static Connection getConnection(Configuration config){
        try {
            Class.forName(config.getDriver());
            return DriverManager.getConnection(config.getUrl(),config.getUsername(),config.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
