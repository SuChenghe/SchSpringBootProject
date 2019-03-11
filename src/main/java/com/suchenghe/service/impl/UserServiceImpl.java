package com.suchenghe.service.impl;

import com.suchenghe.dao.mysql.datasourcemapperfirst.UserInfoMapper;
import com.suchenghe.dao.mysql.pojo.UserInfo;
import com.suchenghe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SuChenghe
 * @date 2018/12/16 23:05
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfo(UserInfo userInfo) {
        return userInfoMapper.getUserInfo(userInfo);
    }
}
