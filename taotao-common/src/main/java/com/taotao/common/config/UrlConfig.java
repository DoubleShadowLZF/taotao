package com.taotao.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Url集合类
 * @Author Double
 */
@Configuration
@ConfigurationProperties(prefix = "taotao.url")
@Data
public class UrlConfig {
	/**
	 * 获取广告位信息Url
	 */
	private String adListUrl;
	/**
	 * 搜索框，搜索信息，匹配商品title
	 */
	private String searchUrl;
	/**
	 * 获取商品信息Url
	 */
	private String itemUrl;
	/**
	 * 获取商品规格参数
	 */
	private String itemParamItem;
	/**
	 * 获取用户cookie信息
	 */
	private String userToken;
	/**
	 * 登陆页面
	 */
	private String login;
	/**
	 * post:创建订单
	 */
	private String createOrder;
	/**
	 * delete:删除购物车列表
	 */
	private String cartList;
}
