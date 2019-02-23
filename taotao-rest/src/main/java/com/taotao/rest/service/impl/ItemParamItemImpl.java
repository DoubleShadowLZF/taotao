package com.taotao.rest.service.impl;

import com.taotao.manager.dao.mapper.TbItemParamItemDAO;
import com.taotao.manager.pojo.domain.TbItemParamItem;
import com.taotao.manager.pojo.domain.TbItemParamItemExample;
import com.taotao.rest.service.IItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
@Service
public class ItemParamItemImpl implements IItemParamItemService {
	@Autowired
	private TbItemParamItemDAO itemParamItemDAO;


	@Override
	public TbItemParamItem getItemParamItem(Long itemId) {
		TbItemParamItemExample e = new TbItemParamItemExample();
		TbItemParamItemExample.Criteria criteria = e.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> itemParamItems = itemParamItemDAO.selectByExample(e);
		return itemParamItems.size() > 0 ? itemParamItems.get(0) : null;
	}
}
