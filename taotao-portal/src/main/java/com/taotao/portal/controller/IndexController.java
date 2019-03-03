package com.taotao.portal.controller;

import com.alibaba.fastjson.JSONArray;
import com.taotao.common.config.UrlConfig;
import com.taotao.portal.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 首页Controller
 */
@Controller
@Slf4j
public class IndexController {
	@Autowired
	private IndexService pageService;

	@Autowired
	private UrlConfig urlConfig;


	@GetMapping({"/index", "/index.html", "/"})
	public String index(Model model) {
		String adJson = pageService.getAdItemList();
		model.addAttribute("ad1", adJson);
		return "index";
	}

	@GetMapping({"/search", "/search.html"})
	public String search(@RequestParam("q") String queryString, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "row", defaultValue = "60") Integer row, HttpServletRequest req) throws UnsupportedEncodingException {
		RestTemplate restTemplate = new RestTemplate();
		Map map = new HashMap(3);
		queryString = new String(queryString.getBytes("ISO8859-1"),"UTF-8");
		log.debug("查询商品：{}",queryString);
		map.put("q",queryString );
		map.put("page", page);
		map.put("row", row);
		String url = urlConfig.getSearchUrl() + "?q={q}&page={page}&row={row}";
//		String url = "http://search.taotao.com?q={q}&page={page}&row={row}";
		List searchResult = restTemplate.getForObject(url, JSONArray.class, map);
		req.setAttribute("itemList", searchResult);
		req.setAttribute("query", queryString);
		int totalPage = searchResult.size() % 60 > 0 ? searchResult.size() / 60 + 1 : searchResult.size() / 60;
		req.setAttribute("totalPages", totalPage);
		req.setAttribute("page", page);
		return "search";
	}


}
