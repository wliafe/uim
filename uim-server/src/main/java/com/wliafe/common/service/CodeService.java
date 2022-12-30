package com.wliafe.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @auther wliafe
 */
@Service
public class CodeService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
     * @return 成功 true 失败 false
     */
    public boolean checkCode(String string, String code) {
        return code.equals(redisTemplate.opsForValue().get("codes:" + string));
    }
}
