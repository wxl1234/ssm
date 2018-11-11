
package com.wxl.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxl.dao.IPermissionDao;
import com.wxl.dao.IRoleDao;
import com.wxl.domain.Permission;
import com.wxl.domain.Role;
import com.wxl.service.IRoleService;
import com.wxl.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色服务层实现类
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 分页显示角色
     * @param pageNum
     * @param size
     * @return
     */
    @Override
    public List<Role> findAll(Integer pageNum, Integer size) throws Exception {
        PageHelper.startPage(pageNum,size);
        return roleDao.findAll();
    }

    /**
     * 新增角色
     * @param role
     */
    @Override
    public void saveRoel(Role role) throws Exception {
        role.setId(UuidUtil.getUuid());
        roleDao.saveRole(role);
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @Override
    public Role findRoleById(String id) throws Exception {
        return roleDao.findRoleByRoleId(id);
    }

    @Override
    public void assignPermissionToRole(String permissionid, String roleid) {
        roleDao.assignPermissionToRole(permissionid,roleid);
    }

    @Override
    public List<Permission> findPermissionAssignToRole(String roleid) {
        return permissionDao.findPermissionAssignRole(roleid);
    }
}
