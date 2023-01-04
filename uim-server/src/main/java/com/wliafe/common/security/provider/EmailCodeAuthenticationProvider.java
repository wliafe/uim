package com.wliafe.common.security.provider;

import com.wliafe.common.security.authentication.AuthenticationToken;
import com.wliafe.common.security.service.EmailCodeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmailCodeAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private EmailCodeDetailsService emailCodeDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationToken authenticationToken = (AuthenticationToken) authentication;
        UserDetails userDetails = emailCodeDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (Objects.isNull(userDetails)) throw new InternalAuthenticationServiceException("无法获取用户信息");
        if (!userDetails.getPassword().equals(authentication.getCredentials()))
            throw new InternalAuthenticationServiceException("验证码填写错误");
        AuthenticationToken authenticationResult = new AuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthenticationToken.class.isAssignableFrom(authentication);
    }
}
