package com.wxl.Controller;

import com.github.pagehelper.PageInfo;
import com.wxl.domain.Permission;
import com.wxl.domain.Role;
import com.wxl.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;
    /**
     * 分页查询资源许可信息
     * @param pageNum
     * @param size
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "pageSize",defaultValue = "1")Integer pageNum,
                                @RequestParam(value = "size",defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll(pageNum, size);
        mv.addObject("pageInfo",new PageInfo(permissions));
        mv.setViewName("permission-list");
        return mv;
    }



    /**
     * 新增资源许可信息
     * @param permission
     * @return
     */
    @RequestMapping("/save.do")
    public String SaveRole(Permission permission) throws Exception {
        permissionService.savePermission(permission);
        return "redirect:/permission/findAll.do";
    }
}
