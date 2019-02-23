package com.taotao.rest.controller;

import com.taotao.manager.pojo.domain.TbItem;
import com.taotao.rest.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 */
@RequestMapping("/item")
@RestController
public class ItemController {

	@Autowired
	private IItemService itemService;

	/**
	 * 根据商品信息获取商品
	 * @param itemId 商品Id
	 * @return
	 */
	@GetMapping("/{itemId}")
	public TbItem getItem(@PathVariable("itemId")Long itemId){
		return itemService.getItem(itemId);
	}

}
