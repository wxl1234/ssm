package com.wxl.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxl.dao.ISysLogDao;
import com.wxl.domain.SysLog;
import com.wxl.service.ISysLogService;
import com.wxl.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysLogService")
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public List<SysLog> findAll(Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        return sysLogDao.findAll();
    }

    @Override
    public void saveSysLog(SysLog sysLog) {
        sysLog.setId(UuidUtil.getUuid());
        sysLogDao.saveSysLog(sysLog);
    }
}
