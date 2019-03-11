package com.suchenghe.common.response;

/**
 * @author SuChenghe
 * @date 2018/7/27 10:24
 */
public enum RepresentationEnum {

    /**
     * 参数错误
     */
    PARAM_ERROR(10001, "参数错误"),

    /**
     * 登陆异常
     */
    LOGIN_ERROR(10002, "登陆异常"),

    /**
     * 权限不足
     */
    PERSSION_ERROR(40014, "权限不足");

    private int code;
    private String message;

    RepresentationEnum(int code, String message) {
        this.code = code;
        this.message = message;

    }

    /**
     * 根据code获取msg
     *
     * @param code
     * @return
     */
    public static String getMessage(int code) {
        for (RepresentationEnum c : RepresentationEnum.values()) {
            if (c.code == code) {
                return c.message;
            }
        }
        return null;
    }

}
