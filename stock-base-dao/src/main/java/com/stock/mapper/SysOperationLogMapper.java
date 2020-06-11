package com.stock.mapper;

import com.stock.entity.SysOperationLog;

import java.util.List;

public interface SysOperationLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysOperationLog record);

    int insertSelective(SysOperationLog record);

    SysOperationLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysOperationLog record);

    int updateByPrimaryKey(SysOperationLog record);

    //新增
    List<SysOperationLog> findAll();
}