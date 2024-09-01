package com.wliafe.common.security.exception;

import com.alibaba.fastjson.JSON;
import com.wliafe.common.domain.MyResult;
import com.wliafe.common.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败处理器
 *
 * @author wliafe
 * @date 2023/1/12 0:08
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String json = JSON.toJSONString(MyResult.error("用户权限不足"));
        WebUtils.renderString(response, json);
    }
}
