package com.taotao.manager.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.manager.service.ItemCatService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 商品类目
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

	@Resource
	private ItemCatService itemCatService;

	@PostMapping("/list")
	public List<EUTreeNode> getEUTreeNodes(@RequestParam(value = "id",defaultValue = "0") Long id) {
		return itemCatService.getTreeNodes(id);
	}


}
