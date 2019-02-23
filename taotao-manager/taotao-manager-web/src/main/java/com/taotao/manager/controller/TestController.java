package com.taotao.manager.controller;

import com.taotao.manager.pojo.domain.TbUser;
import com.taotao.manager.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 */
@RestController
@RequestMapping("/test")
public class TestController {
	@GetMapping
	public String testResponse() {
		return "Hello world";
	}

	@Autowired
	private PageService pageService;

	@GetMapping("/user")
	public TbUser testUser(int id){
		return pageService.getTbUser(id);
	}
}
