package com.sistema.uab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class NoticiasUabV1Application {

	public static void main(String[] args) {
		SpringApplication.run(NoticiasUabV1Application.class, args);
	}

		@Bean
		public SpringDataDialect springDataDialect() {
			return new SpringDataDialect();
		}
}
