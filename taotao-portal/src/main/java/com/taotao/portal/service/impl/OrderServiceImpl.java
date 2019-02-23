package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.taotao.common.config.UrlConfig;
import com.taotao.portal.service.IOrderService;
import com.taotao.manager.pojo.qo.OrderQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author Double
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private UrlConfig urlConfig;

	@Override
	public String createOrder(OrderQo query, HttpServletRequest request, HttpServletResponse response) {
		String createOrderUrl = urlConfig.getCreateOrder();
		JSONObject json = (JSONObject) JSONObject.toJSON(query);
		RestTemplate rest = new RestTemplate();
		Object entity = rest.postForObject(createOrderUrl,json, Object.class);
//		HttpHeaders headers = entity.getHeaders();
		return "";
	}
}
