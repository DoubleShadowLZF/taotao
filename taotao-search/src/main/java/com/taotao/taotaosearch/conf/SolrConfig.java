package com.taotao.taotaosearch.conf;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description solr文件配置
 * @Author Double
 */
@ConfigurationProperties(prefix = "taotao.search.solr")
@Data
@Configuration
@Accessors(chain = true)
public class SolrConfig {
	private String collection;
}
