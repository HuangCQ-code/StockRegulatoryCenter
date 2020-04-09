package com.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Description: session失效 和 单点登录踢掉上一个登录用户
 * @Author: weiguodong
 * @Create:2019/10/9 16:12
 */
@Controller
public class SessionController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(value = "/sessionInvalid")
    @ResponseBody
    public String invalidateSession(String message) throws Exception{
        String ajaxHeader = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
        if (isAjax) {
            return message;
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?message=" + message);
            return "";
        }
    }
}
