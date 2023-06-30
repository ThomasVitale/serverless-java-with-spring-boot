package com.thomasvitale.javafunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.RabbitMQContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestJavaFunctionApplication {

	@Bean
	@ServiceConnection
	RabbitMQContainer rabbitContainer() {
		return new RabbitMQContainer("rabbitmq:3.12.1-management");
	}

	public static void main(String[] args) {
		SpringApplication.from(JavaFunctionApplication::main).with(TestJavaFunctionApplication.class).run(args);
	}

}
