package com.znv.demo.filter;

import com.znv.demo.common.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MainFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 过滤，如果例外路径可以通过，如果不是例外则验证是否已经登录，如果没有登录，则返回错误
     *
     * @param servletRequest  请求
     * @param servletResponse 回复
     * @param filterChain     过滤器链
     * @throws IOException      向前端发送错误失败
     * @throws ServletException 过滤失败
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {

        long st=System.currentTimeMillis();
        ThreadLocalUtil.set(st);
        MDC.put("reqId", String.valueOf(st));
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "5000");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,Authorization,Token");
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    @Override
    public void destroy() {

    }

}