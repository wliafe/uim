package com.wliafe.common.security.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author wliafe
 * @date 2023/1/20 17:38
 **/
public class EmailCodeAuthenticationToken extends AuthenticationToken{
    public EmailCodeAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public EmailCodeAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
