package com.wliafe.common.domain;

import com.wliafe.admin.domain.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wliafe
 * @date 2023/1/21 9:48
 **/
@Data
@Schema(description = "角色参数")
public class RoleParameters implements Serializable {
    @Schema(description = "角色实体类")
    private Role role;
    @Schema(description = "角色权限id数组")
    private List<String> menuIdList;
}
