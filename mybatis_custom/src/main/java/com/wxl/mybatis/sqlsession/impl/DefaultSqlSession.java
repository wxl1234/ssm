package com.wxl.mybatis.sqlsession.impl;

import com.wxl.mybatis.config.Configuration;
import com.wxl.mybatis.sqlsession.proxy.MapperProxy;
import com.wxl.mybatis.sqlsession.SqlSession;
import com.wxl.mybatis.util.DataBaseUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * SqlSession的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration config;
    private Connection conn;
    public DefaultSqlSession(Configuration cfg){
        this.config = cfg;
        conn = DataBaseUtil.getConnection(cfg);
    }

    /**
     * 动态实现mapper接口的方法(动态代理)
     * @param mapperClass
     * @param <E>
     * @return
     */
    public <E> E getMapper(Class mapperClass) {
        return (E) Proxy.newProxyInstance(
                mapperClass.getClassLoader(),
                new Class[]{mapperClass},
                new MapperProxy(config.getMappers(),conn));
    }

    /**
     * 释放资源
     */
    public void close() {

    }
}
