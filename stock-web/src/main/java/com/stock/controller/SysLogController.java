package com.stock.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.stock.entity.SysOperationLog;
import com.stock.service.ISysLogService;

import java.util.List;


/**
 * @Description: 操作日志服务接口
 * @Author: weiguodong
 * @Create:2019/9/26 14:53
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/toSysLog.do")
    @PreAuthorize("hasRole('ADMIN')")
    public String toSysLog() throws Exception {
        return "sysLog";
    }

    @RequestMapping("/findAllSysLog.do")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public PageInfo findAllSysLog(@RequestParam(name = "pageNum", required = true, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize) throws Exception {
        List<SysOperationLog> sysLogs = sysLogService.findAllSysLog(pageNum, pageSize);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(sysLogs);
        return pageInfo;
    }
}
