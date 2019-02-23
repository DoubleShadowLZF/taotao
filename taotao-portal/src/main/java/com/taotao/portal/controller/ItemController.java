package com.taotao.portal.controller;

import com.taotao.portal.service.IitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description 货品Controller
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private IitemService itemService;

	@GetMapping("/{itemId}")
	public String getItem(@PathVariable("itemId") Long itemId, HttpServletRequest req){
		Map<String,Object> item = itemService.getItem(itemId);
		req.setAttribute("item",item);
		return "item";
	}

}
