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
                .route("campaign",
                r-> r.path("/campaign/**")
                        .filters(f->f.rewritePath("/campaign/(?<path>.*)", "/$\\{path}"))
                        .uri("lb://nistagram-campaign")
                )
                .route("media",
                        r-> r.path("/media/**")
                                .filters(f->f.rewritePath("/media/(?<path>.*)", "/$\\{path}"))
                                .uri("lb://nistagram-media")
                ).route("post",
                        r-> r.path("/post/**")
                                .filters(f->f.rewritePath("/post/(?<path>.*)", "/$\\{path}"))
                                .uri("lb://nistagram-post")
                ).route("search",
                        r-> r.path("/search/**")
                                .filters(f->f.rewritePath("/search/(?<path>.*)", "/$\\{path}"))
                                .uri("lb://nistagram-search")
                ).route("user",
                        r-> r.path("/user/**")
                                .filters(f->f.rewritePath("/user/(?<path>.*)", "/$\\{path}"))
                                .uri("lb://nistagram-user")
                )
                .route("front",
                        r-> r.path("/")
                                .filters(f->f.rewritePath("/", "/index.html"))
                                .uri("http://localhost:8088")
                )
                .build();
    }
}