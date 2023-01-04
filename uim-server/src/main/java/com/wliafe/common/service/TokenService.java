package com.wliafe.common.service;

import com.wliafe.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Value("${token.time:14}")
    private Integer time;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public static String getToken() {
        return UUIDUtil.getUUID();
    }

    public String setToken() {
        return setToken("");
    }

    public String setToken(Object data) {
        String token = getToken();
        redisTemplate.opsForValue().set("tokens:" + token, data, time, TimeUnit.DAYS);
        return token;
    }

    /**
     * token校验
     *
     * @param token token
     * @return 存在 true 不存在 false
     */
    public boolean checkToken(String token) {
        token = token == null ? "" : token;
        Long expire = redisTemplate.getExpire("tokens:" + token, TimeUnit.MINUTES);
        if (Objects.isNull(expire)) return false;
        else if (expire > 0) {
            if (expire < 30) redisTemplate.expire("tokens:" + token, 3, TimeUnit.HOURS);
            return true;
        } else return false;
    }

    public Object getValue(String token) {
        return redisTemplate.opsForValue().get("tokens:" + token);
    }

    public void deleteToken(String token) {
        redisTemplate.delete("tokens:" + token);
    }
}
