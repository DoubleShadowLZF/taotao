package com.taotao.portal.service.impl;

import com.taotao.manager.pojo.domain.TbItemParamItem;
import com.taotao.common.config.UrlConfig;
import com.taotao.portal.service.IItemParamItemService;
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
public class ItemParamItemServiceImpl implements IItemParamItemService {

	@Autowired
	private UrlConfig urlConfig;

	@Override
	public TbItemParamItem getItemParamItem(Long itemId) {
		RestTemplate rest = new RestTemplate();
		Map<String, Long> map = new HashMap<>(1);
		map.put("itemId", itemId);
		ResponseEntity<TbItemParamItem> resp = rest.getForEntity(urlConfig.getItemParamItem(), TbItemParamItem.class, map);
		if (resp.getStatusCode() == HttpStatus.OK) {
			return resp.getBody();
		}
		return null;
	}
}
