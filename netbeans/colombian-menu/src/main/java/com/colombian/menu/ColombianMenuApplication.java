package com.colombian.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@SpringBootApplication
public class ColombianMenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColombianMenuApplication.class, args);
	}

}
