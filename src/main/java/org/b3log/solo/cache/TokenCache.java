package org.b3log.solo.cache;

import org.apache.commons.lang.StringUtils;
import org.b3log.latke.ioc.Singleton;
import org.b3log.solo.common.ExpireMap;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created on 2019/11/20 17:39.
 *
 * @author zhaozengjie
 * Description : 存储会话Token
 */
@Singleton
public class TokenCache {

    /**
     * key : userName
     * val : token
     */
    private final Map<String, TokenInfo> cache = new ExpireMap<>();

    /**
     * 通过UserName获取token
     * 这里如果token的时间超过10s，则会移除，这里后续会启动一个线程来移除过期的Token
     */
    public String getToken(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        TokenInfo tokenInfo = cache.get(key);

        if (tokenInfo == null) {
            return null;
        }

        return tokenInfo.getToken();

    }

    /**
     * 通过UserName来创建Token，如果已经存在，则直接返回已经创建的Token
     * Token的类型是UUID，去除‘-’号后生成的32位字符串
     */
    public String createToken(String userName) {

        if (StringUtils.isEmpty(userName)) {
            throw new RuntimeException("创建登录Token失败，userName为空");
        }

        String token = getToken(userName);

        if (token != null) {
            return token;
        }

        token = UUID.randomUUID().toString().replace("-", "");

        cache.put(userName, new TokenInfo(token));

        return getToken(userName);
    }

    class TokenInfo {

        private final String token;

        public TokenInfo(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

    }

}
