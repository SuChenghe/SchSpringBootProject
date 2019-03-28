package com.suchenghe.service;

import com.suchenghe.dao.mysql.mybatis.pojo.UserInfo;

/**
 * @author SuChenghe
 * @date 2018/12/16 23:05
 */
public interface UserService {
    UserInfo getUserInfo(UserInfo userInfo);
}
