package com.wliafe.common.utils;

public class RoleUtils {
    /**
     * 测试角色id长度
     *
     * @param roleId 角色id
     * @return 正确 true 错误 false
     */
    public static boolean checkLength(String roleId) {
        return roleId.length() == 32;
    }
}
