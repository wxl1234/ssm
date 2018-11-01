package com.wxl.mybatis.sqlsession.impl;

import com.wxl.mybatis.config.Configuration;
import com.wxl.mybatis.sqlsession.SqlSession;
import com.wxl.mybatis.sqlsession.SqlSessionFactroy;

/**
 * SqlSessionFactory的实现工厂类
 */
public class DefaultSqlSessionFactory  implements SqlSessionFactroy{

    private Configuration configuration;
    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }


    /**
     * 创建sqlSession
     * @return
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
