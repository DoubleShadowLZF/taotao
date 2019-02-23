package com.taotao.common.config.auto;

import com.taotao.common.client.FtpClient;
import com.taotao.common.config.FtpProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author Double
 */
@Configuration
@ConditionalOnWebApplication //web应用才生效
@EnableConfigurationProperties(FtpProperties.class)
@Slf4j
public class FtpAutoConfiguration {

	@Autowired
	private FtpProperties ftpProperties;

	public FtpAutoConfiguration() {
		log.info("FtpAutoConfiguration自动注入...");
	}


	@Bean
	@ConditionalOnMissingBean(name = {"ftpClient"})
	public FtpClient ftpClient() {
		FtpClient ftpClient = new FtpClient(ftpProperties);
		log.info("ftpClient对象注入到Spring容器中");
		return ftpClient;
	}
}
