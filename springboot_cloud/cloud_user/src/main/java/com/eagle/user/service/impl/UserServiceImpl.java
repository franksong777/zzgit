package com.eagle.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eagle.user.dao.UserDao;
import com.eagle.user.domain.User;
import com.eagle.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
