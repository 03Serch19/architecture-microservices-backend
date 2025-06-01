package com.eureka_server_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Microservice4EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Microservice4EurekaApplication.class, args);
	}
}
