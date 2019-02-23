package com.taotao.manager.controller;

import com.taotao.manager.service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @Description
 */
@Controller
public class PageController {

    @Resource
	private PageService pageService;

    @GetMapping("/{page}")
    public String toIndexPage(@PathVariable String page){
		return page;
    }
}
