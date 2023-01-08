package com.wliafe.common.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 操作消息提醒
 * 键值对信息返回
 */
@Data
@Schema(description = "请求返回类型")
public class AjaxResult {
    @Schema(description = "校验码", example = "200")
    private Integer code;
    @Schema(description = "返回消息", example = "请求成功")
    private String message;
    @Schema(description = "返回数据")
    private Object data;

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param message  返回内容
     * @param data 数据对象
     */
    public AjaxResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param message 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String message) {
        return AjaxResult.success(message, null);
    }

    /**
     * 返回成功消息
     *
     * @param message  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String message, Object data) {
        return new AjaxResult(HttpStatus.OK.value(), message, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param message 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String message) {
        return AjaxResult.error(message, null);
    }

    /**
     * 返回错误消息
     *
     * @param message  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String message, Object data) {
        return new AjaxResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param message  返回内容
     * @return 警告消息
     */
    public static AjaxResult error(int code, String message) {
        return new AjaxResult(code, message, null);
    }
}
