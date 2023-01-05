package com.wliafe.common.security.exception;

import com.alibaba.fastjson.JSON;
import com.wliafe.common.domain.AjaxResult;
import com.wliafe.common.utils.WebUtils;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        String json = JSON.toJSONString(AjaxResult.error("用户未登录"));
        WebUtils.renderString(response, json);
    }
}
