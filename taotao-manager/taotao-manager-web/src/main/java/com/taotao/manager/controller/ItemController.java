package com.taotao.manager.controller;

import com.taotao.manager.pojo.domain.TbItem;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.manager.service.ItemService;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 */
@RestController
@RequestMapping("/item")
public class ItemController {

	@Resource
	private ItemService itemService;

	@GetMapping("/list")
	public EUDataGridResult getItemList(@RequestParam(value="page",defaultValue = "1") Integer page, @RequestParam(value = "rows",defaultValue = "10") Integer rows) {
		return itemService.getItemList(page, rows);
	}

	@PostMapping("/save")
	public TaotaoResult saveItem(TbItem item, String desc, String itemParams) {
		return itemService.saveItem(item, desc, itemParams);
	}

	@GetMapping("/{itemId}")
	public TaotaoResult getItem(@PathVariable("itemId") String itemId){
		return itemService.getItem(itemId);
	}


}
