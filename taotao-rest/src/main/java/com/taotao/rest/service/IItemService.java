package com.taotao.rest.service;

import com.taotao.manager.pojo.domain.TbItem;

/**
 * @Description
 */
public interface IItemService {

	/**
	 * 获取商品信息
	 * @param param
	 * @return
	 */
	TbItem getItem(Long itemId);

}
