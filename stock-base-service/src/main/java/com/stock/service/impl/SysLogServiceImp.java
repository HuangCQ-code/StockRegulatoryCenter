package com.stock.service.impl;

import com.github.pagehelper.PageHelper;
import com.stock.entity.SysOperationLog;
import com.stock.mapper.SysOperationLogMapper;
import com.stock.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 操作日志服务
 * @Author: weiguodong
 * @Create: 2020/4/9 23:03
 */
@Service("sysLogService")
public class SysLogServiceImp implements ISysLogService {

    @Autowired
    private SysOperationLogMapper sysOperationLogMapper;

    @Override
    public void saveTx(SysOperationLog sysLog) {
        sysOperationLogMapper.insertSelective(sysLog);
    }

    @Override
    public List<SysOperationLog> findAllSysLog(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return this.sysOperationLogMapper.findAll();
    }
}
