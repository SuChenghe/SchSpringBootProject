package com.suchenghe.dao.mysql.mybatis.datasourcemapperfirst;

import com.suchenghe.dao.mysql.mybatis.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserInfoMapper {
    UserInfo getUserInfo(UserInfo userInfo);
}
