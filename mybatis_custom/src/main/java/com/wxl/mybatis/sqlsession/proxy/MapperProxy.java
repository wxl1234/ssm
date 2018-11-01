package com.wxl.mybatis.sqlsession.proxy;

import com.wxl.mybatis.config.Mapper;
import com.wxl.mybatis.util.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * 动态代理类.
 */
public class MapperProxy implements InvocationHandler {

    private Map<String,Mapper> maps;
    private Connection conn;
    public MapperProxy(Map<String,Mapper> maps,Connection conn){
        this.maps = maps;
        this.conn = conn;
    }

    /**
     * 代理实现类,动态获取方法名，和dao全限定名称，组成key
     * @param proxy 代理对象的引用
     * @param method 方法
     * @param args 参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //获取方法名称
        String methodName = method.getName();
        //获取当前方法的全限定名称
        String className = method.getDeclaringClass().getName();
        String key = className+"."+methodName;
        Mapper mapper = maps.get(key);
        if(args == null) return Executor.selectList(mapper,conn);
        return Executor.selectOne(mapper,conn,args);
    }
}
