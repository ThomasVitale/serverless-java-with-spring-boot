package com.thomasvitale.javafunction;

import java.util.function.Consumer;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaFunctionApplication {

	private static final Logger log = LoggerFactory.getLogger(JavaFunctionApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JavaFunctionApplication.class, args);
	}

	@Bean
	Function<String,Instrument> uppercase() {
		return instrument -> {
			log.info("Converting {} to uppercase", instrument);
			return new Instrument(instrument.toUpperCase());
		};
	}

	@Bean
	Function<Instrument,Skill> sentence() {
		return instrument -> {
			log.info("Building skill with {}", instrument.name());
			return new Skill("I play the " + instrument.name());
		};
	}

	@Bean
	Consumer<Skill> print() {
		return skill -> log.info(skill.content());
	}

}

record Instrument(String name){}
record Skill(String content){}
