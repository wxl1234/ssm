package com.wxl.mybatis.sqlsession;

import com.wxl.mybatis.config.Configuration;
import com.wxl.mybatis.sqlsession.impl.DefaultSqlSessionFactory;
import com.wxl.mybatis.util.XmlConfigParse;

import java.io.InputStream;

/**
 * 接受配置文件的参数类型，实现材料的收集和转发
 */
public class SqlSessionFactoryBulider {

    public SqlSessionFactroy bulid(InputStream in){
        Configuration configuration = XmlConfigParse.LoadMasterConfig(in);
        return  new DefaultSqlSessionFactory(configuration);
    }
}
