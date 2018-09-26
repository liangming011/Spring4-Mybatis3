package com.lm.springmvc.service;

import com.lm.springmvc.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    public boolean createUser(User user);
}
