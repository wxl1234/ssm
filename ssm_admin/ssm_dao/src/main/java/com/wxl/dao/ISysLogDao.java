package com.wxl.dao;

import com.wxl.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 日志管理类
 */
public interface ISysLogDao {

    /**
     * 查询所有日志信息
     * @return
     */
    @Select("select * from syslog")
    List<SysLog> findAll();

    /**
     * 保存日志
     * @param sysLog
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void saveSysLog(SysLog sysLog);
}
