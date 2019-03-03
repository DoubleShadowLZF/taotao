package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.taotao.common.config.TaotaoRedisConf;
import com.taotao.common.config.TaotaoService;
import com.taotao.manager.pojo.domain.TbContent;
import com.taotao.common.config.UrlConfig;
import com.taotao.portal.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 */
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

	@Autowired
	private UrlConfig urlConfig;

	@Autowired
	TaotaoRedisConf taotaoRedisConf;

	@Autowired
	TaotaoService taotaoServiceName;

	private Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);

	@Override
	@Cacheable(cacheNames = "adItemList")
	public String getAdItemList() {
		RestTemplate restTemplate = new RestTemplate();
		log.debug("请求路径：{}",urlConfig.getAdListUrl());
		List<Map<String,Object>> tbContents = restTemplate.getForObject(urlConfig.getAdListUrl(), List.class);
		logger.debug("tbContents:{}",tbContents);
		List result = new ArrayList<>();
		for (Map<String,Object> map : tbContents) {
			Map item = new HashMap(8);
			item.put("height",240);
			item.put("width",670);
			item.put("src",map.get("pic"));
			item.put("heightB",240);
			item.put("widthB",550);
			item.put("srcB",map.get("pic2"));
			item.put("alt",map.get("content"));
			item.put("href",map.get("url"));
			result.add(item);
		}

		return JSONObject.toJSONString(result);
	}


	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		List<TbContent> tbContents = restTemplate.getForObject("http://manager.taotao.com/content/adItemList", List.class);
		System.out.println(tbContents);
	}
}
