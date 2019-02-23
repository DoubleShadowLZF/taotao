package com.taotao.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author Double
 */
@Configuration
@ConfigurationProperties(prefix = "taotao.cookie")
@Data
public class CookieConfig {
	/**
	 * cookie 中购物车的名称
	 */
	private String cartName;
	/**
	 * cookie 中登陆用户的cookie名称
	 */
	private String token;
}
