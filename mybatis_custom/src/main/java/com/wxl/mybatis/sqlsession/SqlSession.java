package com.wxl.mybatis.sqlsession;

/**
 *  动态实现接口方法(动态代理)
 */
public interface SqlSession {

    /**
     * 通过传入类字节码文件，动态实现接口的方法
     * @param mapperClass
     * @param <E>
     * @return
     */
    <E> E getMapper(Class mapperClass);

    /**
     * 释放资源
     */
    void close();
}
