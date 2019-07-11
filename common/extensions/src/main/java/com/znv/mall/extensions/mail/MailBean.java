package com.znv.mall.extensions.mail;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: yf
 * @Date: 2019-6-26
 * @Description:
 */
@Data
public class MailBean implements Serializable {
    private String recipient;   //邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容

}