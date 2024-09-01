package com.wliafe.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wliafe
 * @date 2023/1/24 17:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Verification {
    private String userId;
    private String phone;
    private String email;
}
