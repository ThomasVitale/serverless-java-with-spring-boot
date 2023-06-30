package com.thomasvitale.nativeservice;

import java.util.List;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
@RegisterReflectionForBinding(Artist.class)
public class NativeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NativeServiceApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route()
			.GET("/", request -> ServerResponse.ok().bodyValue("GraalVM Rocks!"))
			.GET("/artists", request -> ServerResponse.ok().bodyValue(List.<Artist>of(
				new Artist("Deep Purple"),
				new Artist("Dolly Parton"),
				new Artist("Shemekia Copeland")
			)))
			.build();
	}

}

record Artist(String name){}
