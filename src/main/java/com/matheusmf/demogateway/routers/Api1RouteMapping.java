package com.matheusmf.demogateway.routers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.matheusmf.demogateway.configs.UrlConfiguration;
import com.matheusmf.demogateway.compositions.Api1Composition;
import com.matheusmf.demogateway.filters.FixAllowOriginGatewayFilter;

import reactor.core.publisher.Mono;

@RestController
public class Api1RouteMapping {

    @Autowired
    private UrlConfiguration urls;
    
    @Autowired
    private Api1Composition composition;

    @Bean
    public RouteLocator api1Routes(RouteLocatorBuilder builder) {
    	return builder
    			.routes()
    			.route("api", p -> p.path(this.urls.getExampleApi1Url()+ "**")
    			.filters(f -> f
    					.rewritePath(this.urls.getExampleApi1Url() + "(?<RID>.*)", "/${RID}")
    					.modifyResponseBody(String.class, String.class, (exchange, s) -> Mono.just(composition.compose(s)))
    					.filter(FixAllowOriginGatewayFilter.filter())
    					)
    			.uri(this.urls.getExampleApi1Url()))
    			.build();
    }
    
}
