package com.taotao.manager.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.manager.service.ContentCategoryService;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 */
@RestController
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	@GetMapping("/list")
	public List<EUTreeNode> list(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return contentCategoryService.getTreeNodes(parentId);
	}

	@PostMapping("/create")
	public TaotaoResult create(Long parentId,String name) {
		return contentCategoryService.createNode(parentId , name);
	}

	@PostMapping("/delete")
	public TaotaoResult delete(@RequestParam(value = "id", required = true) Long id) {
		return contentCategoryService.deleteNode(id);
	}

	@PostMapping("/update")
	public TaotaoResult update(@RequestParam(value = "id", required = true) Long id, @RequestParam(value = "name") String name) {
		return contentCategoryService.update(id, name);
	}

}
