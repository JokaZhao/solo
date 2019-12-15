package org.b3log.solo.constants;

/**
 * Created on 2019/12/13 11:30 上午.
 *
 * @author zhaozengjie
 * Description : 角色枚举类
 */
public enum RoleEnum {
    ADMIN("admin"),
    VISITOR("visitor");


    private String code;

    RoleEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
