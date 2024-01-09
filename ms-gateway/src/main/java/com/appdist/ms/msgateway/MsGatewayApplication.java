package com.appdist.ms.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("users-service", r -> r.path("/v1/api/user/**")
						.uri("http://localhost:8081"))
				.route("files-service", r -> r.path("/v1/api/files/**")
						.uri("http://localhost:8082"))
				.route("files-service", r -> r.path("/**")
						.uri("http://localhost:8080"))
				.build();
	}

}
