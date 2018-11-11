package com.wxl.Controller;

import com.github.pagehelper.PageInfo;
import com.wxl.domain.Permission;
import com.wxl.domain.Role;
import com.wxl.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色控制层
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    /**
     * 分页查询角色信息
     * @param pageNum
     * @param size
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "pageSize",defaultValue = "1")Integer pageNum,
                                @RequestParam(value = "size",defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll(pageNum, size);
        mv.addObject("pageInfo",new PageInfo(roles));
        mv.setViewName("role-list");
        return mv;
    }


    /**
     * 新增角色信息
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public String SaveRole(Role role) throws Exception {
        roleService.saveRoel(role);
        return "redirect:/role/findAll.do";
    }

    /**
     * 角色详情
     * @param id
     * @return
     */
    @RequestMapping("/findRoleById")
    public ModelAndView findRoleById(@RequestParam(value = "id",required = true)String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findRoleById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    /**
     * 查询当前用户和所有可用的角色信息
     * @return
     */
    @RequestMapping("findRoleByIdAndAllPermission.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(value = "id") String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("permissionList",roleService.findPermissionAssignToRole(id));
        Role role = roleService.findRoleById(id);
        role.setId(id);
        mv.addObject("role",role);
        mv.setViewName("roler-permission-add");
        return mv;
    }

    /**
     * 为当前用户分配角色
     * @param roleId
     * @param permissionIds
     * @return
     * @throws Exception
     */
    @RequestMapping("addPermissionToRole.do")
    public String assignRoleToUser(@RequestParam(value = "roleId",required = true)String roleId,
                                   @RequestParam(value = "permissionIds",required = true)List<String> permissionIds) throws Exception {
        for (String pid : permissionIds) {
            roleService.assignPermissionToRole(pid,roleId);
        }
        return "redirect:/role/findAll.do";
    }
}
