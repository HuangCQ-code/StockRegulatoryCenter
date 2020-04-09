package com.stock.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import com.stock.entity.SysOperationLog;
import com.stock.service.ISysLogService;
import com.stock.util.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义注销操作 （主要是记录日志）
 * @Author: weiguodong
 * @Create:2019/9/27 13:59
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

    @Autowired
    private ISysLogService sysLogService;

    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //获取当前操作的用户
        String username = "";
        if (authentication == null) {
            super.onLogoutSuccess(request, response, authentication);
            return;
        }
        User user = (User) authentication.getPrincipal();

        if (user != null) {
            username = user.getUsername();
        } else {
            super.onLogoutSuccess(request, response, authentication);
            return;
        }

        long visitMillis = System.currentTimeMillis(); //访问时间戳

        super.onLogoutSuccess(request, response, authentication);

        //记录日志
        //获取访问的ip
        String ip = CommonUtils.getIpAddr(request);

        //将日志相关信息封装到SysLog对象
        SysOperationLog sysLog = new SysOperationLog();
        sysLog.setUsername(username);
        sysLog.setIp(ip);
        sysLog.setUrl("/logout.do");
        sysLog.setMethod("logout");
        sysLog.setParameters("[]");
        sysLog.setExecutionTime(System.currentTimeMillis() - visitMillis); //执行时长
        sysLog.setVisitMillis(visitMillis);
        sysLog.setCreateMillis(System.currentTimeMillis());
        sysLog.setLastUpdateMillis(sysLog.getCreateMillis());

        //调用Service记录
        sysLogService.saveTx(sysLog);
    }
}
