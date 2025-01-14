package org.b3log.solo.cache;

import jodd.util.StringUtil;
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
    private final ExpireMap<String, TokenInfo> cache = new ExpireMap<>();

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
        String token = tokenInfo.getToken();

        return token;

    }



    /**
     * 创建一个过期时间为10分钟的Token
     */
    public String createToken(String userName) {
        return createToken(userName, 60*1000*10L);

    }

    /**
     * 通过UserName来创建Token，如果已经存在，则直接返回已经创建的Token
     * Token的类型是UUID，去除‘-’号后生成的32位字符串
     */
    public String createToken(String userName, Long time) {

        if (StringUtils.isEmpty(userName)) {
            throw new RuntimeException("创建登录Token失败，userName为空");
        }

        String token = getToken(userName);

        if (token != null) {
            return token;
        }

        token = UUID.randomUUID().toString().replace("-", "");

        cache.put(userName, new TokenInfo(token), time);

        return getToken(userName);

    }

    public String createAesKey(String key,Long time){

        if (StringUtils.isEmpty(key)){
            throw new RuntimeException("创建登录Aes Key失败，Key为空");
        }
        String token = getToken(key);

        if (token != null){
            return token;
        }

        String uuid16 = getUUID16();

        cache.put(key, new TokenInfo(uuid16), time);

        return getToken(key);

    }

    public static String getUUID16()
    {
        String uuid= UUID.randomUUID().toString().replace("-","");
        uuid = uuid.substring(0,16);
        return uuid;
    }

    /**
     * 刷新token的失效时间
     * @param key
     * @param time
     */
    public void refreshToken(String key,Long time){

        if (StringUtils.isEmpty(key)){
            return;
        }

        String token = getToken(key);

        if (StringUtils.isEmpty(token)){
            return;
        }

        TokenInfo tokenInfo = cache.get(key);

        cache.put(key,tokenInfo,time);

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
