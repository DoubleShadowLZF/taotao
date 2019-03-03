package com.taotao.rest.service.impl;

import com.taotao.manager.dao.mapper.TbItemCatDAO;
import com.taotao.manager.pojo.domain.TbItemCat;
import com.taotao.manager.pojo.domain.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatRestService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 */
@Service
public class ItemCatRestServiceImpl implements ItemCatRestService {


	private Logger log = LoggerFactory.getLogger(ItemCatRestServiceImpl.class);

	@Autowired
	private TbItemCatDAO tbItemCatDAO;

	@Override
	@Cacheable(cacheNames = "itemCatList")
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		catResult.setData(getItemCatData(0L));
		return catResult;
	}

	@Cacheable(cacheNames = "itemCatData")
	public List getItemCatData(Long parentId) {
		List result = new ArrayList();
		TbItemCatExample example = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCats = tbItemCatDAO.selectByExample(example);
		log.debug("tbItemCats:{}",tbItemCats);
		int len = parentId == 0 ? tbItemCats.size() -5 : tbItemCats.size();
		for (int i = 0; i < len ; i++) {
			CatNode catNode = new CatNode();
			TbItemCat cat = tbItemCats.get(i);
			if (cat.getIsParent()) {
				if (cat.getParentId() == 0) {
					catNode.setUrl("/products/" + cat.getId() + ".html");
					catNode.setName("<a href='/products/" + cat.getId() + ".html'>" + cat.getName() + "</a>");
					catNode.setItem(getItemCatData(cat.getId()));
				} else {
					catNode.setUrl("/products/" + cat.getId() + ".html");
					catNode.setName(cat.getName());
					catNode.setItem(getItemCatData(cat.getId()));
				}
				result.add(catNode);
			} else {
				//"/products/3.html|电子书",
				result.add("/products/"+cat.getId()+".html|"+cat.getName());
			}
		}
		return result;
	}

}
