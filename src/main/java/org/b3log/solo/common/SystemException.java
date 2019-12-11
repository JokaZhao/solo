package org.b3log.solo.common;

/**
 * Created on 2019/12/7 5:32 下午.
 *
 * @author zhaozengjie
 * Description : 系统统一的错误
 */
public class SystemException extends Exception {

    private String code;

    private String msg;

    public SystemException(String msg) {
        super(msg);
        this.msg = msg;
        this.code = "000001";
    }

    public SystemException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
