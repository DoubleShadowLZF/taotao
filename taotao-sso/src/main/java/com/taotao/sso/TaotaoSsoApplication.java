package com.taotao.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 集成 SpringBootServletInitializer ，重写Configure方法，使用Tomcat启动
 */
@SpringBootApplication(scanBasePackages={"com.taotao.sso", "com.taotao.manager.dao"})
public class TaotaoSsoApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TaotaoSsoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TaotaoSsoApplication.class, args);
	}
}

