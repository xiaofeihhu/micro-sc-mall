
package com.znv.demo.common.exception;

/**
 * 
  * @ClassName: BusinessException
  * @Description: 业务异常类,使用场景:程序并未出现执行异常情况,人为抛出异常信息。
  * 例如：登录功能,账号不存在或密码错误时,可抛出一个业务异常,自定义异常信息
  * @author znv
  * @date 2018/5/16 16:29
  *
 */
public class BusinessException extends RuntimeException{
     /**
      * @Fields serialVersionUID :
      */
    private static final long serialVersionUID = 1L;
    
    private Integer code;
    private String message;

    public BusinessException(String message) {
        super();
        this.message = message;
    }
    
    public BusinessException(int code,String message) {
        super();
        this.code=code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
