package com.znv.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author znv
 * @ClassName:
 * @Description: 公共配置注入
 * @date 2018-5-18 13:42
 */
@Configuration
@ConfigurationProperties(prefix = "service.common")
@Data
public class CommonConfig {

    /**
     * 服务所在根路径
     */
    private String serverPath;






}
