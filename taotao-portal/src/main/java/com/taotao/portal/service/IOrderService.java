package com.taotao.portal.service;

import com.taotao.manager.pojo.qo.OrderQo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author Double
 */
public interface IOrderService {
	String createOrder(OrderQo query, HttpServletRequest request, HttpServletResponse response);
}
