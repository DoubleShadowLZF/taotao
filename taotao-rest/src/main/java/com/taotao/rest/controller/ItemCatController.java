package com.taotao.rest.controller;

import com.taotao.rest.service.ItemCatRestService;
import com.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 */
@RestController
@RequestMapping("/itemcat")
public class ItemCatController {

	@Autowired
	private ItemCatRestService itemCatRestService;

	@GetMapping("/all")
	public String showItem(String callback){
		return callback + "(" + JsonUtils.objectToJson(itemCatRestService.getItemCatList()) +");";
	}

}
