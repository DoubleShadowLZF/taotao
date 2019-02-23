package com.taotao.rest.service.impl;

import com.taotao.manager.dao.mapper.TbItemDAO;
import com.taotao.manager.pojo.domain.TbItem;
import com.taotao.manager.pojo.domain.TbItemExample;
import com.taotao.rest.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private TbItemDAO itemDAO;

	@Override
	public TbItem getItem(Long itemId) {
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> tbItems = itemDAO.selectByExample(example);
		if(tbItems.size() > 0){
			return tbItems.get(0);
		}
		return null;
	}
}
