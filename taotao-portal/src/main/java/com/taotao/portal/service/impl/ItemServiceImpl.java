package com.taotao.portal.service.impl;

import com.taotao.common.config.UrlConfig;
import com.taotao.portal.service.IitemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 */
@Service
@Slf4j
public class ItemServiceImpl implements IitemService {
	@Autowired
	private UrlConfig urlConfig;

	@Override
	public Map getItem(Long itemId) {
		Map<String,Object> param = new HashMap<>();
		param.put("itemId",itemId);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> ret = restTemplate.getForEntity(urlConfig.getItemUrl(), Map.class,param);
		if(ret.getStatusCode() == HttpStatus.OK){
			Map body = ret.getBody();
			String img = (String) body.get("image");
			String[] split = img.split(",");
			if(split.length > 1){
				body.put("img",split[0]);
			}
			return body;
		}else{
			log.debug("获取商品信息出错：{}",ret);
		}
		return null;
	}
}
