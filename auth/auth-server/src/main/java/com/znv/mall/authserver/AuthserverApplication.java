package com.znv.mall.authserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


/***
 * ComponentScan指定包之后，SpringBoot会自动描该包下面的功能，如Controller
 * EnableAutoConfiguration 使用自动配置
 * EnableScheduling 开启定时任务
 */
@SpringBootApplication
@ComponentScan("com.znv.mall.authserver")
//@MapperScan("com.znv.mall.authserver.dao.mapper")
@EnableScheduling
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
public class AuthserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthserverApplication.class, args);
	}
}
