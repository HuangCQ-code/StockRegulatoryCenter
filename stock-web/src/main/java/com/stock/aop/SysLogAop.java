package com.stock.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.stock.entity.SysOperationLog;
import com.stock.service.ISysLogService;
import com.stock.util.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Description: 操作日志
 * @Author: weiguodong
 * @Create:2019/9/25 17:59
 */
@Component
@Aspect
public class SysLogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Long visitMillis; //访问时间戳
    private Class clazz;    //访问的类
    private Method method;  //访问的方法

    //前置通知
    @Before("execution(* com.stock.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitMillis = System.currentTimeMillis();
        clazz = jp.getTarget().getClass();
        MethodSignature signature = (MethodSignature) jp.getSignature();
        method = signature.getMethod();
    }

    //后置通知
    @After("execution(* com.stock.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取当前操作的用户
        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
        Authentication authentication = context.getAuthentication();
        //如果authentication==null，不需要认证,不记录日志
        if (authentication == null) {
            return;
        }
        User user = (User) authentication.getPrincipal();

        long executionTime = System.currentTimeMillis() - visitMillis; //获取访问的时长

        String url = "";
        //获取url
        if (clazz != null && method != null && clazz != SysLogAop.class) {
            //获取类上的@RequestMapping
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //获取方法上的@RequestMapping
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

                    //获取访问的ip
                    String ip = CommonUtils.getIpAddr(request);

                    String username = "";
                    if (user != null) {
                        username = user.getUsername();
                    } else {
                        username = "匿名用户";
                    }

                    //将日志相关信息封装到SysLog对象
                    SysOperationLog sysLog = new SysOperationLog();
                    sysLog.setUsername(username);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setMethod(method.getName());
                    sysLog.setParameters(Arrays.toString(jp.getArgs()));
                    sysLog.setExecutionTime(executionTime); //执行时长
                    sysLog.setVisitMillis(visitMillis);
                    sysLog.setCreateMillis(System.currentTimeMillis());
                    sysLog.setLastUpdateMillis(sysLog.getCreateMillis());

                    //调用Service记录
                    sysLogService.saveTx(sysLog);
                }
            }
        }

    }
}
