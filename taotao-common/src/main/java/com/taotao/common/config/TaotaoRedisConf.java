package com.taotao.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 淘淘redis配置类
 */
@Configuration
@ConfigurationProperties(prefix = "taotao.redis")
@Data
public class TaotaoRedisConf {
	private String token;
	private String orderId;
}
