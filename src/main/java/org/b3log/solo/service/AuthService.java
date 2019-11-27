package org.b3log.solo.service;

import org.apache.commons.lang.StringUtils;
import org.b3log.latke.ioc.Inject;
import org.b3log.latke.service.annotation.Service;
import org.b3log.solo.cache.TokenCache;
import org.b3log.solo.encrypt.AES;
import org.b3log.solo.encrypt.PasswordHash;
import org.b3log.solo.model.LoginForm;
import org.b3log.solo.repository.UserLoginInfoRepository;
import org.b3log.solo.util.Solos;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created on 2019/11/21 17:47.
 *
 * @author zhaozengjie
 * Description : 登录验证
 */
@Service
public class AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    @Inject
    private TokenCache tokenCache;

    @Inject
    private UserLoginInfoRepository userLoginInfoRepository;

    public String getUserTicket(String userName, String kid, String paramToken) {

        commonAuth(kid, paramToken);

        String ticket = tokenCache.createToken(userName, 10 * 1000L);

        return ticket;

    }


    public boolean authLogin(LoginForm form) {

        commonAuth(form.getKid(),form.getToken());

        String ticket = tokenCache.getToken(form.getUserName());

        if (StringUtils.isEmpty(ticket)){
            LOGGER.info("无法获取到登录Ticket，登录Ticket失效或者非法登录");
            throw new RuntimeException("登录失败");
        }

        //明文密码
        String password = AES.getInstance().decrypt(form.getPw(), ticket);

        JSONObject userInfo = userLoginInfoRepository.getByUserName(form.getUserName());

        String salt = userInfo.getString("salt");

        String storePw = userInfo.getString("password");

        try {
            boolean isPass = PasswordHash.validatePassword(salt + password, storePw);

            return isPass;

        } catch (Exception e) {
            LOGGER.error("",e);
            throw new RuntimeException("系统异常");
        }

    }

    /**
     * 通用校验
     * @param kid 临时用户名
     * @param paramToken 临时token
     */
    private void commonAuth(String kid, String paramToken) {
        String token = tokenCache.getToken(kid);

        if (StringUtils.isEmpty(token)) {
            LOGGER.info("无法获取到登录Token，登录token失效或者非法登录");
            throw new RuntimeException("登录失败");
        }

        if (!token.equals(paramToken)) {
            LOGGER.info("登录Token不一致，origin-{},param-{}", token, paramToken);
            throw new RuntimeException("登录失败");
        }
    }

}
