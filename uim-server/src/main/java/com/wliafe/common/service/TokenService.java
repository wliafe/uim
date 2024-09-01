package com.wliafe.common.service;

import com.wliafe.common.security.details.BaseDetails;
import com.wliafe.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * token存取服务
 *
 * @author wliafe
 * @date 2023/1/12 0:17
 **/
@Service
public class TokenService {
    @Value("${token.time:14}")
    private Integer time;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public static String getToken() {
        return UUIDUtil.getUUID();
    }

    public String setToken(Object data) {
        String token = getToken();
        redisTemplate.opsForValue().set("tokens:" + token, data, time, TimeUnit.DAYS);
        return token;
    }

    private void removeToken(String token) {
        redisTemplate.delete("tokens:" + token);
    }

    public Object getValue(String token) {
        return redisTemplate.opsForValue().get("tokens:" + token);
    }

    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BaseDetails baseDetails = (BaseDetails) getValue((String) authentication.getCredentials());
        return baseDetails.getVerification().getUserId();
    }

    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        removeToken((String) authentication.getCredentials());
    }
}
