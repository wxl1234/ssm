package com.wxl.mybatis.sqlsession;

/**
 * 接受构建者提供的数据，创建sqlsession
 */
public interface SqlSessionFactroy {
    /**
     * 创建sqlSession
     * @return
     */
    SqlSession openSession();
}
