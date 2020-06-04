package com.znv.demo.common.exception;

/**
 * @ClassName: ResultCodeEnum
 * @Description: 自定义错误异常码
 * @author znv
 * @date 2018/5/16 16:29
 *
 */
public enum ResultCodeEnum {

    NORMAL(200, "正常"),
    CREATEORUPDATESUCCESS(201, "用户新建或修改数据成功"),
    ACCEPTED(202, "表示一个请求已经进入后台排队（异步任务）"),
    DELETESUCCESS(204, "用户删除数据成功"),
    INVALIDREQUEST(400, "用户发出的请求有错误，服务器没有进行新建或修改数据的操作"),
    UNAUTHORIZED(401, "用户没有权限（令牌、用户名、密码错误）"),
    FORBIDDEN(403, "用户得到授权（与401错误相对），但是访问是被禁止的"),
    NOTFOUND(404, "请求针对的是不存在的记录，服务器没有进行操作"),
    NOTACCEPTABLE(406, "请求的格式不可得"),
    GONE(410, "用户请求的资源被永久删除，且不会再得到的"),
    UNPROCESABLEENTITY(422, "创建一个对象时，发生一个验证错误"),
    SYSTEMERROR(500, "系统异常");

    private int code;
    private String name;

    private ResultCodeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
