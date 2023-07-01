package com.thomasvitale.javafunction;

import java.util.function.Consumer;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StreamFunctionApplication {

	private static final Logger log = LoggerFactory.getLogger(StreamFunctionApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StreamFunctionApplication.class, args);
	}

	@Bean
	Function<Instrument,String> uppercase() {
		return instrument -> {
			log.info("Converting {} to uppercase", instrument.name());
			return instrument.name().toUpperCase();
		};
	}

	@Bean
	Function<String,Skill> sentence() {
		return instrument -> {
			log.info("Building skill with {}", instrument);
			return new Skill("I play the " + instrument);
		};
	}

	@Bean
	Consumer<Skill> print() {
		return skill -> log.info(skill.content());
	}

}

record Instrument(String name){}
record Skill(String content){}
