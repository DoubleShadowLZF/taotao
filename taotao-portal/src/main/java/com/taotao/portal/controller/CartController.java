package com.taotao.portal.controller;

import com.taotao.portal.service.ICartService;
import com.taotao.common.utils.TaotaoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description
 * @Author Double
 */
@Controller
@Slf4j
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private ICartService cartService;

	@GetMapping("/cart.html")
	public String cartPage(HttpServletRequest request,Model model) {
		List itemList = cartService.getCartItemList(request);
		model.addAttribute("cartList",itemList);
		return "cart";
	}

	@GetMapping("/add/{itemId}.html")
	public String addPage(@PathVariable("itemId") String itemId, @RequestParam(value = "num",defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response) {
		cartService.addPage(itemId, num, request, response);
		return "cartSuccess";
	}

	@GetMapping("/delete/{itemId}.html")
	public String deleteItem(@PathVariable("itemId")String itemId, @RequestParam(value = "num",defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
		cartService.deleteItem(itemId, num, request, response);
		return "redirect:/cart/cart.html";
	}

	@PostMapping("/update/num/{itemId}/{num}.action")
	public TaotaoResult updateNum(@PathVariable("itemId")String itemId,@PathVariable("num") Integer num, HttpServletRequest request, HttpServletResponse response){
		return cartService.updateNum(itemId,num,request,response);
	}

	@DeleteMapping("/cartList")
	public TaotaoResult deleteCartList(HttpServletRequest request,HttpServletResponse response){
		return cartService.deleteCartList(request,response);
	}


}
