package com.wliafe.common.security.details;

import com.alibaba.fastjson2.annotation.JSONField;
import com.wliafe.admin.domain.User;
import com.wliafe.common.service.CodeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailCodeDetails extends BaseDetails {

    private CodeService codeService;

    public EmailCodeDetails(User user, String username, CodeService codeService, List<String> permissions) {
        super(user, username, permissions);
        this.codeService = codeService;
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        String code = codeService.getCode(username);
        if (Objects.isNull(code)) throw new RuntimeException("未能获取验证码,请重新生成验证码");
        return code;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return codeService.isCodeNonExpire(username);
    }
}
