package getawayserver.config;

import getawayserver.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator customRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("user-service", r -> r.path("/api/auth/**")
                        .uri("lb://user"))
                .route("user-service", r -> r.path("/api/user/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://user"))
                .route("product-service", r -> r.path("/api/product/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://product"))
                .route("order-service", r -> r.path("/api/order/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://order"))
                .build();
    }
}
