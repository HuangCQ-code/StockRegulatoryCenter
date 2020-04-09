package com.stock.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import com.stock.entity.SysOperationLog;
import com.stock.model.CodeType;
import com.stock.service.ISysLogService;
import com.stock.util.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private ISysLogService sysLogService;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {

        long visitMillis = System.currentTimeMillis(); //访问时间戳

        String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
        if (isAjax) {
            response.getWriter().print(CodeType.Success.value);
            response.getWriter().flush();
        } else {
            super.onAuthenticationSuccess(request, response, auth);
        }

        //记录日志
        //获取访问的ip
        String ip = CommonUtils.getIpAddr(request);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //将日志相关信息封装到SysLog对象
        SysOperationLog sysLog = new SysOperationLog();
        sysLog.setUsername(username);
        sysLog.setIp(ip);
        sysLog.setUrl("/login.do");
        sysLog.setMethod("onAuthenticationSuccess");
        sysLog.setParameters("[username=" + username + "]");
        sysLog.setExecutionTime(System.currentTimeMillis() - visitMillis); //执行时长
        sysLog.setVisitMillis(visitMillis);
        sysLog.setCreateMillis(System.currentTimeMillis());
        sysLog.setLastUpdateMillis(sysLog.getCreateMillis());
        sysLog.setExtra("loginSuccess");

        //调用Service记录
        sysLogService.saveTx(sysLog);

    }

}
