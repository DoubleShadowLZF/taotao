package com.taotao.portal.controller;

import com.taotao.manager.pojo.domain.TbOrder;
import com.taotao.portal.service.ICartService;
import com.taotao.portal.service.IOrderService;
import com.taotao.manager.pojo.qo.OrderQo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description
 * @Author Double
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private IOrderService orderService;

	@Autowired
	private ICartService cartService;

	@GetMapping("/order-cart")
	public String toOrderCartPage(HttpServletRequest request, Model model) {
		//cartList
		List<TbOrder> cartList = cartService.getCartItemList(request);
		model.addAttribute("cartList", cartList);
		return "order-cart";
	}

	@PostMapping("/create")
	public String createOrderPost(OrderQo query, Model model, HttpServletRequest request, HttpServletResponse response) {
		String orderId = orderService.createOrder(query,request,response);
		model.addAttribute("oderId", orderId);
		model.addAttribute("payment", query.getPayment());
		model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
		return "success";
	}
}
