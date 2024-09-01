package com.wliafe.common.security.provider;

import com.wliafe.common.security.authentication.EmailCodeAuthenticationToken;
import com.wliafe.common.security.details.EmailCodeDetails;
import com.wliafe.common.security.service.EmailCodeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author wliafe
 * @date 2023/1/12 0:15
 **/
@Component
public class EmailCodeAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private EmailCodeDetailsService emailCodeDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailCodeDetails emailCodeDetails = (EmailCodeDetails) emailCodeDetailsService.loadUserByUsername((String) authentication.getPrincipal());
        if (Objects.isNull(emailCodeDetails)) throw new InternalAuthenticationServiceException("无法获取用户信息");
        if (!emailCodeDetails.getPassword().equals(authentication.getCredentials()))
            throw new InternalAuthenticationServiceException("验证码填写错误");
        EmailCodeAuthenticationToken authenticationResult = new EmailCodeAuthenticationToken(emailCodeDetails, null, emailCodeDetails.getAuthorities());
        authenticationResult.setDetails(authentication.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
