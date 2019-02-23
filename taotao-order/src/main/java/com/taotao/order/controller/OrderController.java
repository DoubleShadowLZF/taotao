package com.taotao.order.controller;

import com.taotao.manager.pojo.qo.OrderQo;
import com.taotao.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author Double
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private IOrderService orderService;

	@PostMapping("/create")
	public ResponseEntity create(@RequestBody OrderQo query, HttpServletRequest request, HttpServletResponse response){
		return orderService.create(query,request,response);
	}

}
