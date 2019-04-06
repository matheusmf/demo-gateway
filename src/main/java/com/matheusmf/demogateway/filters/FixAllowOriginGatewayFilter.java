package com.matheusmf.demogateway.filters;

import java.util.Collections;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;

import reactor.core.publisher.Mono;

public class FixAllowOriginGatewayFilter extends AbstractGatewayFilterFactory<FixAllowOriginGatewayFilter.Config> {

	public FixAllowOriginGatewayFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				ServerHttpResponse response = exchange.getResponse();
				HttpHeaders headers = response.getHeaders();
		        headers.put("Access-Control-Allow-Origin", Collections.singletonList("*"));
			}));
		};
	}
	
	public static GatewayFilter filter() {
		return new FixAllowOriginGatewayFilter().apply(new FixAllowOriginGatewayFilter.Config());
	}

	public static class Config {
	}

}
