package com.stock.service;

import com.stock.entity.SysOperationLog;

import java.util.List;

/**
 * @Description: 操作日志服务接口
 * @Author: weiguodong
 * @Create: 2020/4/9 23:01
 */
public interface ISysLogService {

    void saveTx(SysOperationLog sysLog);

    List<SysOperationLog> findAllSysLog(int pageNum, int pageSize);

}
