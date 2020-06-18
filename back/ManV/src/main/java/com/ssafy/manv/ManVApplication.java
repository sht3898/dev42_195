package com.ssafy.manv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ManVApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManVApplication.class, args);
	}

	//http://localhost:8197/swagger-ui.html#
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
