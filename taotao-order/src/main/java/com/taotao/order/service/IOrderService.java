package com.taotao.order.service;

import com.taotao.manager.pojo.qo.OrderQo;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author Double
 */
public interface IOrderService {

	ResponseEntity create(OrderQo query, HttpServletRequest request, HttpServletResponse response);
}
