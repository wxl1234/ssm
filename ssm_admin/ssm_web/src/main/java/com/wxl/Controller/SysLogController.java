package com.wxl.Controller;

import com.github.pagehelper.PageInfo;
import com.wxl.domain.SysLog;
import com.wxl.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(value = "pageSize",defaultValue = "1")Integer pageNum,
                                @RequestParam(value = "size",defaultValue = "4")Integer size){
        ModelAndView mv =new  ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll(pageNum, size);

        mv.addObject("pageInfo",new PageInfo(sysLogs));
        mv.setViewName("syslog-list");
        return mv;
    }
}
