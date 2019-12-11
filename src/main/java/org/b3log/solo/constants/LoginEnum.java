package org.b3log.solo.constants;

/**
 * Created on 2019/11/29 17:46.
 *
 * @author zhaozengjie
 * Description : 登录所是用的枚举
 */
public enum LoginEnum {


    USER_NAME("user_name"),
    TOKEN("token"),
    USER_ID("user_id");


    private String key;

    public String getKey() {
        return key;
    }



    LoginEnum(String key) {
        this.key = key;
    }
}
