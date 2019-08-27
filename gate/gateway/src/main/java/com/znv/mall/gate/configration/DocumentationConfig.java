package com.znv.mall.gate.configration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    public static final String API_URI = "/v2/api-docs";

    public static final List<String> SWAGGER_NOT_SHOW_SERVICES = new ArrayList<String>();

    public static final String EUREKA_SUB_PRIX = "CompositeDiscoveryClient_";

    private final DiscoveryClientRouteDefinitionLocator routeLocator;

    public DocumentationConfig(DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator) {
        this.routeLocator = discoveryClientRouteDefinitionLocator;
    }

    static {
        SWAGGER_NOT_SHOW_SERVICES.add("config");
        SWAGGER_NOT_SHOW_SERVICES.add("gate");
        SWAGGER_NOT_SHOW_SERVICES.add("turbine");
        SWAGGER_NOT_SHOW_SERVICES.add("admin");
        SWAGGER_NOT_SHOW_SERVICES.add("authorization");
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        //在这里遍历的时候，可以排除掉敏感微服务的路由
        routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
            String routeId = routeDefinition.getId().substring(EUREKA_SUB_PRIX.length());
            if (SWAGGER_NOT_SHOW_SERVICES.contains(routeId.toLowerCase())) {
                return;
            }
            resources.add(swaggerResource(routeDefinition.getId().substring(EUREKA_SUB_PRIX.length()),
                    routeDefinition.getPredicates().get(0).getArgs().get("pattern").replace("/**", API_URI),"2.0"));
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }


}
