package com.taotao.rest.controller;

import com.taotao.manager.pojo.domain.TbItemParamItem;
import com.taotao.rest.service.IItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 */
@RestController
@RequestMapping("/item/param/")
public class ItemParamItemController {

	@Autowired
	private IItemParamItemService itemParamItemService;

	@GetMapping({"/{itemId}","/{itemId}.html"})
	public TbItemParamItem getItemParamItem(@PathVariable Long itemId) {
		return itemParamItemService.getItemParamItem(itemId);
	}

}
