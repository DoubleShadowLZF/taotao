package com.taotao.common.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "taotao.ftp")
public class FtpProperties {
	private String host;
	private Integer port;
	private String username;
	private String password;
	private String basePath;
	private String filePath;
}
