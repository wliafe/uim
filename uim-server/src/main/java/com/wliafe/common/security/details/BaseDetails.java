package com.wliafe.common.security.details;

import com.alibaba.fastjson2.annotation.JSONField;
import com.wliafe.common.domain.Verification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wliafe
 * @date 2023/1/12 0:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDetails implements UserDetails {
    protected Verification verification;
    protected String username;
    protected List<String> permissions;

    @JSONField(serialize = false)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.isNull(permissions)) return null;
        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        return null;
    }

    @JSONField(serialize = false)
    @Override
    public String getUsername() {
        return username;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
