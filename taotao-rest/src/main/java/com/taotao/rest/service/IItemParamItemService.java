package com.taotao.rest.service;

import com.taotao.manager.pojo.domain.TbItemParamItem;

/**
 * @Description
 */
public interface IItemParamItemService {
	/**
	 * 获取商品规格参数
	 * @param itemId
	 * @return
	 */
	TbItemParamItem getItemParamItem(Long itemId);
}
