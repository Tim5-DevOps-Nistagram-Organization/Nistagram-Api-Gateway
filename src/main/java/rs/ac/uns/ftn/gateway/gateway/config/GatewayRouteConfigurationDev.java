package rs.ac.uns.ftn.gateway.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
public class GatewayRouteConfigurationDev {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("order",
                        r-> r.path("/auth/**")
                                .filters(f->f.rewritePath("/order/(?<path>.*)", "/$\\{path}"))
                                .uri("lb://nistagram-auth")
                )
                .route("product",
                r-> r.path("/product/**")
                        .filters(f->f.rewritePath("/product/(?<path>.*)", "/$\\{path}"))
                        .uri("lb://product")
                )
                .route("report",
                        r-> r.path("/report/**")
                                .filters(f->f.rewritePath("/report/(?<path>.*)", "/$\\{path}"))
                                .uri("lb://report")
                )
                .route("front",
                        r-> r.path("/")
                                .filters(f->f.rewritePath("/", "/index.html"))
                                .uri("http://localhost:8088")
                )
                .build();
    }
}