package com.stock.service.impl;

import com.stock.entity.Manager;
import com.stock.mapper.ManagerMapper;
import com.stock.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: weiguodong
 * @Create:2020/4/9 17:17
 */
@Service("managerService")
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = null;
        manager = managerMapper.findByUsername(username);
        if (manager == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + "USER"));
        if ("admin".equals(username)) {
            list.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
        }
        //处理自己的用户对象封装成UserDetails
        User user = new User(manager.getUsername(), manager.getPassword(), manager.getStatus() == 0 ? true : false, true, true, true, list);
        return user;
    }

    @Override
    public Manager getByUsername(String username) {
        return managerMapper.findByUsername(username);
    }
}
