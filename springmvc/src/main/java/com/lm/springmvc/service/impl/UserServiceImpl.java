package com.lm.springmvc.service.impl;

import com.lm.springmvc.entity.User;
import com.lm.springmvc.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean createUser(User user) {
        System.out.println("创建客户成功");
        return true;
    }
}
