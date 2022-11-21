package lab.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab1Application.class, args);
	}
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route("continents", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/continents/{id}", "/api/continents")
						.uri("http://localhost:8081"))
				.route("countries", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/countries", "/api/countries/*", "/api/continents/{continent}/countries", "/api/continents/{continent}/countries/*")
						.uri("http://localhost:8082"))
				.build();
	}
}
