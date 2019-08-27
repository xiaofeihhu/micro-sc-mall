package com.znv.mall.gate.filter;

/**
 * @Auther: yf
 * @Date: 2019/7/31
 * @Description:
 */

import com.znv.mall.gate.configration.DocumentationConfig;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

@Component

public class SwaggerHeaderFilter extends AbstractGatewayFilterFactory {

    private static final String HEADER_NAME = "X-Forwarded-Prefix";

    private static final String HOST_NAME = "X-Forwarded-Host";

    @Override

    public GatewayFilter apply(Object config) {

        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();

            String path = request.getURI().getPath();

            if (!StringUtils.endsWithIgnoreCase(path, DocumentationConfig.API_URI)) {

                return chain.filter(exchange);

            }

            String basePath = path.substring(0, path.lastIndexOf(DocumentationConfig.API_URI));

            ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();

            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();

            return chain.filter(newExchange);

        };

    }

}