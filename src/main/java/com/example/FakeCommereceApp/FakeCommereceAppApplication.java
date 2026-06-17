package com.example.FakeCommereceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FakeCommereceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeCommereceAppApplication.class, args);
	}

}
