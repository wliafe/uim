package com.wliafe.admin.filter;

import com.wliafe.common.constant.HttpStatus;
import com.wliafe.common.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Order(1)
@WebFilter(urlPatterns = "/*")
public class AfterLoginFilter implements Filter {
    @Autowired
    private TokenService tokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("AfterLoginFilter过滤器开启");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!request.getRequestURL().toString().contains("/after-login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        log.info("AfterLoginFilter过滤器执行");
        if (tokenService.checkToken(request.getHeader("token")))
            filterChain.doFilter(servletRequest, servletResponse);
        else
            response.sendError(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("AfterLoginFilter过滤器销毁");
    }
}
