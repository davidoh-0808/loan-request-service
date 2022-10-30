package com.neo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.neo.config.MyBatisProperties;

@SpringBootApplication
@EnableConfigurationProperties(MyBatisProperties.class)
public class HdSmileApplication {

	public static void main(String[] args) {
		SpringApplication.run(HdSmileApplication.class, args);
	}

}
