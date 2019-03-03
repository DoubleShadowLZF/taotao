package com.taotao.manager.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description FastDFS配置类
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "taotao.fast-dfs")
public class FdfsProperties {

	private String resHost;
	private String fdfsStoragePort;
	private String nginxFdfsPort;

	public FdfsProperties(){
		log.debug("FdfsProperties init..");
	}
}
