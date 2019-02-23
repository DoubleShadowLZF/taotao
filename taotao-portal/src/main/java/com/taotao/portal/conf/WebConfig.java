package com.taotao.portal.conf;

import com.taotao.portal.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description
 * @Author Double
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	/**
	 * 未登录用户进行订单相关操作时，强制登陆
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/order/**");
	}
}
