package com.taotao.manager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.taotao.manager.dao.mapper.TbContentDAO;
import com.taotao.manager.pojo.domain.TbContent;
import com.taotao.manager.pojo.domain.TbContentExample;
import com.taotao.manager.service.ContentService;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentDAO contentDAO;

	@Override
	@CacheEvict(cacheNames = "contentList")
	public TaotaoResult save(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentDAO.insertSelective(content);
		return TaotaoResult.ok();
	}

	@Override
	@Cacheable(cacheNames = "contentList")
	public String getAdItemList() {
		TbContentExample e =new TbContentExample();
		List<TbContent> tbContents = contentDAO.selectByExampleWithBLOBs(e);
		return JSONObject.toJSONString(tbContents);
	}


}
