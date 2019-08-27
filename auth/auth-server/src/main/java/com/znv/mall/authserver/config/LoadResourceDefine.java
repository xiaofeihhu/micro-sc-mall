package com.znv.mall.authserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
class LoadResourceDefine {

//    @Autowired
//    private IResourceService resourceService;

    @Autowired
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @Bean
    public Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes() {
//        Set<Resource> resources = resourceService.findAll();
//        Map<RequestMatcher, ConfigAttribute> map = resources.stream()
//                .collect(Collectors.toMap(
//                        resource -> {
//                            MvcRequestMatcher mvcRequestMatcher = new MvcRequestMatcher(mvcHandlerMappingIntrospector, resource.getUrl());
//                            mvcRequestMatcher.setMethod(HttpMethod.resolve(resource.getMethod()));
//                            return mvcRequestMatcher;
//                        },
//                        resource -> new SecurityConfig(resource.getCode())
//                        )
//                );
        Map<RequestMatcher, ConfigAttribute> map = new HashMap<>();
        log.info("resourceConfigAttributes:{}", map);
        return map;
    }
}
