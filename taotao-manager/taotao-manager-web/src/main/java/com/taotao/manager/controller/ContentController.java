package com.taotao.manager.controller;

import com.taotao.manager.pojo.domain.TbContent;
import com.taotao.manager.service.ContentService;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 */
@RestController
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@PostMapping("/save")
	public TaotaoResult save(TbContent content){
		return contentService.save(content);
	}

	@GetMapping("/adItemList")
	public String getAdItemList(){
		return contentService.getAdItemList();
	}

}
