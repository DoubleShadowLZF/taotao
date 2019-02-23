package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description 
 */
@Controller
public class TestController {
	@GetMapping("/hello")
	public String hello(){
		return "success";
	}
}
