package com.wxl.aop;

import com.wxl.Controller.SysLogController;
import com.wxl.domain.SysLog;
import com.wxl.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 配置日志切面类
 */
@Component("syslog")
@Aspect
public class SysLogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date startTime;//配置访问开始时间
    private Class  executionClass;;// 配置访问的类
    private Method executionMethod;//配置访问的方法


    /**
     * 配置前置通知
     * @param pjp
     */
    @Before("execution(* com.wxl.Controller.*.*(..))")
    public void doBefore(JoinPoint pjp) throws NoSuchMethodException {
            // 配置前置通知
            startTime = new Date();
            executionClass = pjp.getTarget().getClass();
            String methodName = pjp.getSignature().getName();
            Object[] args = pjp.getArgs();
            if(args == null || args.length == 0){
               executionMethod = executionClass.getMethod(methodName);
            }else {
                // 表示获取的方法包含参数
                Class[] method_args = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    method_args[i] = args[i].getClass();
                }
                executionMethod = executionClass.getMethod(methodName,method_args);
            }
    }

    //配置最终通知，获取url，username，executetime，ip，method
    @After("execution(* com.wxl.Controller.*.*(..))")
    public void doAfter(){



        //1.获取类上的@RequestMapping对象
        if(!executionClass.equals(SysLogController.class)){
            // 获取类上的requestmapping
            RequestMapping requestMapping_class = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if(requestMapping_class != null) {
                // 获取方法上的requestmapping
                RequestMapping requestMapping_method = executionMethod.getAnnotation(RequestMapping.class);
                if(requestMapping_method != null){

                    SysLog sysLog = new SysLog();
                    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                    sysLog.setUrl(requestMapping_class.value()[0]+"/"+requestMapping_method.value()[0]);
                    sysLog.setVisitTime(startTime);
                    sysLog.setExecutionTime(new Date().getTime() - startTime.getTime());
                    sysLog.setIp(request.getRemoteAddr());
                    sysLog.setUsername(user.getUsername());
                    sysLog.setMethod(executionClass.getName()+executionMethod.getName());
                    sysLogService.saveSysLog(sysLog);
                }
            }
        }

    }

}
