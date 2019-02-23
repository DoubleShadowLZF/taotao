package com.taotao.sso.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 */
@Controller
@RequestMapping("/page")
public class PageController {

	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}

	@GetMapping("/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}

}
