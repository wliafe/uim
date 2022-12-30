package com.wliafe.common.utils;

public class RoleUtils {
    /**
     * 测试角色id长度
     *
     * @param roleId 角色id
     * @return 正确 true 错误 false
     */
    public static boolean isLengthRight(String roleId) {
        return roleId.length() == 32;
    }

    /**
     * 测试角色id长度
     *
     * @param roleId 角色id
     * @return 正确 false 错误 true
     */
    public static boolean isLengthWrong(String roleId) {
        return !isLengthRight(roleId);
    }

    /**
     * 是超管
     *
     * @param roleId 角色id
     * @return 是 true 不是 false
     */
    public static boolean isAdmin(String roleId) {
        return roleId.equals("admin");
    }

    /**
     * 不是超管
     *
     * @param roleId 角色id
     * @return 是 false 不是 true
     */
    public static boolean isNotAdmin(String roleId) {
        return !isAdmin(roleId);
    }
}
