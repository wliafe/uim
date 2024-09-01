package com.wliafe.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 验证码服务
 *
 * @author wliafe
 * @date 2023/1/12 0:16
 **/
@Service
public class CodeService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取验证码
     *
     * @return 随机4位验证码
     */
    public static String randomCode() {
        return Integer.valueOf((int) (Math.random() * 9000 + 1000)).toString();
    }

    /**
     * 设置验证码
     *
     * @param string key
     * @return 验证码
     */
    public String setCode(String string) {
        String code = randomCode();
        redisTemplate.opsForValue().set("codes:" + string, code, 10, TimeUnit.MINUTES);
        return code;
    }

    /**
     * 检查验证码
     *
     * @param string key
     * @param code   验证码
     * @return 不正确 true 正确 false
     */
    public boolean codeNotRight(String string, String code) {
        return !code.equals(getCode(string));
    }

    public boolean isCodeNonExpire(String string) {
        string = string == null ? "" : string;
        Long codeExpire = redisTemplate.getExpire("codes" + string, TimeUnit.SECONDS);
        if (Objects.isNull(codeExpire)) return false;
        else return codeExpire > 0;
    }

    /**
     * 获取验证码
     *
     * @param string key
     * @return 验证码
     */
    public String getCode(String string) {
        return redisTemplate.opsForValue().get("codes:" + string);
    }
}
