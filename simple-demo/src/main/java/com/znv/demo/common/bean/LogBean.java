package com.znv.demo.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yf
 * @Date: 2020/6/9
 * @Description:
 */
@Data
public class LogBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String logId;           // 日志id
    private String type;            // 请求类型
    private String title;           // 告警标题
    private String remoteAddr;      // 请求地址
    private String requestUri;      // 接口url
    private String method;          // 方法
    private String params;          // 入参
    private String exception;       // 异常信息
    private String startTime;       // 开始时间
    private String endTime;         // 结束时间
    private long cost;              // 耗时（ms）
    private String result;          // 返回结果
    private String status;          // 状态码
    private String userId;          // 用户id
}
