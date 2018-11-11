package com.wxl.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxl.dao.IPermissionDao;
import com.wxl.domain.Permission;
import com.wxl.service.IPermissionService;
import com.wxl.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源许可服务层
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService{

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum,size);
        return permissionDao.findAll();
    }

    @Override
    public void savePermission(Permission permission) {
        permission.setId(UuidUtil.getUuid());
        permissionDao.savePermission(permission);
    }
}
