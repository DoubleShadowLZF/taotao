package com.taotao.taotaosearch.controller;

import com.taotao.taotaosearch.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @Description
 */
@RestController
@Slf4j
public class SearchController {

	@Autowired
	private ISearchService searchService;

	@GetMapping
	public Object getList(@RequestParam("q") String queryString, @RequestParam(value = "page", defaultValue = "1") Integer page,
	                      @RequestParam(value = "row",defaultValue = "60") Integer row) {
//		if(!StringUtils.isEmpty(queryString)){
//			try {
//				queryString = new String(queryString.getBytes("ISO8859-1"),"UTF-8");
				log.debug("搜索商品名称：{}",queryString);
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
		return searchService.queryList(queryString, page, row);
	}
}
