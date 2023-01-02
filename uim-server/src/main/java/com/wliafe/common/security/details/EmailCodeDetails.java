package com.wliafe.common.security.details;

import com.wliafe.admin.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailCodeDetails extends BaseDetails {

    private String code;

    public EmailCodeDetails(User user, String code, List<String> permissions) {
        super(user, permissions);
        this.code = code;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return code;
    }
}
