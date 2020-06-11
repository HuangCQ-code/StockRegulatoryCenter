package com.stock.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import com.stock.entity.SysOperationLog;
import com.stock.model.CodeType;
import com.stock.service.ISysLogService;
import com.stock.util.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ISysLogService sysLogService;

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        long visitMillis = System.currentTimeMillis(); //访问时间戳

        String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
        if (isAjax) {
            int code = CodeType.Failure.value;
            if ("Bad credentials".equals(exception.getMessage())) {
                code = CodeType.UserOrPasswordError.value;
            } else if ("Username not found".equals(exception.getMessage())) {
                code = CodeType.UsernameNotFound.value;
            } else if ("User is disabled".equals(exception.getMessage())) {
                code = CodeType.UserDisabled.value;
            }
            response.getWriter().print(code);
            response.getWriter().flush();
        } else {
            super.onAuthenticationFailure(request, response, exception);
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
        sysLog.setMethod("onAuthenticationFailure");
        sysLog.setParameters("[username=" + username +"]");
        sysLog.setExecutionTime(System.currentTimeMillis() - visitMillis); //执行时长
        sysLog.setVisitMillis(visitMillis);
        sysLog.setCreateMillis(System.currentTimeMillis());
        sysLog.setLastUpdateMillis(sysLog.getCreateMillis());
        sysLog.setExtra("loginFailure");

        //调用Service记录
        sysLogService.saveTx(sysLog);

    }

}
