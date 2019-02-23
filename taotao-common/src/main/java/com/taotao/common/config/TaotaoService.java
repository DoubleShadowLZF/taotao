package com.taotao.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author Double
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "taotao.service")
public class TaotaoService {
	private String manager;
	private String rest;
	private String portal;
	private String search;
	private String sso;
}
