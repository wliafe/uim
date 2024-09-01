package com.wliafe.common.security.filter;

import com.wliafe.common.security.authentication.AuthenticationToken;
import com.wliafe.common.security.details.BaseDetails;
import com.wliafe.common.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 权限校验过滤器
 *
 * @author wliafe
 * @date 2023/1/12 0:08
 **/
@Component
public class ServerAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        BaseDetails baseDetails = (BaseDetails) tokenService.getValue(token);
        if (Objects.isNull(baseDetails)) throw new ServletException("用户未登录");
        AuthenticationToken authentication = new AuthenticationToken(baseDetails, token, baseDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
