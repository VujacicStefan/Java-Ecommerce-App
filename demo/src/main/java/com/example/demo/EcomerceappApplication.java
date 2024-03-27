package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan("com.example.demo.dao")
@EntityScan("com.example.demo.Entity")
@EnableJpaRepositories("com.example.demo.dao")
public class EcomerceappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomerceappApplication.class, args);
	}


}
