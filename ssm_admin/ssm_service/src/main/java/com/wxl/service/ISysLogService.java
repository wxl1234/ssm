package com.wxl.service;

import com.wxl.domain.SysLog;

import java.util.List;

/**
 * 日志类服务层入口
 */
public interface ISysLogService {


    List<SysLog> findAll(Integer pageNum,Integer size);

    void saveSysLog(SysLog sysLog);
}
