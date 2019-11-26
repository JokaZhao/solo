package org.b3log.solo.model;

import org.apache.commons.lang.StringUtils;
import org.b3log.latke.servlet.RequestContext;

/**
 * Created on 2019/11/21 17:29.
 *
 * @author zhaozengjie
 * Description :
 */
public class LoginForm {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码，使用md5+盐值
     */
    private String pw;

    /**
     * 获取登录页面所使用的Token
     */
    private String token;

    /**
     * 使用用户名所换取的ticket
     */
    private String ticket;

    /**
     * 临时userName
     */
    private String kid;

    public LoginForm(RequestContext context) {

        if (null == context) {
            throw new RuntimeException("没有");
        }

        this.userName = context.param("userName");
        this.kid = context.param("kid");
        this.pw = context.param("pw");
        this.token = context.param("token");
        this.ticket = context.param("ticket");

    }

    public boolean verify() {
        return StringUtils.isNotEmpty(userName)
                && StringUtils.isNotEmpty(kid)
                && StringUtils.isNotEmpty(pw)
                && StringUtils.isNotEmpty(token)
                && StringUtils.isNotEmpty(ticket);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
