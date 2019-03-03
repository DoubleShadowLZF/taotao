package com.taotao.manager.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.common.utils.EasyUIResult;
import com.taotao.manager.pojo.qo.ContentQo;
import com.taotao.manager.pojo.domain.TbContent;
import com.taotao.manager.service.ContentService;
import com.taotao.common.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@GetMapping("/query/list")
	public EasyUIResult getContentList(ContentQo query){
		return contentService.getContentList(query);
	}
}
