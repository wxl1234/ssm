package com.wxl.service;

import com.wxl.domain.Permission;

import java.util.List;

/**
 * 资源许可持久层
 */
public interface IPermissionService {

    /**
     * 分页显示资源
     * @param pageNum
     * @param size
     * @return
     */
    List<Permission> findAll(Integer pageNum,Integer size);

    /**
     * 新增资源许可
     * @param permission
     */
    void savePermission(Permission permission);
}
