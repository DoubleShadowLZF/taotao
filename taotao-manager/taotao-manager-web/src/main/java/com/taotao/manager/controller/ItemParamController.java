package com.taotao.manager.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.manager.service.ItemParamService;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 规格参数
 */
@RestController
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;

	/**
	 * 规格参数列表
	 * @param page 当前页码
	 * @param rows 一页条目数
	 * @return EasyUI DataGrid 结构体
	 */
	@GetMapping("/list")
	public EUDataGridResult list(int page , int rows){
		return itemParamService.getItemParamList(page,rows);
	}

	@GetMapping("/query/itemcatid/{itemcatid}")
	public TaotaoResult getItemPram(@PathVariable Long itemcatid){
		return itemParamService.getItemParam(itemcatid);
	}

	@PostMapping("/save/{itemCatId}")
	public TaotaoResult saveItemParam(@PathVariable Long itemCatId, String paramData){
		return itemParamService.saveItemParam(itemCatId , paramData);
	}


}
