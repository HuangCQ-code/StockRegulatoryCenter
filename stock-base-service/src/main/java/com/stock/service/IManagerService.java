package com.stock.service;

import com.stock.entity.Manager;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description: 后台管理人员服务接口
 * @Author: weiguodong
 * @Create:2020/4/9 17:16
 */
public interface IManagerService extends UserDetailsService {

    Manager getByUsername(String username);
}
