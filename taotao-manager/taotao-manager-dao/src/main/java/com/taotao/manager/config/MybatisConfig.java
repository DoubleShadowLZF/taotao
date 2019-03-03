package com.taotao.manager.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
     */
@Configuration
@MapperScan("com.taotao.manager.dao.mapper")
public class MybatisConfig {
}
