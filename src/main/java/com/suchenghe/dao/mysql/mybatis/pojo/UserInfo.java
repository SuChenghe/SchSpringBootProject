package com.suchenghe.dao.mysql.mybatis.pojo;

import lombok.Data;

/**
 * @author SuChenghe
 * @date 2018/12/16 23:07
 */
@Data
public class UserInfo {
    private Long id;
    private String userName;
    private String passWord;
}
