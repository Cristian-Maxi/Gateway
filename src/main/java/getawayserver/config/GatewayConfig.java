package getawayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GatewayConfig {
    @Bean
    public RouteLocator customRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("user-service", r -> r.path("/api/user/**")
                        .uri("lb://user"))
                .route("product-service", r -> r.path("/api/product/**")
                        .uri("lb://product"))
                .route("order-service", r -> r.path("/api/order/**")
                        .uri("lb://order"))
                .build();
    }
}
