package org.b3log.solo.service;

import org.apache.commons.lang.StringUtils;
import org.b3log.latke.ioc.Inject;
import org.b3log.latke.service.annotation.Service;
import org.b3log.solo.cache.TokenCache;
import org.b3log.solo.model.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public String getUserSalt(String userName, String kid, String paramToken) {

        String token = tokenCache.getToken(kid);

        if (StringUtils.isEmpty(token)) {
            LOGGER.info("无法获取到登录Token，登录token失效或者非法登录");
            throw new RuntimeException("登录失败");
        }

        if (!token.equals(paramToken)) {
            LOGGER.info("登录Token不一致，origin-{},param-{}", token, paramToken);
            throw new RuntimeException("登录失败");
        }

        return null;

    }

    public boolean authLogin(LoginForm form) {


        return false;
    }

}
