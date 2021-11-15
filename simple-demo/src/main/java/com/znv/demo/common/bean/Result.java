
package com.znv.demo.common.bean;

/**
  * @ClassName: Result
  * @Description: 封装返回对象格式类型
  * @author znv
  * @date 2018/5/16 16:29
  *
  */
public class Result {
    /**
     * 默认返回状态码
     */
    private int code = 200;
    /**
     * 默认返回状态说明
     */
    private String message = "ok";
    /**
     * 返回数据对象
     */
    private Object data;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 耗时（ms）
     */
    private long cost;

    public Result() {
        super();
    }

    public Result(Object data) {
        this.data = data;
    }
    public Result(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * 返回结果统一设置code以及message
     *
     * @param code
     * @param message
     */
    public void setResutCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
}