package com.taotao.manager;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Description
 */
@SpringBootApplication
public class TaotaoWebApplication  extends SpringBootServletInitializer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(TaotaoWebApplication.class).web(true).run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TaotaoWebApplication.class);
	}
}
