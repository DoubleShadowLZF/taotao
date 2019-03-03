package com.taotao.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.taotao.rest", "com.taotao.manager"})
public class TaotaoRestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoRestApplication.class, args);
	}
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TaotaoRestApplication.class);
	}*/
}
