package com.wxl.Controller;

import com.github.pagehelper.PageInfo;
import com.wxl.domain.User;
import com.wxl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Secured(value = "ROLE_ADMIN")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查询所有用户信息
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "pageSize",defaultValue = "1",required = true)Integer pageNum,
                                @RequestParam(value = "size",defaultValue = "4",required = true)Integer size) throws Exception {
     ModelAndView mv = new ModelAndView();
        List<User> users = userService.findAll(pageNum,size);
        mv.addObject("pageInfo",new PageInfo(users));
        mv.setViewName("user-list");
     return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(value = "id",required = true)String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = userService.findUserById(id);
        mv.addObject("user",user);
        System.out.println(user);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 新增用户
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("save.do")
    public String saveUser(User user) throws Exception {
            userService.addUser(user);
        return "redirect:/user/findAll.do";
    }

    /**
     * 查询当前用户和所有可用的角色信息
     * @return
     */
    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(value = "id") String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleList",userService.findUserAssignRoles(id));
        User user = userService.findUserById(id);
        user.setId(id);
        mv.addObject("user",user);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 为当前用户分配角色
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    @RequestMapping("addRoleToUser.do")
    public String assignRoleToUser(@RequestParam(value = "userId",required = true)String userId,
                                   @RequestParam(value = "roleIds",required = true)List<String> roleIds) throws Exception {
        for (String roleId : roleIds) {
            userService.assignRoleToUser(userId,roleId);
        }
        return "redirect:/user/findAll.do";
    }
}
