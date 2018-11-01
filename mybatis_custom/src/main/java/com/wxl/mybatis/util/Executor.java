package com.wxl.mybatis.util;

import com.wxl.mybatis.config.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * sql语句的执行类和封装数据
 * 实现数据的封装
 */
public class Executor {

    // 查询多个对象
    public static <E> List<E> selectList(Mapper mapper, Connection conn){
        List<E> list = new ArrayList<E>();
        PreparedStatement pstmt = null;
        ResultSet rs =null;
        // 1.获取到sql语句
        String sql = mapper.getQuerySql();
        // 2.获取到返回值类型
        String resultType = mapper.getResultType();
        // 3.加载返回值对象
        try {
            Class<?> clazz = Class.forName(resultType);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                // 4.封装数据
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                E obj = (E)clazz.newInstance();
                for (int i = 1; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(columnName);
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,clazz);
                    Method writeMethod = pd.getWriteMethod();
                    writeMethod.invoke(obj,value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release(pstmt, rs, conn);
        }
        return list;
    }

    // 查询单个对象
    public static <E> E selectOne(Mapper mapper, Connection conn, Object[] args) {
        PreparedStatement pstmt = null;
        ResultSet rs =null;
        // 1.获取到sql语句
        String sql = mapper.getQuerySql();
        String regex = "#\\{.{2,5}\\}";
        sql = sql.replaceAll(regex, "?");
        // 2.获取到返回值类型
        String resultType = mapper.getResultType();
        Class<?> clazz =  null;
        E obj = null;
        // 3.加载返回值对象
        try {
            clazz = Class.forName(resultType);
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i+1,args[i]);
            }
            rs = pstmt.executeQuery();
            while (rs.next()){
                // 4.封装数据
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                obj = (E)clazz.newInstance();
                for (int i = 1; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(columnName);
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,clazz);
                    Method writeMethod = pd.getWriteMethod();
                    writeMethod.invoke(obj,value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release(pstmt, rs, conn);
        }
        return obj;
    }

    private static void  release(PreparedStatement pstmt, ResultSet rs,Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(pstmt != null){
                    try {
                        pstmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally {
                        if(conn != null){
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

}
