package com.znv.mall.authserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Slf4j
//@ConditionalOnProperty(name="spring.security.oauth2.enable",havingValue = "true")
public class WebServerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("spring.security.oauth2.enable")
    private String oauth2Enable;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("HttpSecurity configure method");
        http.csrf().disable();
        if ("true".equalsIgnoreCase(oauth2Enable)) {
            http.authorizeRequests()
                    .antMatchers("/", "/login/**").permitAll()
                    .antMatchers("/actuator/**").permitAll()
                    .antMatchers(
                            HttpMethod.GET,
                            "**/v2/api-docs",
                            "**/swagger-resources/**",
                            "**/swagger-ui.html**",
                            "**/webjars/**",
                            "favicon.ico"
                    ).permitAll()
                    .anyRequest().authenticated();
        } else {
            http.authorizeRequests()
                    .antMatchers("**/**","**").permitAll();
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/actuator/**")
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/v2/api-docs",
//                        "/swagger-resources/**",
//                        "/swagger-ui.html**",
//                        "/webjars/**",
//                        "favicon.ico"
//                );
    }
}