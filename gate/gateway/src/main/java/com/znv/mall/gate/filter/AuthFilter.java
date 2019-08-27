package com.znv.mall.gate.filter;

import com.znv.mall.authclient.service.IAuthService;
import com.znv.mall.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;


/**
 * @Auther: yf
 * @Date: 2019-6-3
 * @Description: 权限控制过滤器
 */
@Component
@RefreshScope
@Slf4j
public class AuthFilter implements GlobalFilter,Ordered {

	@Value("${auth.onoff}")
	boolean isOnOff;

	@Autowired
	IAuthService iAuthService;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 开关关闭  不走鉴权
		if (!isOnOff) {
			return chain.filter(exchange);
		}
		ServerHttpRequest request = exchange.getRequest();
		String path = request.getURI().getPath();
		if (!isAllowRequest(exchange,path)) {
			// 校验当前请求是否有权限
			String token = request.getHeaders().getFirst("Authorization");
			// TODO 此处需校验token是否正常  暂时改为只校验session信息
			Result session = iAuthService.getSession();
			if (session.isSuccess() && session.getData()!=null) {
				log.info("session check ok! session: {},path: {}",session.getData(), path);
			} else {
				log.info("session check ng! session: {},path: {}",session.getData(), path);
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}
//			if (!iAuthService.hasPermission(token,path,request.getMethod().name())) {
//				log.info("permission check ng! token: {},path: {}",token, path);
//				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//				return exchange.getResponse().setComplete();
//			}

		}


		return chain.filter(exchange);
	}

	private boolean isAllowRequest(ServerWebExchange exchange,String path) {
//        Mono<WebSession> session = exchange.getSession();
//        exchange.getSession().flatMap(webSession -> {
//            log.info("websession: {}", webSession.getId());
//            if (webSession.getAttribute(webSession.getId())!=null) {
//                return true;
//            }
//        });


		// 校验是否白名单的请求url
		if (iAuthService.ignoreAuthentication(path)) {
			log.info("ignore Authentication path: {}",path);
			return true;
		}

        return false;
	}

	@Override
	public int getOrder() {
		return -100;
	}
}
